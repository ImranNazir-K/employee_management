/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Interface for performing insert, update, delete, retrieve
 * projects in the database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public interface ProjectDAO {

    /**
     * Inserts the project into the database.
     *
     * @param project as Project object.
     *
     * @return generated project id as int.
     */
    int insertProject(Project project);

    /**
     * gets all the project id.
     *
     * @return list of project id as List<Integer>.
     */
    List<Integer> getAllProjectId();

    /**
     * Retrives All the projects from the Database.
     *
     * @return all the projects as List<Project>
     */
    List<Project> getAllProjects();

    /**
     * Retrives the particular project from the Database.
     *
     * @param projectId as int.
     *
     * @return particular project as Project object
     */
    Project getParticularProject(int projectId);

    /**
     * updates the projects.
     *
     * @param project as Project object.
     */
    void updateProject(Project project);

    /**
     * Deletes all the projects in the database.
     *
     * @return number of rows affected as int.
     */
    int deleteAllProjects();

    /**
     * Deletes the particular project in the database.
     *
     * @param projectId as int.
     */
    void deleteParticularProject(int projectId);
}