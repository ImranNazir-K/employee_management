
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.dto.AthenticationRequest;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * performs operations like create, Display, Update, Delete for
 *      employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    /**
     * Creates employee.
     * 
     * @param employeeDto as EmployeeDTO object that contains an 
     *        employee to be created.
     * 
     * @return the employee created as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee
            (@RequestBody @Valid EmployeeDTO employee) throws
            EmployeeManagementSystemException {
        return new ResponseEntity<EmployeeDTO>(employeeService.createEmployee
        		(employee), HttpStatus.CREATED);
    }

    /**
     * Gets all the employees from the Database.
     * 
     * @return all the employees as List<EmployeeDTO> as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees()
            throws EmployeeManagementSystemException {
        return new ResponseEntity<List<EmployeeDTO>>(employeeService
        		.getEmployees(), HttpStatus.OK);
    }

    /**
     * Gets the particular Employee from the database.
     * 
     * @param employeeId of an employee as int.
     * 
     * @return EmployeeDTO object that contains that particular
     *         employee as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID
            (@PathVariable int employeeId) throws
            EmployeeManagementSystemException {
        return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById
        		(employeeId), HttpStatus.OK);
    }

    /**
     * updates an employee.
     * 
     * @param employeeDto as EmployeeDTO object that contains an
     *        employee.
     * 
     * @return the updated employee as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     * 		   of the employee management system.
     */
    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid 
            EmployeeDTO employeeDto) throws EmployeeManagementSystemException {
        return new ResponseEntity<EmployeeDTO>(employeeService.updateEmployee
        		(employeeDto), HttpStatus.OK);
    }

    /**
     * Adds the project to that particular employee.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int
     *
     * @return the updated employee as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @PutMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDTO> assignProject(
            @PathVariable int employeeId, @PathVariable int projectId)
            throws EmployeeManagementSystemException {
        return new ResponseEntity<EmployeeDTO>(employeeService.assignProject
        		(employeeId, projectId), HttpStatus.OK);
    }

    /**
     * Deletes that particular project from that employee.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated employee as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @DeleteMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDTO> unAssignProject(
            @PathVariable int employeeId, @PathVariable int projectId)
            throws EmployeeManagementSystemException {
        return new ResponseEntity<EmployeeDTO>(employeeService
                .unAssignProject(employeeId, projectId), HttpStatus.OK);
    }

    /**
     * Deletes particular employee.
     * 
     * @param employeeId of an employee as int.
     *
     * @return deleted message as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId)
            throws EmployeeManagementSystemException {
        return new ResponseEntity<String>(employeeService
        		.deleteEmployee(employeeId), HttpStatus.OK);
    }
    
    /**
	 * Builds a Json web token for the user. The json web token is
	 * 		build by setting the claims and digitally signed with a
	 * 		algorithm and a secret by compacting it into a URL-safe
	 * 		JWT String.
	 * 
     * @param authRequest as AthenticationRequest object contains
     *      username and password.
	 * 
	 * @return A compact URL-safe JWT string. 
     */
    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken
    		(@RequestBody AthenticationRequest authRequest) {
    	return new ResponseEntity<String>(employeeService
    			.generateToken(authRequest), HttpStatus.OK);
    }
}