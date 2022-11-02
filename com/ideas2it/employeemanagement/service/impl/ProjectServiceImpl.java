/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * {@inheritDoc}
 */
public class ProjectServiceImpl implements ProjectService {

    private ProjectDAOImpl projectDao = new ProjectDAOImpl();

    /**
     * Empty Constructor.
     */
    public ProjectServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectName(String projectName) {
        return Pattern.matches("((([a-zA-Z0-9]{3,})(([ ])([a-zA-Z0-9])"
                + "{3,})?){1,})", projectName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectDomain(String projectDomain) {
        return Pattern.matches("((([A-Za-z]{1,}([ ]?)){1,}))((([.]?)"
                + "([a-zA-Z]{1})){1,})", projectDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectDescription(String projectDescription) {
        return Pattern.matches("((([A-Za-z]{1,}([ ]?)){1,}))((([.]?)"
                + "([a-zA-Z]{1})){1,})", projectDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertProject(ProjectDTO projectDto) {
        boolean isInserted = false;

        if (0 < (projectDao.insertProject(Mapper.toProject(projectDto)))) {
            isInserted = true;
        }
        return isInserted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDbIsEmpty() {
         return (getAllProjects().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getAllProjects() {
        return  Mapper.toProjectDtoList(projectDao.getAllProjects());
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) {
        return Mapper.toProjectDto(projectDao.getParticularProject(projectId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int projectId) {
        boolean isPresent = false;

        for (ProjectDTO project : getAllProjects()) {
            if (projectId == project.getProjectId()) {
                isPresent = true;
                break;
            } 
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(ProjectDTO projectDto) {
        boolean isUpdated = false;

        if (0 < (projectDao.updateProject(Mapper.toProject(projectDto)))) {
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllProjects() {
        boolean isDeleted = false;

        if (0 < (projectDao.deleteAllProjects())) {
            isDeleted = true;
        }
        return isDeleted; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteParticularProject(int projectId) {
        boolean isDeleted = false;

        if (0 < (projectDao.deleteParticularProject(projectId))) {
            isDeleted = true;
        }
        return isDeleted;
    }
}