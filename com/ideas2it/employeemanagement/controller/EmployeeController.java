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
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * An object is created for a public class Employee Service.
 * passes the arguments sent by Employees view class to Employee
 * Service class to create, Display, Update, Delete and to validate
 * employees.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class EmployeeController {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    /**
     * empty constructor.
     */
    public EmployeeController() {
    }
  
    /**
     * validates employeeName.
     *
     * @param employeeName as String.
     *     
     * @returns true if the validation of the name is true.
     * @returns false if the validation of the name is false.
     */
    public boolean validateEmployeeName(String employeeName) {
        return employeeService.validateEmployeeName(employeeName);
    }

    /**
     * validates employeePhoneNumber.
     *
     * @param employeeNumber as String.
     *     
     * @returns true if the validation of the Phone number is true.
     * @returns false if the the validation of the Phone number is false.
     */
    public boolean validateEmployeeContactNumber(String employeePhoneNumber) {
        return employeeService.
                validateEmployeeContactNumber(employeePhoneNumber);
    }

    /**
     * validates employeeSalary.
     *
     * @param employeeSalary as String.
     *     
     * @returns true if the validation of the salary is true.
     * @returns false if the validation of the salary is false.
     */
    public boolean validateEmployeeSalary(String employeeSalary) {
        return employeeService.validateEmployeeSalary(employeeSalary);        
    }

    /**
     * validates employeeMailId.
     *
     * @param employeeEmailId as String.
     *     
     * @returns true if the validation of the Mail Id is true.
     * @returns false if the validation of the Mail Id is false.
     * returns false.
     */
    public boolean validateEmployeeMailId(String employeeMailId) {
        return employeeService.validateEmployeeMailId(employeeMailId);       
    }

    /**
     * validates employeeBirthDate.
     *
     * @param employeeBirthDate as String.
     *     
     * @returns true if the validation of the Birth Date is true.
     * @returns false if the validation of the Birth Date is false.
     */
    public boolean validateEmployeeBirthDate(String employeeBirthDate) {
        return employeeService.validateEmployeeBirthDate(employeeBirthDate);        
    }

    /**
     * Inserts Employees into the Database .
     */
    public boolean insertEmployee(EmployeeDTO employeeDto) {
        return employeeService.insertEmployee(employeeDto);
    }

    /**
     * Retrieves all the employees from the Database as an ArrayList.
     *
     * @return ArrayList that contains all the employees.
     */
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /** 
     * Retrieves particular employee from the Database.
     *
     * @return Employee. 
     */
    public EmployeeDTO getParticularEmployee(int employeeId) {
        return employeeService.getParticularEmployee(employeeId);
    }

    /**
     * checks whether the Database is empty or not.
     *
     * @return true if the Database is Empty.
     *
     * @return false if the Database is not Empty.
     */
    public boolean isDbIsEmpty() {
        return employeeService.isDbIsEmpty();
    }

    /**
     * checks whether the Id is present in the Database or not.
     *
     * @return true if the Database contains that particular 
     * Employee Id.
     *
     * @return false if the Database contains that particular 
     * Employee Id.
     *
     * @param employeeId as int.
     */
    public boolean isIdExists(int employeeId) {
        return employeeService.isIdExists(employeeId);
    }

    /**
     * checks whether the particular Employee Phone Number is present
     * in the Database or not.
     *
     * @param contactNumber as Stirng.
     *
     * @return true if the Phone Number is present int the Database.
     *
     * @return false if the Phone Number is not present in the
     * Database.
     */
    public boolean isContactNumberExists(String contactNumber) {
        return employeeService.isContactNumberExists(contactNumber);
    }

    /**
     * checks whether the particular Mail Id is present
     * in the Database or not.
     *
     * @param employeeMailId as String.
     *
     * @return true if the MailId is present int the
     * Database.
     *
     * @return false if the MailId is not present in
     * the Database.
     */
    public boolean isMailIdExists(String employeeMailId) {
        return employeeService.isMailIdExists(employeeMailId);
    }

    /**
     * Updates all the fields of an Employees.
     */
    public boolean updateEmployee(EmployeeDTO employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

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
    public boolean isPhoneNumberExists(long phoneNumber,int employeeId) {
        return employeeService.isPhoneNumberExists(phoneNumber,employeeId); 
    }

    /**
     * checks whether the particular Mail Id is present
     * in the Database except the mailId of the employee given 
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
    public boolean isMailExists(String mailId, int employeeId) {
        return employeeService.isMailExists(mailId, employeeId); 
    }

    /**
     * delete All Employees in the Database.
     */
    public boolean deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }

    /**
     * deletes the particular employee user wants to delete.
     *
     * @param employeeId as int.
     *
     * @return true if the Employee is deleted.
     * @return false if the Employee is not deleted.
     */
    public boolean deleteParticularEmployee(int employeeId) {
        return employeeService.deleteParticularEmployee(employeeId);
    }

    /**
     * Assigns Employee to Project.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the Employee is assigned to a Project.
     * @return false if the Employee is not assigned to a Project.
     */
    public boolean assignEmployeeToProject(int employeeId, int projectId) {
        return employeeService.assignEmployeeToProject(employeeId, projectId); 
    }

    /**
     * checks the Database whether the Project is Already Assigned or not.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the Project is already assigned.
     * @return false if the Project is not assigned.
     */
    public boolean checkRedundancy(int employeeId, int projectId) {
        return employeeService.checkRedundancy(employeeId, projectId); 
    }
}