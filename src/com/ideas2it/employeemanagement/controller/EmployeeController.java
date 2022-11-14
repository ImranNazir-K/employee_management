/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * performs operations like create, Display, Update, Delete and to
 * validate employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public EmployeeController() {
    }
  
    /**
     * Validates name of an employee.
     *
     * @param employeeName as String.
     *     
     * @returns true if the validation of the employee name is true.
     */
    public boolean validateEmployeeName(String employeeName) {
        return employeeService.validateEmployeeName(employeeName);
    }

    /**
     * Validates the phone number of an employee.
     *
     * @param employeePhoneNumber as String.
     *     
     * @returns true if the validation of the employee contact
     * number is true.
     */
    public boolean validateEmployeeContactNumber(String employeePhoneNumber) {
        return employeeService.
                validateEmployeeContactNumber(employeePhoneNumber);
    }

    /**
     * Validates the salary of an employee.
     *
     * @param employeeSalary as String.
     *     
     * @returns true if the validation of the employee salary is true.
     */
    public boolean validateEmployeeSalary(String employeeSalary) {
        return employeeService.validateEmployeeSalary(employeeSalary);        
    }

    /**
     * Validates the mail id of an employee.
     *
     * @param employeeMailId as String.
     *     
     * @returns true if the validation of the employee mail id is
     * true.
     */
    public boolean validateEmployeeMailId(String employeeMailId) {
        return employeeService.validateEmployeeMailId(employeeMailId);       
    }

    /**
     * Validates the  birth date of an employee.
     *
     * @param employeeBirthDate as String.
     *     
     * @returns true if the validation of the employee birth date is
     * true.
     */
    public boolean validateEmployeeBirthDate(String employeeBirthDate) {
        return employeeService.validateEmployeeBirthDate(employeeBirthDate);        
    }

    /**
     * Inserts employee into the database.
     *
     * @return generated employee Id as int.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    public int insertEmployee(EmployeeDTO employeeDto) {
        return employeeService.insertEmployee(employeeDto);
    }

    /**
     * Retrieves all the employees from the database.
     *
     * @return all employees as List<EmployeeDTO>.
     */
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /** 
     * Retrieves the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as EmployeeDTO object.
     */
    public EmployeeDTO getParticularEmployee(int employeeId) {
        return employeeService.getParticularEmployee(employeeId);
    }

    /**
     * Checks whether the database is empty.
     *
     * @return true if the database is Empty.
     */
    public boolean isDbIsEmpty() {
        return employeeService.isDbIsEmpty();
    }

    /**
     * checks whether the employee id is present in the database.
     *
     * @param employeeId as int.
     *
     * @return true if the Database contains that particular 
     * employee Id.
     */
    public boolean isIdExists(int employeeId) {
        return employeeService.isIdExists(employeeId);
    }

    /**
     * Checks whether the particular employee phone number is present
     * in the database.
     *
     * @param contactNumber as String.
     *
     * @return true if the contact number is present in the Database.
     */
    public boolean isContactNumberExists(String contactNumber) {
        return employeeService.isContactNumberExists(contactNumber);
    }

    /**
     * Checks whether the particular mail Id is present in the
     * database or not.
     *
     * @param employeeMailId as String.
     *
     * @return true if the MailId is present in the database.
     */
    public boolean isMailIdExists(String employeeMailId) {
        return employeeService.isMailIdExists(employeeMailId);
    }

    /**
     * Updates the employee.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    public void updateEmployee(EmployeeDTO employeeDto) {
        employeeService.updateEmployee(employeeDto);
    }

    /**
     * checks the database whether it contains the contact number
     * except the contact number of that given employee.
     *
     * @param contactNumber as long.
     * @param employeeId as int.
     *
     * @return true if the contact number is present int the
     * database.
     */
    public boolean isContactNumberExists(long contactNumber, int employeeId) {
        return employeeService.isContactNumberExists(contactNumber,
                employeeId);
    }

    /**
     * checks whether the particular mail Id is present in the
     * database except the mailId of that given employeeId
     *
     * @param mailId as String.
     *
     * @param employeeId as int.
     *
     * @return true if the MailId is present in the database.
     */
    public boolean isMailIdExists(String mailId, int employeeId) {
        return employeeService.isMailIdExists(mailId, employeeId); 
    }

    /**
     * delete All Employees in the Database.
     *
     * @return true if all the employees were deleted.
     */
    public boolean deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }

    /**
     * deletes the particular employee user wants to delete.
     *
     * @param employeeId as int.
     *
     * @return true if that particular employee was deleted.
     */
    public void deleteParticularEmployee(int employeeId) {
        employeeService.deleteParticularEmployee(employeeId);
    }

    /**
     * Gets all the projects available in the database.
     *
     * @return all the projects as List<ProjectDTO>.
     */
    public List<ProjectDTO> getAllProjects() {
        return employeeService.getAllProjects();
    }

    /**
     * Checks whether the project id is present in the database.
     *
     * @return true if that particular project id is present in the
     * database.
     */
    public boolean isProjectIdExists(int projectId) {
        return employeeService.isProjectIdExists(projectId);
    }

    /**
     * Checks whether the project database is empty.
     *
     * @return true if the project database is empty.
     */
    public boolean isProjectDbIsEmpty() {
        return employeeService.isProjectDbIsEmpty();
    }
    

    /**
     * checks the Database whether the Project is Already Assigned or not.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the Project is already assigned.
     */
    public boolean isAlreadyAssigned(int employeeId, int projectId) {
        return employeeService.isAlreadyAssigned(employeeId, projectId); 
    }

    /** 
     * Retrieves the particular project from the database.
     *
     * @param projectId as int.
     *
     * @return particular project as ProjectDTO object.
     */
    public ProjectDTO getParticularProject(int projectId) {
        return employeeService.getParticularProject(projectId);
    }
}