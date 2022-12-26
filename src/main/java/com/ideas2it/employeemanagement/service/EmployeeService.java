
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

import org.springframework.http.ResponseEntity;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * Interface for a class that validates all input given by users
 *      Does Operations like create, update, display, delete 
 *      employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
public interface EmployeeService {

    /**
     * Creates employees.
     * 
     * @param employeeDto as EmployeeDTO object that contains an 
     *        employee to be created.
     * 
     * @return the employee created as ResponseEntity.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> createEmployee(EmployeeDTO employeeDto)
            throws EMSException;

    /**
     * Gets all the Employees from the Database.
     * 
     * @return all the employees as list from the database as
     *         List<EmployeeDTO>.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<List<EmployeeDTO>> getEmployees() throws EMSException;

    /**
     * Gets the particular Employee.
     * 
     * @param employeeId of an employee as int.
     * 
     * @return EmployeeDTO object contains that particular
     *         employee.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> getEmployee(int employeeId) throws EMSException;

    /**
     * Deletes that particular employee.
     * 
     * @param employeeId of an employee as int.
     *
     * @return repsonse as as ResponseEntity.
     * 
     * @throws EMSException if error occurred in database.
     */
    ResponseEntity<String> deleteEmployee(int employeeId) throws EMSException;

    /**
     * updates an employee.
     * 
     * @param employee as EmployeeDTO object.
     * 
     * @return the updated employee  as ResponseEntity.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    ResponseEntity<EmployeeDTO> update(int employeeId, EmployeeDTO employee)
            throws EMSException;

    /**
     * updates an employee.
     * 
     * @param employeeId of an employee as int.
     * @param employee as EmployeeDTO object.
     * 
     * @return the updated employee  as ResponseEntity.
     * 
     * @throws EMSException exception message of the employee
     * management system.
     */
    ResponseEntity<EmployeeDTO> updateEmployee(int employeeId, Employee employee)
            throws EMSException;
    
    /**
     * checks whether the maild Id is available in the list of
     * mailIds.
     * 
     * @param employeeMailId of an employee as String.
     * @param employeeId of an employee as int.
     * 
     * @return true if the mail Id is present.
     */
    boolean isMailIdExists(String employeeMailId, int EmployeeId);

    /**
     * checks whether the contactNumber is available in the list of
     * contactNumbers.
     * 
     * @param contactNumber of an employee as String.
     * @param employeeId of an employee as int.
     * 
     * @return true if the mail Id is present.
     */
    boolean isContactNumberExists(String contactNumber, int employeeId);

    /**
     * checks the whether the employees and projects are available to
     * Add the project to that particular employee.
     * 
     * @param employeeId of an employee.
     * @param projectId of an projectId.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> assignProject(int employeeId, int projectId)
            throws EMSException;
    
    /**
     * checks the whether that particular employee and project are 
     * available to Add the project to that particular employee.
     *      
     * @param employeeId  of an employee as int.
     * @param projectId of an projectId as int.
     * 
     * @return the updated employee as ResponseEntity.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    ResponseEntity<EmployeeDTO> assign(int employeeId, int projectId)
            throws EMSException;
    
    /**
     *  Adds the project to that particular employee.
     *  
     * @param employeeId of an employee.
     * @param project contains a project as ProjectDTO object.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> assignProjectToEmployee(int employeeId, ProjectDTO project)
            throws EMSException;

    /**
     * checks the whether the employees and projects are available to
     * remove that project from that particular employee.
     * 
     * @param employeeId of an employee.
     * @param projectId of an projectId.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException if error occurred in database.
     */
    ResponseEntity<EmployeeDTO> unAssignProject(int employeeId, int projectId)
            throws EMSException;

    /**
     * checks the whether that employee and project are available to
     * remove that project from that particular employee.
     * 
     * @param employeeId of an employee.
     * @param projectId of an projectId.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> unAssign(int employeeId, int projectId)
            throws EMSException;
    
    /**
     * Deletes the project from that particular employee.
     * 
     * @param employeeId of an employee.
     * @param project contains a project as ProjectDTO object.
     * 
     * @return the updated employee.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<EmployeeDTO> unAssignProjectFromEmployee(int employeeId,
            ProjectDTO project)
            throws EMSException;
    /**
     * checks whether the project is already assigned to that 
     * employee or not.
     *
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     *
     * @return true if the project is already assigned.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    boolean isProjectAlreadyAssigned(int employeeId, int projectId)
            throws EMSException;

    /**
     * Gets the particular employee.
     * 
     * @param employeeId of an employee as int.
     * 
     * @return EmployeeDTO object contains that particular
     *         employee.
     */
    EmployeeDTO getParticularEmployee(int employeeId);
    
    /**
     * Checks whether the Employees are available are not.
     * 
     * @return true if the empolyees are available.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    boolean isEmployeesAvailable() throws EMSException;
}