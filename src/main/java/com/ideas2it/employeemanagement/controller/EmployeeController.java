
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
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

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /**
     * Creates employee.
     * 
     * @param employeeDto as EmployeeDTO object that contains an 
     *        employee to be created.
     * 
     * @return the employee created.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    @PostMapping
    private ResponseEntity<EmployeeDTO> createEmployee
            (@RequestBody @Valid EmployeeDTO employee) throws EMSException {
        return employeeService.createEmployee(employee); 
    }

    /**
     * Gets all the employees from the Database.
     * 
     * @return all the employees as list from the database as
     *         List<EmployeeDTO>.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    @GetMapping
    private ResponseEntity<List<EmployeeDTO>> getEmployees()
            throws EMSException {
        return employeeService.getEmployees();
    }

    /**
     * Gets the particular Employee from the database.
     * 
     * @param employeeId of an employee as int.
     * 
     * @return EmployeeDTO object that contains that particular
     *         employee.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @GetMapping("/{employeeId}")
    private ResponseEntity<EmployeeDTO> displayEmployee
            (@PathVariable int employeeId) throws EMSException {
        return employeeService.getEmployee(employeeId);
    }

    /**
     * updates an employee.
     * 
     * @param employeeDto as EmployeeDTO object that contains an
     *        employee.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @PutMapping("/{employeeId}")
    private ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable int employeeId, @RequestBody @Valid 
            EmployeeDTO employeeDto) throws EMSException {
        return employeeService.update(employeeId, employeeDto);
    }

    /**
     * Adds the project to that particular employee.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int
     *
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @PutMapping("/{employeeId}/projects/{projectId}")
    private ResponseEntity<EmployeeDTO> assignProject(
            @PathVariable int employeeId, @PathVariable int projectId)
            throws EMSException {
        return employeeService.assignProject(employeeId, projectId);
    }

    /**
     * Deletes that particular project from that employee.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @DeleteMapping("/{employeeId}/projects/{projectId}")
    private ResponseEntity<EmployeeDTO> unAssignProject(
            @PathVariable int employeeId, @PathVariable int projectId)
            throws EMSException {
        return employeeService
                .unAssignProject(employeeId, projectId);
    }

    /**
     * Deletes particular employee.
     * 
     * @param employeeId of an employee as int.
     *
     * @return deleted message as String.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @DeleteMapping("/{employeeId}")
    private ResponseEntity<String> deleteEmployee(@PathVariable int employeeId)
            throws EMSException {
        return employeeService.deleteEmployee(employeeId);
    }
}