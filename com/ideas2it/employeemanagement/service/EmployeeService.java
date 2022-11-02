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

/**
 * contains methods to Validate all input given by users.
 * Stores the employees in the Database.
 * Does Operations like Create, update, Display, Delete employees.
 *
 * @author { name = "IMRAN NAZIR K" }
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public interface EmployeeService {

    /**
     * Validates the Name of an employee given as 
     * input by user.
     *
     * @param employeeName as String.
     *
     * @return true if the regex is true.
     * @return false if the regex is false.
     */ 
    boolean validateEmployeeName (String employeeName);

    /**
     * Validates the Contact Number of an employee given as
     * input by user.
     *
     * @param contactNumber as String.
     *
     * @return true if the regex is true.
     * @return false if the regex is false.
     */
    boolean validateEmployeeContactNumber (String contactNumber);

    /**
     * Validates the Salary of an employee given as
     * input by user.
     *
     * @param salary as String.
     *
     * @return true if the regex is true.
     * @return false if the regex is false.
     */
    boolean validateEmployeeSalary (String employeeSalary);

    /**
     * Validates the Email Id of an employee given as
     * input by user.
     *
     * @param emailId as String.
     *
     * @return true if the regex is true.
     * @return false if the regex is false.
     */
    boolean validateEmployeeMailId (String employeeMailId);

    /**
     * Validates the Birth Date of an employee given as
     * input by user.
     *
     * @param birthDate as String.
     *
     * @return true if the regex is true.
     * @return false if the regex is false.
     */
    boolean validateEmployeeBirthDate(String employeeBirthDate);

    /**
     * inserts the employee into the Database. 
     */
    boolean insertEmployee(EmployeeDTO employeeDto);

    /**
     * Gets all the employees from the Database and Storing those 
     * employees in an ArrayList.
     * 
     * @return employeesList as ArrayList.
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * Gets the particular employee user wants from the Database.
     *
     * @return values of the given employeeId.
     */
    EmployeeDTO getParticularEmployee(int employeeId);

    /**
     * checks whether the Database is empty or not.
     *
     * @return true if the Database is Empty.
     * @return false if the Database is not Empty.
     */
    public boolean isDbIsEmpty();

    /**
     * checks whether the employeeId is present in the Database 
     * or not.
     *
     * @param employeeId as String.
     *
     * @return true if the Database contains that parameter.
     * @return false if the Database does not contains that
     * parameter.
     */
    boolean isIdExists(int employeeId);

    /**
     * checks whether the MailId is present in the Database
     * or not.
     *
     * @param mailId as String.
     *
     * @return true if the Database contains that parameter.
     * @return false if the Database does not contains that
     * parameter.
     */
    boolean isMailIdExists(String mailId);

    /**
     * checks whether the contactNumber is present in the Database
     * or not.
     *
     * @param contactNumber as String.
     *
     * @return true if the Database contains that parameter.
     * @return false if the Database does not contains that
     * parameter.
     */
    boolean isContactNumberExists(String contactNumber);

    /**
     * Updates the Employee in the Database.
     *
     * @param employeeDto as EmployeeDTO object.
     *
     * @return if the employee is Updated.
     *
     * @retun if the employee is not Updated. 
     */
    boolean updateEmployee(EmployeeDTO employeeDto);

    /**
     * checks the database whether it contains the Phone Number
     * except the Phone Number of an employee given.
     *
     * @param phoneNumber as long.
     *
     * @param employeeId as int.
     *
     * @return true if the Phone Number is present int the
     * Database.
     *
     * @return false if the Phone Number is not present in
     * the Database.
     */
    boolean isPhoneNumberExists(long phoneNumber, int employeeId);

    /**
     * checks the database whether the particular Mail Id is present
     * except the mailId of the employee given 
     * employeeId or not.
     *
     * @param employeeMailId as String.
     *
     * @param employeeId as int.
     *
     * @return true if the MailId is present int the
     * Database.
     *
     * @return false if the MailId is not present in
     * the Database.
     */
    boolean isMailExists(String mailId, int employeeId);

    /**
     * deletes All Employees in the Database.
     *
     * @return if the employee is Deleted.
     *
     * @retun if the employee is not Deleted.
     */
    boolean deleteAllEmployees();

    /**
     * Deletes the particular Project in the Database.
     *
     * @return if the employee is Deleted.
     *
     * @retun if the employee is not Deleted.
     */
    boolean deleteParticularEmployee(int employeeId);

    /**
     * Assigns Employee to Project.
     */
    boolean assignEmployeeToProject(int employeeId, int projectId);

    /**
     * checks the Database whether the Project is Already Assigned or not.
     *
     * @return true if the Project is already assigned.
     *
     * @return false if the Project is not assigned.
     */
    public boolean checkRedundancy(int employeeId, int projectId);
}

