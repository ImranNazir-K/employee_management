/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dbconnection.DbConnection;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;

/**
 * class Executes all the queries to Database to Insert, Update,
 * Delete, Retrieve Projects in the Database.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public interface ProjectDAO {

    /**
     * Inserts Projects into the Database.
     *
     * @param project as Project object.
     *
     * @return count of the Rows affected after execution of Queries
     * as int.
     */
    int insertProject(Project project);

    /**
     * Retrives All the projects available in the Database.
     *
     * @return Project List as List<Project>
     */
    List<Project> getAllProjects();

    /**
     * Retrives particular project from the Database.
     *
     * @param projectId as int.
     *
     * @return project as Project object.
     */
    Project getParticularProject(int projectId);

    /**
     * updates the projects.
     *
     * @param project as Project object.
     *
     * @return count of the Rows affected after execution of Queries.
     * as int.
     */
    int updateProject(Project project);

    /**
     * Deletes All the projects in the database.
     *
     * @return number of rows affeted in the database as int. 
     */
    int deleteAllProjects();

    /**
     * Deletes particular project in the database.
     *
     * @param projectId as int.
     *
     * @return number of rows affeted in the database as int.
     */
    int deleteParticularProject(int projectId);

    /**
     * gets All the projects of that particular employee.
     *
     * @param employeeId as int.
     * @param connection as Connection object.
     */
    List<Project> getProjectList(int employeeId, Connection connection);
}