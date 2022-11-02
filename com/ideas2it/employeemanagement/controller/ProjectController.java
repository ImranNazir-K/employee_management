/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.util.List;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImpl;

/**
 * An object is created for a public class Project Service.
 * passes the arguments sent by Project view class to Employee
 * Service class to create, Display, Update, Delete and to validate
 * employees.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class ProjectController {

    private ProjectServiceImpl projectService = new ProjectServiceImpl();

    /**
     * Empty Constructor.
     */
    public ProjectController() {
    }

    /**
     * Inserts Projects into the Database .
     */
    public boolean insertProject(ProjectDTO projectDto) {
        return projectService.insertProject(projectDto);
    }

    /**
     * 
     */
    public boolean validateProjectName(String projectName) {
        return projectService.validateProjectName(projectName);
    }

    /**
     * 
     */
    public boolean validateProjectDomain(String projectDomain) {
        return projectService.validateProjectDomain(projectDomain);
    }

    /**
     * 
     */
    public boolean validateProjectDescription(String projectDescription) {
        return projectService.validateProjectDescription(projectDescription);
    }

    /**
     * checks whether the Database is empty or not.
     *
     * @return true if the Database is Empty.
     *
     * @return false if the Database is not Empty.
     */
    public boolean isDbIsEmpty() {
        return projectService.isDbIsEmpty();
    }

    /**
     * Retrieves all the employees from the Database as an ArrayList.
     *
     * @return ArrayList that contains all the employees.
     */
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    /** 
     * Retrieves particular employee from the Database.
     *
     * @return project. 
     */
    public ProjectDTO getParticularProject(int projectId) {
        return projectService.getParticularProject(projectId);
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
    public boolean isIdExists(int projectId) {
        return projectService.isIdExists(projectId);
    }

    /**
     * Updates all the fields of an Projects.
     */
    public boolean updateProject(ProjectDTO projectDto) {
        return projectService.updateProject(projectDto);
    }

    /**
     * delete All Employees in the Database.
     */
    public boolean deleteAllProjects() {
        return projectService.deleteAllProjects();
    }

    /**
     * deletes the particular employee user wants to delete.
     */
    public boolean deleteParticularProject(int projectId) {
        return projectService.deleteParticularProject(projectId);
    }
}