
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

import org.springframework.http.ResponseEntity;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;

/**
 * Interface for a class that validates all input given by users 
 *      Does Operations
 * like create, update, display, delete projects.
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
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<ProjectDTO> createProject(ProjectDTO projectDto)
            throws EMSException;

    /**
     * Displays all the projects.
     * 
     * @return all the projects as list from the database as
     *         List<ProjectDTO>.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<List<ProjectDTO>> getProjects() throws EMSException;

    /**
     * Displays particular project.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ProjectDTO object contains that particular project.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<ProjectDTO> getProject(int projectId) throws EMSException;

    /**
     * Deletes particular project.
     * 
     * @param projectId of an project as int.
     *
     * @return deleted message as String.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<String> deleteProject(int projectId) throws EMSException;

    /**
     * updates the project.
     * 
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    ResponseEntity<ProjectDTO> updateProject(int projectId, 
            ProjectDTO projectDto) throws EMSException;

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
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> assignEmployee(int projectId, int employeeId)
             throws EMSException;

    /**
     * checks the whether that particular project and employee are
     * available to add that employee from that particular
     * project.
     * 
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> assign(int projectId, int employeeId)
            throws EMSException;
    
    /**
     * Adds that specific employee from the project.
     * 
     * @param projectId of an project as int.
     * @param employee contains an employee as EmployeeDTO object.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> assignEmployeeToProject(int projectId,
            EmployeeDTO employee) throws EMSException;
    
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
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> unAssignEmployee(int employeeId, int projectId)
             throws EMSException;
    

    /**
     * checks the whether that particular project and employee are
     * available and remove that employee from that particular
     * project.
     *      
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> unAssign(int projectId, int employeeId)
            throws EMSException;
    /**
     * Deletes that specific employee from the project.
     * 
     * @param projectId of an project as int.
     * @param employee contains an employee as EmployeeDTO object.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
     ResponseEntity<ProjectDTO> unAssignEmployeeFromProject(int projectId,
            EmployeeDTO employeeDto) throws EMSException;
    
    /**
     * checks whether the employee is already assigned to that
     * project or not.
     *
     * @param projectId of an project as int.
     * @param employeeId of an employee as int.
     * 
     * @return true if the employee is already assigned.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    boolean isEmployeeAlreadyAssigned(int projectId, int employeeId)
            throws EMSException;

    /**
     * Checks whether the particular Employee is available are not.
     * 
     * @param employeeId of an employee.
     * 
     * @return  true if the Employee is available
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    boolean isEmployeeAvailable(int employeeId) throws EMSException;

    /**
     * Gets the particular project for the employee if available.
     * 
     * @param projectId of an project.
     * 
     * @return a project as ProjectDTO object if available.
     */
    ProjectDTO getParticularProject(int projectId);
    
    /**
     * Checks whether the prrjects are available are not.
     * 
     * @return true if the projects are available.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    boolean isProjectsAvailable() throws EMSException;
}