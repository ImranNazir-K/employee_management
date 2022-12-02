
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

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;

/**
 * Interface for a class that validates all input given by users
 * Does Operations like create, update, display, delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
public interface EmployeeService {

    /**
     * Creates employees.
     * 
     * @param employeeDto as EMployeeDTO object.
     * 
     * @return employeeId created as int.
     * 
     * @throws EMSException if error occurred in database.
     */
    int createEmployee(EmployeeDTO employeeDto) throws EMSException;

    /**
     * Gets all the Employees from the Database.
     * 
     * @return List<EmployeeDTO> all the employees from the database.
     * 
     * @throws EMSException if error occurred in database.
     */
    List<EmployeeDTO> getAllEmployees() throws EMSException;

    /**
     * Displays the particular Employee.
     * 
     * @param employeeId id of an employee as int.
     * 
     * @return EmployeeDTO object object contains particular employee.
     * 
     * @throws EMSException if error occurred in database.
     */
    EmployeeDTO getParticularEmployee(int employeeId)
            throws EMSException;

    /**
     * Deletes all the employees.
     * 
     * @throws EMSException if error occurred in database.
     */
    void deleteAllEmployees() throws EMSException;

    /**
     * Deletes particular employee.
     * 
     * @param employeeId id of an employee as int.
     * @throws EMSException if error occurred in database.
     */
    void deleteParticularEmployee(int employeeId) throws EMSException;

    /**
     * updates an employee.
     * 
     * @param employee as EmployeeDTO object.
     * @throws EMSException if error occurred in database.
     */
    void updateEmployee(EmployeeDTO employee) throws EMSException;

    /**
     * Gets the particular project.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ProjectDTO object contains particular project.
     * 
     * @throws EMSException if error occurred in database.
     */
    ProjectDTO getParticularProject(int projectId) throws EMSException;

    /**
     * checks whether Mail Id of an employee is present in the database.
     *
     * @param mailId Mail Id of an employee.
     * 
     * @return true if the Mail id is present in the database.
     * 
     * @throws EMSException  if error occurred in database.
     */
    boolean isMailIdExists(String mailId) throws EMSException;

    /**
     * checks whether the contact number of an employee is present in the
     * database.
     *  
     * @param contactNumber Contact number of an employee.
     * 
     * @return  true if the Contact number  is present in the database.
     * 
     * @throws EMSException if error occurred in database.
     */
    boolean isContactNumberExists(long contactNumber) throws EMSException;
    
    /**
     * checks whether the id is available in the database.
     * 
     * @param employeeId as int.
     * 
     * @return true if the id is present in the database.
     * 
     * @throws EMSException if error occurred in database.
     */
    boolean isIdExists(int employeeId) throws EMSException;

    /**
     * Gets all the projects.
     * 
     * @return List<ProjectDTO> all the project from the database.
     * 
     * @throws EMSException if error occurred in database.
     */
    List<ProjectDTO> getProjects() throws EMSException;

    /**
     * Checks whether the project database is empty or not.
     *
     * @return true if the project database is empty.

     * @throws EMSException if error occurred in database.
     */
    boolean isProjectDbIsEmpty() throws EMSException;

    /**
     * Checks whether the employee database is empty.
     *
     * @return true if the database is empty.

     * @throws EMSException if error occurred in database.
     */
    boolean isDbIsEmpty() throws EMSException;

    /**
     * Checks whether the project id is present in the database.
     *
     * @param projectId as int.
     *
     * @return true if the id is present in the database.

     * @throws EMSException if error occurred in database.
     */
    boolean isProjectIdExists(int projectId) throws EMSException;

    /**
     * checks the database whether the project is already assigned.
     *
     * @param employeeId id of an employee as int.
     * @param projectId id of an project as int.
     *
     * @return true if the project is already assigned. 

     * @throws EMSException if error occurred in database.
     */
    boolean isAlreadyAssigned(int employeeId, int projectId)
            throws EMSException;

}