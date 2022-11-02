/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dbconnection.DbConnection;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;
/**
 * {@inheritDoc}
 */
public class ProjectDAOImpl implements ProjectDAO {

    /**
     * Empty Constructor.
     */
    public ProjectDAOImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertProject(Project project) {
        int rowsAffected = 0;
        String query = "INSERT INTO project(name, domain, description) VALUES(?, ?, ?)";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectDomain());
            preparedStatement.setString(3, project.getProjectDescription());

            rowsAffected = preparedStatement.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<Project>();
        Project project = null;

        String query = "SELECT * FROM project";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                project = new Project(resultSet.getInt("projectId"),
                        resultSet.getString("projectName"),
                        resultSet.getString("domain"),
                        resultSet.getString("description"));
                projectList.add(project);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return projectList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getParticularProject(int projectId) {
        Project project = null;

        String query = "SELECT * FROM project WHERE id = ?";

        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                project = new Project(resultSet.getInt("ProjectId"),
                        resultSet.getString("projectName"),
                        resultSet.getString("domain"),
                        resultSet.getString("description"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateProject(Project project) {
        int rowsAffected = 0;
        String query = "UPDATE project SET name = ?, domain = ?, description = ? WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectDomain());
            preparedStatement.setString(3, project.getProjectDescription());
            preparedStatement.setLong(4, project.getProjectId());

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
    public int deleteAllProjects() {
        int rowsAffected = 0;
        String query = "TRUNCATE project";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }         
        return rowsAffected;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int deleteParticularProject(int projectId) {
        int rowsAffected = 0;
        String query = "DELETE from project where Id = ?";
        
        try (Connection connection = DbConnection.getConnectionInstance()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projectId);
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
    public List<Project> getProjectList(int employeeId,
            Connection connection) {
        List<Project> projectList = new ArrayList<Project>();
        Project project;
        String query = "select e.*, p.projectId, p.projectName, p.domain,"
                + "p.description from employees as e inner join "
                + "employee_project as ep on e.id = ep.employeeId inner"
                + " join project as p on ep.projectid = p.projectId where"
                + " e.id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();
            

            while (result.next()) {
                project = new Project(result.getInt("projectId"),
                        result.getString("projectName"),
                        result.getString("domain"),
                        result.getString("description"));

                projectList.add(project);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return projectList;
    }

}