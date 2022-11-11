/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
    int insertEmployee(Employee employee);

    /**
     * Retrieves all the employees From the database.
     *
     * @return all the employees as List.
     */
    List<Employee> getAllEmployees();


    /**
     * Retrieves the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as Employee object.
     */
    Employee getParticularEmployee(int employeeId);

    /**
     * updates the employee.
     *
     * @param employee as Employee object.
     */
    void updateEmployee(Employee employee);

    /**
     * gets all the contact number except the contact number of that
     * given employee id.
     *
     * @param employeeId as int.
     *
     * @return list of contact number as List<Long>.
     */
    List<Long> getContactNumber(int employeeId);

    /**
     * gets all the mail id except the mail id of that
     * given employee id.
     *
     * @param employeeId as int.
     *
     * @return list of mail id as List<String>.
     */
    List<String> getMailId(int employeeId);

    /**
     * gets all the employee id.
     *
     * @return list of employee id as List<Integer>.
     */
    List<Integer> getAllEmployeeId();

    /**
     * Deletes all the employees in the database.
     *
     * @return number of rows affected as int.
     */
    int deleteAllEmployees();

    /**
     * Deletes Particular employee user wants to delete from the Map
     *
     * @param  employeeId as int.
     */
    void deleteParticularEmployee(int employeeId);
}