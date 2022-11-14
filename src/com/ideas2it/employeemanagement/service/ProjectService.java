/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;

/**
 * contains methods to validate all input given by users.
 * Does operations like create, update, display, delete Projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public interface ProjectService {

    /**
     * validates the name of the project.
     *
     * @param projectsName as String.
     *
     * @return true if the validation is true.
     */
    boolean validateProjectName(String projectName);

    /**
     * validates the project domain 
     *
     * @param projectDomain as String.
     *
     * @return true if the validation is true.
     */
    boolean validateProjectDomain(String projectDomain);

    /**
     * validates the project description
     *
     * @param projectDescription as String.
     *
     * @return true if the validation is true.
     */
    boolean validateProjectDescription(String projectDescription);


    /**
     * Inserts project into the database.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return generated projectId as int.
     */
    int insertProject(ProjectDTO projectDto);

    /**
     * checks the database whether the database is empty.
     *
     * @return true if the database is empty.
     */
    boolean isDbIsEmpty();

    /**
     * Retrieves all the projects from the database.
     *
     * @return all the projects as List<ProjectDTO>.
     */
    List<ProjectDTO> getAllProjects();

    /** 
     * Retrieves the particular project from the database.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return particular project as ProjectDTO object.
     */
    ProjectDTO getParticularProject(int projectId);

    /**
     * checks whether the Id is present in the database.
     *
     * @param projectId as int.
     *
     * @return true if the database contains that particular 
     * project Id.
     */
    boolean isIdExists(int projectId);

    /**
     * Updates the project.
     *
     * @param projectDto as ProjectDTO object.
     */
    void updateProject(ProjectDTO projectDto);

    /**
     * Deletes all the projects in the database.
     *
     * @return true if all the project is deleted.
     */
    boolean deleteAllProjects();

    /**
     * Deletes the particular project in the database.
     *
     * @param projectId as int.
     */
    void deleteParticularProject(int projectId);

    /**
     * checks whether the employee id is present in the database.
     *
     * @param employeeId as int.
     *
     * @return true if the Database contains that particular 
     * employee Id. 
     */
    boolean isEmployeeIdExists(int employeeId);

    /**
     * Checks whether the employee database is empty or not.
     */
    boolean isEmployeeDbIsEmpty();

    /**
     * Retrieves all the employees from the database.
     * 
     * @return all the employees as List<EmployeeDTO>.
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * checks whether the project is already assigned to that
     * employee.
     *
     * @param employeeId as int.
     * @param projectId as int.
     *
     * @return true if the project is assigned to an employee.
     */
    boolean isAlreadyAssigned(int projectId, int employeeId);

    /**
     * Gets the particular employee from the database.
     *
     * @param employeeId as int.
     *
     * @return particular employee as EmployeeDTO object.
     */
    EmployeeDTO getParticularEmployee(int employeeId);
}