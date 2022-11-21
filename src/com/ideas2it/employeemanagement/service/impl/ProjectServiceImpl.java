
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * {@inheritDoc}
 */
public class ProjectServiceImpl implements ProjectService {

    private static final ProjectDAO projectDao = new ProjectDAOImpl();

    public ProjectServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectName(String projectName) {
        return Pattern.matches(Constants.VALIDATE_PROJECT_NAME,
                projectName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectDomain(String projectDomain) {
        return Pattern.matches(Constants.VALIDATE_PROJECT_DOMAIN,
                projectDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateProjectDescription(String projectDescription) {
        return Pattern.matches(Constants.VALIDATE_PROJECT_DESCRIPTION,
                projectDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertProject(ProjectDTO projectDto) throws EMSException {
        return projectDao.insertProject(Mapper.toProject(projectDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDbIsEmpty() throws EMSException {
         return (getAllProjects().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getAllProjects() throws EMSException {
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();

        for (Project project : projectDao.getAllProjects()) {
            projects.add(Mapper.projectEmployeeToDto(project));
        }
        return  projects;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) throws EMSException {
        return Mapper.projectEmployeeToDto(projectDao
                .getParticularProject(projectId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int projectId) throws EMSException {
        boolean isPresent = false;

        if (null != getParticularProject(projectId)) {
            isPresent = true;
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProject(ProjectDTO projectDto) throws EMSException {
        projectDao.updateProject(Mapper.dtoToProjectEmployee(projectDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllProjects() throws EMSException {
        projectDao.deleteAllProjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularProject(int projectId) throws EMSException {
        projectDao.deleteParticularProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeDbIsEmpty() throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.isDbIsEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeIdExists(int employeeId) throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.isIdExists(employeeId);
    }

    /**
     * 
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getAllEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyAssigned(int projectId, int employeeId)
            throws EMSException {
        boolean isAlreadyAssigned = false;

        ProjectDTO project = getParticularProject(projectId);

        if (!(project.getEmployeeList().isEmpty())) {
            for (EmployeeDTO employee : project.getEmployeeList()) {
                if (employeeId == employee.getEmployeeId()) {
                    isAlreadyAssigned = true;
                    break;
                }
            }
        }
        return isAlreadyAssigned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getParticularEmployee(int employeeId)
                throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getParticularEmployee(employeeId);
    }
}