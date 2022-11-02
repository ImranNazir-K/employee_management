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
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Interface Executes queries to Create, Update, Delete,
 * Display Employees in the database.
 *
 * @author (name = IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public interface EmployeeDAO {

    /**
     * inserts Employee into the Database.
     *
     * @param employee as Employee object.
     *
     * @return count of the Rows affected after execution of Queries
     * as int
     */
    int insertEmployee(Employee employee);

    /**
     * Retrieves All the Employees From the Database.
     *
     * @return List of Employees as employeeList.
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieves the particular Employee user wants From the
     * Database.
     *
     * @param employeeId as int.
     *
     * @return employee.
     */
    Employee getParticularEmployee(int employeeId);

    /**
     * updates the employee in the database.
     *
     * @param employee as Employee object.
     *
     * @return count of the Rows affected after execution of Queries
     * as int.
     */
    int updateEmployee(Employee employee);

    /**
     * Retrieves All the Employees From the Database except the
     * employee whose id is given.
     *
     * @param employeeId as int.
     *
     * @return List of Employees as employeeList.
     */
    List<Employee> getEmployees(int employeeId);

    /**
     * Deletes all the employees in the Database.
     *
     * @return count of the Rows affected after execution of Queries
     * as int.
     */
    int deleteAllEmployees();

    /**
     * Deletes Particular employee user wants to delete from the Map
     *
     * @param employeeId as int.
     *
     * @return count of the Rows affected after execution of Queries
     * as int.
     */
    int deleteParticularEmployee(int employeeId);

    /**
     * Assigns Employee to Project.
     *
     * @return count of the Rows affected after execution of Queries
     * as int.
     *
     * @param employeeId as int.
     * @param projectid as int.
     */
    int assignEmployeeToProject(int employeeId, int projectId);
}