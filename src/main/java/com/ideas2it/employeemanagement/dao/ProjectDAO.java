
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Interface for performing insert, update, delete, retrieve projects in the
 * database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
public interface ProjectDAO {

    /**
     * Inserts the project into the database.
     *
     * @param project as Project object.
     *
     * @return generated project id as int.
     * 
     * @throws EMSException if error occurred in database
     */
    int createProject(Project project) throws EMSException;

    /**
     * Retrieves All the projects from the Database.
     *
     * @return all the projects as List<Project>
     * 
     * @throws EMSException if error occurred in database
     */
    List<Project> getAllProjects() throws EMSException;

    /**
     * Retrives the particular project from the Database.
     *
     * @param projectId as int.
     * 
     * @throws EMSException if error occurred in database
     */
    Project getParticularProject(int projectId) throws EMSException;

    /**
     * updates the project.
     *
     * @param project as Project object.
     * 
     * @throws EMSException if error occurred in database
     */
    void updateProject(Project project) throws EMSException;

    /**
     * Deletes all the projects in the database.
     * 
     * @throws EMSException if error occurred in database
     */
    void deleteAllProjects() throws EMSException;

    /**
     * Deletes the particular project in the database.
     *
     * @param projectId as int.
     * 
     * @throws EMSException if error occurred in database
     */
    void deleteParticularProject(int projectId) throws EMSException;
}