
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Interface for performing insert, update, delete, retrieve
 * projects in the database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public interface EmployeeDAO {

    /**
     * Inserts employee into the Database.
     *
     * @param employee as Employee object.
     *
     * @return employeeId generated as int.
     */
    int insertEmployee(Employee employee) throws EMSException;

    /**
     * Retrieves all the employees From the database.
     *
     * @return all the employees as List<Employee>.
     */
    List<Employee> getAllEmployees() throws EMSException;


    /**
     * Retrieves the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as Employee object.
     */
    Employee getParticularEmployee(int employeeId) throws EMSException;

    /**
     * updates the employee.
     *
     * @param employee as Employee object.
     */
    void updateEmployee(Employee employee) throws EMSException;

    /**
     * gets all the contact number except the contact number of that
     * given employee id.
     *
     * @param employeeId as int.
     *
     * @return all the contact number as List<Long>.
     */
    List<Long> getContactNumber(int employeeId) throws EMSException;

    /**
     * gets all the mail id except the mail id of that
     * given employee id.
     *
     * @param employeeId as int.
     *
     * @return all the mail id as List<String>.
     */
    List<String> getMailId(int employeeId) throws EMSException;

    /**
     * Deletes all the employees in the database.
     */
    void deleteAllEmployees() throws EMSException;

    /**
     * Deletes Particular employee user wants to delete from the Map
     *
     * @param  employeeId as int.
     */
    void deleteParticularEmployee(int employeeId) throws EMSException;
}