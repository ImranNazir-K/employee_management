
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * Validates all input given by users.
 * Does Operations like create, update, display, delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public interface EmployeeService {

    /**
     * Validates the Name of an employee.
     *
     * @param employeeName as String.
     *
     * @return true if the validation is true.
     */ 
    boolean validateEmployeeName (String employeeName);

    /**
     * Validates the contact number of an employee.
     *
     * @param contactNumber as String.
     *
     * @return true if the validation is true.
     */
    boolean validateEmployeeContactNumber (String contactNumber);

    /**
     * Validates the Salary of an employee.
     *
     * @param employeeSalary as String.
     *
     * @return true if the validation is true.
     */
    boolean validateEmployeeSalary (String employeeSalary);

    /**
     * Validates the Email Id of an employee.
     *
     * @param employeeMailId as String.
     *
     * @return true if the validation is true.
     */
    boolean validateEmployeeMailId (String employeeMailId);

    /**
     * Validates the Birth Date of an employee.
     *
     * @param employeeBirthDate as String.
     *
     * @return true if the validation is true
     */
    boolean validateEmployeeBirthDate(String employeeBirthDate);

    /**
     * inserts the employee into the Database.
     *
     * @return generated employee Id as int.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    int insertEmployee(EmployeeDTO employeeDto) throws EMSException;

    /**
     * Retrieves all the employees from the database.
     * 
     * @return all the employees as List<EmployeeDTO>.
     */
    List<EmployeeDTO> getAllEmployees() throws EMSException;

    /**
     * Gets the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as EmployeeDTO object.
     */
    EmployeeDTO getParticularEmployee(int employeeId) throws EMSException;

    /**
     * Checks whether the database is empty.
     *
     * @return true if the database is empty.
     */
    boolean isDbIsEmpty() throws EMSException;

    /**
     * checks whether the employee id is present in the database.
     *
     * @param employeeId as int.
     *
     * @return true if the Database contains that particular 
     * employee Id.
     */
    boolean isIdExists(int employeeId) throws EMSException;

    /**
     * Checks whether the particular mail Id is present
     * in the Database or not.
     *
     * @param employeeMailId as String.
     *
     * @return true if the MailId is present in the database.
     */
    boolean isMailIdExists(String mailId) throws EMSException;

    /**
     * Checks whether the particular employee phone number is present
     * in the database.
     *
     * @param contactNumber as String.
     *
     * @return true if the contact number is present in the database.
     */
    boolean isContactNumberExists(String contactNumber) throws EMSException;

    /**
     * Updates the employee.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    void updateEmployee(EmployeeDTO employeeDto) throws EMSException;

    /**
     * checks the database whether the particular contact number is
     * present except the contact number of the employee given.
     *
     * @param contactNumber as long.
     * @param employeeId as int.
     *
     * @return true if the Phone Number is present int the
     * database.
     */
    boolean isContactNumberExists(long contactNumber, int employeeId) 
            throws EMSException;

    /**
     * checks the database whether the particular Mail Id is present
     * except the mailId of the employee given 
     * employeeId or not.
     *
     * @param mailId as String.
     * @param employeeId as int.
     *
     * @return true if the MailId is present int the
     * Database.
     */
    boolean isMailIdExists(String mailId, int employeeId)
            throws EMSException;

    /**
     * deletes All Employees in the Database.
     */
    void deleteAllEmployees() throws EMSException;

    /**
     * Deletes the particular Project in the Database.
     *
     * @param employeeId as int.
     */
    void deleteParticularEmployee(int employeeId) throws EMSException;

    /**
     * Gets all the projects available in the database.
     *
     * @return all the projects as List<ProjectDTO>.
     */
    List<ProjectDTO> getAllProjects() throws EMSException;

    /**
     * Checks whether the project id is present in the database.
     *
     * @param projectId as int.
     *
     * @return true if the id is present in the database.
     */
    boolean isProjectIdExists(int projectId) throws EMSException;

    /**
     * Checks whether the project database is empty or not.
     *
     * @return true if the project database is empty.
     */
    boolean isProjectDbIsEmpty() throws EMSException;

    /**
     * checks the database whether the project is already assigned.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the project is already assigned.
     */
    boolean isAlreadyAssigned(int employeeId, int projectId)
            throws EMSException;

    /**
     * Retrieves the particular project from the database.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return particular project as List<ProjectDTO>.
     */
    ProjectDTO getParticularProject(int projectId) throws EMSException;
}

