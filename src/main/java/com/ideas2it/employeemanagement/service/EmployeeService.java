
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ideas2it.employeemanagement.dto.AthenticationRequest;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;

/**
 * Interface that validates all input given by users to
 *      do Operations like create, update, display, delete 
 *      employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
public interface EmployeeService extends UserDetailsService{

    /**
     * Creates employees.
     * 
     * @param employeeDto as EmployeeDTO object that contains an 
     *        employee to be created.
     * 
     * @return the employee created as ResponseEntity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    EmployeeDTO createEmployee(EmployeeDTO employeeDto)
            throws EmployeeManagementSystemException;

    /**
     * Gets all the Employees from the Database.
     * 
     * @return all the employees as list from the database as
     *         List<EmployeeDTO>.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    List<EmployeeDTO> getEmployees() throws EmployeeManagementSystemException;

    /**
     * Gets the particular Employee.
     * 
     * @param employeeId of an employee as int.
     * 
     * @return EmployeeDTO object contains that particular
     *         employee.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    EmployeeDTO getEmployeeById(int employeeId) throws
    		EmployeeManagementSystemException;

    /**
     * Deletes that particular employee.
     * 
     * @param employeeId of an employee as int.
     *
     * @return repsonse as as ResponseEntity.
     * 
     * @throws EmployeeManagementSystemException if error occurred in database.
     */
    String deleteEmployee(int employeeId) throws
    		EmployeeManagementSystemException;

    /**
     * updates an employee.
     * 
     * @param employee as EmployeeDTO object.
     * 
     * @return the updated employee  as ResponseEntity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    EmployeeDTO updateEmployee(EmployeeDTO employee)
            throws EmployeeManagementSystemException;

    /**
     * checks the whether the employees and projects are available to
     * Add the project to that particular employee.
     * 
     * @param employeeId of an employee.
     * @param projectId of an projectId.
     * 
     * @return the updated employee.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    EmployeeDTO assignProject(int employeeId, int projectId)
            throws EmployeeManagementSystemException;

    /**
     * checks the whether the employees and projects are available to
     * remove that project from that particular employee.
     * 
     * @param employeeId of an employee.
     * @param projectId of an projectId.
     * 
     * @return the updated employee.
     * 
     * @throws EmployeeManagementSystemException if error occurred in database.
     */
    EmployeeDTO unAssignProject(int employeeId, int projectId)
            throws EmployeeManagementSystemException;

    /**
     * checks whether the project is already assigned to that 
     * employee or not.
     *
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     *
     * @return true if the project is already assigned.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    boolean isProjectAlreadyAssigned(EmployeeDTO employee, int projectId)
            throws EmployeeManagementSystemException;

    /**
     * Gets the user from the database based on the username we
     * 		provide.
     * @param username of the client.
     * @return the user retrieved from the database.
     */
	UserDetails loadUserByUsername(String username);

	/**
	 * checks whether the authentication credentials are valid. if
	 * 		not valid throws excepetion else Builds a Json web token
	 * 		for the user. The json web token is build by setting the
	 * 		claims and digitally signed with a algorithm and a secret
	 * 		by compacting it into a URL-safe JWT String.
	 * 
     * @param authRequest as AthenticationRequest object contains username
     * 		and password.
	 * 
	 * @return A compact URL-safe JWT string. 
	 */
	String generateToken(AthenticationRequest authRequest);
}