
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * performs operations like create, Display, Update, Delete and to
 * validate projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class ProjectController {

    private static final ProjectService projectService = new ProjectServiceImpl();

    public ProjectController() {
    }

    /**
     * Validates the name of a project.
     *
     * @param projectName as String.
     *     
     * @returns true if the validation of the project name is true.
     */
    public boolean validateProjectName(String projectName) {
        return projectService.validateProjectName(projectName);
    }

    /**
     * Validates the domain of a project.
     *
     * @param projectDomain as String.
     *     
     * @returns true if the validation of the project domain is true.
     */
    public boolean validateProjectDomain(String projectDomain) {
        return projectService.validateProjectDomain(projectDomain);
    }

    /**
     * Validates the description of a project.
     *
     * @param projectDescription as String.
     *     
     * @returns true if the validation of the project description is 
     * true.
     */
    public boolean validateProjectDescription(String projectDescription) {
        return projectService.validateProjectDescription(projectDescription);
    }

    /**
     * Inserts project into the database.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return generated project Id as int.
     */
    public int insertProject(ProjectDTO projectDto) throws EMSException {
        return projectService.insertProject(projectDto);
    }

    /**
     * Checks whether the database is empty.
     *
     * @return true if the database is empty.
     */
    public boolean isDbIsEmpty() throws EMSException {
        return projectService.isDbIsEmpty();
    }

    /**
     * Retrieves all the projects from the database.
     *
     * @return all the projects as List<ProjectDTO>.
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return projectService.getAllProjects();
    }

    /** 
     * Retrieves particular project from the database.
     *
     * @param projectId as int.
     *
     * @return particular project as ProjectDTO object.
     */
    public ProjectDTO getParticularProject(int projectId) throws EMSException {
        return projectService.getParticularProject(projectId);
    }

    /**
     * Checks whether that particular Id is present in the Database.
     *
     * @param projectId as int.
     *
     * @return true if the Database contains that particular 
     * project Id.
     */
    public boolean isIdExists(int projectId) throws EMSException {
        return projectService.isIdExists(projectId);
    }

    /**
     * Updates the project.
     *
     * @param projectDto as ProjectDTO object. 
     *
     * @return true if the project is updated.
     */
    public void updateProject(ProjectDTO projectDto) throws EMSException {
        projectService.updateProject(projectDto);
    }

    /**
     * Deletes All project in the database.
     */
    public void deleteAllProjects() throws EMSException {
        projectService.deleteAllProjects();
    }

    /**
     * Deletes the particular employee user wants to delete.
     *
     * @param projectId as int.
     *
     * @return true if the particular project was deleted.
     */
    public void deleteParticularProject(int projectId) throws EMSException {
        projectService.deleteParticularProject(projectId);
    }

    /**
     * checks whether the employee id is present in the database.
     *
     * @param employeeId as int.
     *
     * @return true if the Database contains that particular 
     * employee Id.
     */
    public boolean isEmployeeIdExists(int employeeId) throws EMSException {
        return projectService.isEmployeeIdExists(employeeId);
    }

    /**
     * Checks whether the employee database is empty.
     *
     * @return true if the database is Empty.
     */
    public boolean isEmployeeDbIsEmpty() throws EMSException {
        return projectService.isEmployeeDbIsEmpty();
    }

    /**
     * Retrieves all the employees from the database.
     *
     * @return all the employees as List<EmployeeDTO>.
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return projectService.getAllEmployees();
    }

    /**
     * Checks the database whether the employee is already assigned
     * or not.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the project is assigned to an employee.
     */
    public boolean isAlreadyAssigned(int projectId, int employeeId)
        throws EMSException {
        return projectService.isAlreadyAssigned(projectId, employeeId);
    }

    /** 
     * Retrieves the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as EmployeeDTO object.
     */
    public EmployeeDTO getParticularEmployee(int employeeId) 
            throws EMSException {
        return projectService.getParticularEmployee(employeeId);
    }
}