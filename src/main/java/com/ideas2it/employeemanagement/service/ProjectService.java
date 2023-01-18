
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

import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;

/**
 * Interface that validates all input given by users to
 *      do Operations like create, update, display, delete 
 *      projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
public interface ProjectService {

    /**
     * Creates project.
     *
     * @param projectDto as ProjectDTO object that contains an
     *        project to be created.
     *
     * @return the project Created.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    ProjectDTO createProject(ProjectDTO projectDto)
            throws EmployeeManagementSystemException;

    /**
     * Displays all the projects.
     * 
     * @return all the projects as list from the database as
     *         List<ProjectDTO>.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    List<ProjectDTO> getProjects() throws EmployeeManagementSystemException;

    /**
     * Displays particular project.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ProjectDTO object contains that particular project.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    ProjectDTO getProjectById(int projectId) throws
    		EmployeeManagementSystemException;

    /**
     * Deletes particular project.
     * 
     * @param projectId of an project as int.
     *
     * @return deleted message as String.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    String deleteProject(int projectId) throws
    		EmployeeManagementSystemException;

    /**
     * updates the project.
     * 
     * @param projectDto as ProjectDTO object contains an Project.
     * 
     * @return the updated project.
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    ProjectDTO updateProject(ProjectDTO projectDto) throws
    		EmployeeManagementSystemException;

    /**
     * checks the whether the projects and employees are available
     * to add that employee from that particular
     * project. 
     * 
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return the updated project.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
     ProjectDTO assignEmployee(int projectId, int employeeId)
             throws EmployeeManagementSystemException;
    
    /**
     * checks the whether the projects and employees are available
     * to remove that employee from that particular
     * project. 
     * 
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return the updated project.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
     ProjectDTO unAssignEmployee(int employeeId, int projectId)
             throws EmployeeManagementSystemException;
    
    /**
     * checks whether the employee is already assigned to that
     * project or not.
     *
     *@param projectDto as ProjectDTO object contains an Project.
     * @param employeeId of an employee as int.
     * 
     * @return true if the employee is already assigned.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system. 
     */
    boolean isEmployeeAlreadyAssigned(ProjectDTO project, int employeeId)
            throws EmployeeManagementSystemException;
}