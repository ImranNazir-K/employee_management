/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dbconnection.DbConnection;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;


/**
 * class Executes all the queries to Database to Insert, Update,
 * Delete, Retrieve Employees in the Database.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private Mapper mapper = new Mapper();

    /**
     * empty constructor.
     */
    public EmployeeDAOImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertEmployee(Employee employee) {
        int rowsAffected = 0;
        String query = "INSERT INTO employees(name, emailId, contactNumber,"
                + "salary, Date_of_birth) values(?, ?, ?, ?, ?)"; 

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeMailId());
            statement.setLong(3, employee.getEmployeeContactNumber());
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setDate(5, Date.valueOf(employee
                    .getEmployeeDateOfBirth()));

            rowsAffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } 
        return rowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<Employee>();
        ProjectDAOImpl projectDao = new ProjectDAOImpl();
        Employee employee;
        
        String query = "SELECT * FROM employees";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                employee = new Employee(result.getInt("id"),
                        result.getString("name"),
                        result.getString("emailId"),
                        result.getLong("contactNumber"),
                        result.getDouble("salary"),
                        result.getDate("Date_of_Birth").toLocalDate(),
                        projectDao.getProjectList(result.getInt("id"),
                                connection));

                employeeList.add(employee);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employeeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getParticularEmployee(int employeeId) {
        ProjectDAOImpl projectDao = new ProjectDAOImpl();
        Employee employee = null;

        String query = "SELECT * FROM employees where Id = ?";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                employee = new Employee(result.getInt("id"),
                        result.getString("name"),
                        result.getString("emailId"),
                        result.getLong("contactNumber"),
                        result.getDouble("salary"),
                        result.getDate("Date_of_Birth").toLocalDate(),
                        projectDao.getProjectList(employeeId, connection));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateEmployee(Employee employee) {
        int rowsAffected = 0;
        String query = "UPDATE employees SET name = ?, emailId = ?,"
                + "contactNumber = ?, salary = ?, Date_of_Birth = ?"
                + " WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeMailId());
            statement.setLong(3, employee.getEmployeeContactNumber());
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setDate(5, Date.valueOf(employee
                    .getEmployeeDateOfBirth()));
            statement.setDouble(6, employee.getEmployeeId());

            rowsAffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }         
        return rowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getEmployees(int employeeId) {
        List<Employee> employeeList = new ArrayList<Employee>();
        
        String query = "SELECT * FROM employees where id != ?";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Employee employee = new Employee(result.getInt("id"),
                        result.getString("name"), result.getString("emailId"),
                        result.getLong("contactNumber"),
                        result.getDouble("salary"),
                        result.getDate("Date_of_Birth").toLocalDate());
                employeeList.add(employee);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }     
        return employeeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteAllEmployees() {
        int rowsAffected = 0;
        String query = "TRUNCATE employee_project";
        String deleteEmployeeQuery = "DELETE from employees";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(deleteEmployeeQuery);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteParticularEmployee(int employeeId) {
        int rowsAffected = 0;
        String query = "DELETE FROM employee_project WHERE employeeID = ?";
        String deleteEmployeeQuery = "DELETE FROM employees WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();

            PreparedStatement preparedStatement = connection
                    .prepareStatement(deleteEmployeeQuery);
            preparedStatement.setInt(1, employeeId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }        
        return rowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int assignEmployeeToProject(int employeeId, int projectId) {
        int rowsAffected = 0;
        String query = "INSERT INTO employee_project(employeeId,"
                + "projectId) values(?, ?)";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.setInt(2, projectId);

            rowsAffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rowsAffected;
    }
}   