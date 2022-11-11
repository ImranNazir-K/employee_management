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

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * {@inheritDoc}
 */
public class ProjectServiceImpl implements ProjectService {

    private ProjectDAO projectDao = new ProjectDAOImpl();

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
    public int insertProject(ProjectDTO projectDto) {
        return projectDao.insertProject(Mapper.toProject(projectDto));
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
        List<ProjectDTO> projectDtoList = new ArrayList<ProjectDTO>();

        for (Project project : projectDao.getAllProjects()) {
            projectDtoList.add(Mapper.projectEmployeeToDto(project));
        }
        return  projectDtoList;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) {       
        return Mapper.projectEmployeeToDto(projectDao
                .getParticularProject(projectId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int projectId) {
        boolean isPresent = false;

        for (int id : projectDao.getAllProjectId()) {
            if (projectId == id) {
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
    public void updateProject(ProjectDTO projectDto) {
        projectDao.updateProject(Mapper.dtoToProjectEmployee(projectDto));
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
    public void deleteParticularProject(int projectId) {
        projectDao.deleteParticularProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeDbIsEmpty() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.isDbIsEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeIdExists(int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.isIdExists(employeeId);
    }

    /**
     * 
     */
    public List<EmployeeDTO> getAllEmployees() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getAllEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyAssigned(int projectId, int employeeId) {
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
    public EmployeeDTO getParticularEmployee(int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getParticularEmployee(employeeId);
    }
}