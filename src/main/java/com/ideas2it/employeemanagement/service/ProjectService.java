
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;

/**
 * contains methods to validate all input given by users. Does operations like
 * create, update, display, delete Projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public interface ProjectService {

    /**
     * Creates project.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return generated projectId as int.
     * 
     * @throws EMSException if error occurred in database.
     */
    int createProject(ProjectDTO projectDto) throws EMSException;

    /**
     * Displays all the projects.
     * 
     * @return List<ProjectDTO> all the projects as list.
     * 
     * @throws EMSException if error occurred in database.
     */
    List<ProjectDTO> getAllProjects() throws EMSException;

    /**
     * Displays particular project.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ProjectDTO object contains that particular project.
     * 
     * @throws EMSException if error occurred in databases
     */
    ProjectDTO getParticularProject(int projectId) throws EMSException;

    /**
     * Deletes particular project.
     * 
     * @param projectId id of an project as int.
     * 
     * @throws EMSException if error occurred in database
     */
    void deleteParticularProject(int projectId) throws EMSException;

    /**
     * Deletes all the projects.
     * 
     * @throws EMSException if error occurred in database
     */
    void deleteAllProjects() throws EMSException;

    /**
     * updates the project.
     * 
     * @param projectDto as ProjectDTO object.
     * 
     * @throws EMSException if error occurred in database
     */
    void updateProject(ProjectDTO projectDto) throws EMSException;

    /**
     * Gets the particular employee
     * 
     * @param employeeId id of an employee as int.
     * 
     * @return EmployeeDTO object contains that particular employee.
     * 
     * @throws EMSException if error occurred in database
     */
    EmployeeDTO getParticularEmployee(int employeeId) throws EMSException;

    /**
     * checks whether the id of an project is present in the database.
     * 
     * @param projectId id of an project as int.s
     * 
     * @return true if the id is present in the database.
     * 
     * @throws EMSException if error occurred in database
     */
    boolean isIdExists(int projectId) throws EMSException;

    /**
     * Gets all the employees.
     * 
     * @return List<EmployeeDTO> all the employees from the database.
     * 
     * @throws EMSException if error occurred in database.
     */
    List<EmployeeDTO> getEmployees() throws EMSException;

    /**
     * checks the project database whether the database is empty.
     *
     * @return true if the database is empty.
     * 
     * @throws EMSException if error occurred in database.
     */
    boolean isDbIsEmpty() throws EMSException;

    /**
     * Checks whether the employee database is empty or not.
     * 
     * @return true if the database is empty.
     *  
     * @throws EMSException if error occurred in database.
     */
    boolean isEmployeeDbIsEmpty() throws EMSException;


    /**
     * checks whether the project is already assigned to that
     * employee.
     *
     * @param employeeId id of an employee as int.
     * @param projectId id of an project as int.
     *
     * @return true if the project is assigned to an employee.
     * 
     * @throws EMSException  if error occurred in database.
     */
    boolean isAlreadyAssigned(int projectId, int employeeId)
            throws EMSException;


    /**
     * checks whether the employee id is present in the database.
     *
     * @param employeeId id of an employee as int.
     *
     * @return true if the Database contains that particular 
     * employee Id. 
     * 
     * @throws EMSException if error occurred in database.
     */
    boolean isEmployeeIdExists(int employeeId) throws EMSException;
}