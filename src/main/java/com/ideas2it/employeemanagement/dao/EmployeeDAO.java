
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * Interface for performing insert, update, delete, retrieve projects in the
 * database.
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
     *
     * @throws EMSException if error occurred in database
     */
    int createEmployee(Employee employee) throws EMSException;

    /**
     * Retrieves all the employees From the database.
     *
     * @return all the employees as List<Employee>.
     * 
     * @throws EMSException if error occurred in database
     */
    List<Employee> displayAllEmployees() throws EMSException;

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
     * 
     * @throws EMSException if error occurred in database
     */
    void updateEmployee(Employee employee) throws EMSException;

    /**
     * Deletes all the employees in the database.
     */
    void deleteAllEmployees() throws EMSException;

    /**
     * Deletes Particular employee user wants to delete from the Map
     *
     * @param employeeId as int.
     * 
     * @throws EMSException if error occurred in database
     */
    void deleteParticularEmployee(int employeeId) throws EMSException;
}