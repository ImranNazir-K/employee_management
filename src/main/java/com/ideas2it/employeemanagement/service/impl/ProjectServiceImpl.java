
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

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.mapper.Mapper;
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
    public int createProject(ProjectDTO projectDto) throws EMSException {
        return projectDao.createProject(Mapper.toProject(projectDto));
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
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) throws EMSException {
        return Mapper.projectEmployeeToDto(
                projectDao.getParticularProject(projectId));
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
    public void deleteAllProjects() throws EMSException {
        projectDao.deleteAllProjects();
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
    public EmployeeDTO getParticularEmployee(int employeeId)
            throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getParticularEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int projectId) throws EMSException {
        boolean isExists = false;

        if (null != projectDao.getParticularProject(projectId)) {
            isExists = true;
        }
        return isExists;
    }

    /**
     * {@inheritDoc}
     * @throws EMSException 
     */
    @Override
    public List<EmployeeDTO> getEmployees() throws EMSException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getAllEmployees();
    }

    /**
     * {@inheritDoc}
     * @throws EMSException 
     */
    @Override
    public boolean isDbIsEmpty() throws EMSException {
        boolean isEmpty = false;
        
        if (projectDao.getAllProjects().isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
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

        if (!(project.getEmployees().isEmpty())) {
            for (EmployeeDTO employee : project.getEmployees()) {
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
    public boolean isEmployeeDbIsEmpty() throws EMSException {
        return false;
    }
}