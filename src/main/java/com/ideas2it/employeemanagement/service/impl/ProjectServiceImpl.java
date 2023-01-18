
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementSystemLoggerFactory;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 *  Class to perform CRUD operations for project.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = EmployeeManagementSystemLoggerFactory
    		.getFactory(ProjectService.class);
    
    private final EmployeeServiceImpl employeeService;
    private final ProjectRepository projectRepository;
    
    public ProjectServiceImpl(ProjectRepository projectRepository,
            @Lazy EmployeeServiceImpl employeeService) {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO createProject(@RequestBody @Valid
            ProjectDTO projectDto) throws EmployeeManagementSystemException {
        log.info(Constants.PROJECT_CREATED);
        return Mapper.projectEmployeeToDto
                (projectRepository.save(Mapper.toProject(projectDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getProjects() throws
    		EmployeeManagementSystemException {
        List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();
        List<Project> projects = projectRepository.findAll();
        
        if (!(projects.isEmpty())) {
            
            for (Project project : projects) {
                projectDtos.add(Mapper.projectEmployeeToDto(project));
            }
        }
        return projectDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getProjectById(int projectId)
            throws EmployeeManagementSystemException {
        ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
        					.findById(projectId).get());
        
        if (null != project) {
        	return project;
        } else {
        	log.warn(Constants.PROJECT_ID_NOT_FOUND);
        	throw new EmployeeManagementSystemException(Constants
        			.PROJECT_ID_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteProject(int projectId)
            throws EmployeeManagementSystemException {
        if (projectRepository.existsById(projectId)) {
        	projectRepository.deleteById(projectId);
        	log.info(Constants.PROJECT_DELETED);
        	return Constants.PROJECT_DELETED;
        } else {
        	log.warn(Constants.PROJECT_ID_NOT_FOUND);
        	throw new EmployeeManagementSystemException(Constants
        			.PROJECT_ID_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO updateProject(ProjectDTO projectDto)
    		throws EmployeeManagementSystemException {
    	ProjectDTO project = Mapper.projectEmployeeToDto(
                projectRepository.findById(projectDto.getProjectId()).get());
    	
    	if (null != project) {
    		projectDto.setEmployees(project.getEmployees());
    		log.info(Constants.PROJECT_UPDATED);
            return Mapper.projectEmployeeToDto(projectRepository.save(Mapper
                    .dtoToProjectEmployee(projectDto)));
    	} else {
    		log.warn(Constants.PROJECT_ID_NOT_FOUND);
            throw new EmployeeManagementSystemException(Constants
            		.PROJECT_ID_NOT_FOUND);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO assignEmployee(int projectId,
            int employeeId) throws EmployeeManagementSystemException {
    	ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
                .findById(projectId).get());

        if (null != project) {
            EmployeeDTO employee = employeeService.getEmployeeById
                    (employeeId);
            
            if (null != employee) {
                return assignEmployeeToProject(project, employee);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EmployeeManagementSystemException(Constants
                		.EMPLOYEE_ID_NOT_FOUND);
            }
        } else {
            log.warn(Constants.PROJECT_ID_NOT_FOUND);
            throw new EmployeeManagementSystemException(Constants
            		.PROJECT_ID_NOT_FOUND);
        }
    }

    private ProjectDTO assignEmployeeToProject(ProjectDTO project,
            EmployeeDTO employee) throws EmployeeManagementSystemException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        if (isEmployeeAlreadyAssigned(project, employee.getEmployeeId())) {
            log.warn(Constants.EMPLOYEE_ALREADY_ASSIGNED);
            throw new EmployeeManagementSystemException(Constants
            		.EMPLOYEE_ALREADY_ASSIGNED);
        } else {
            employees.add(employee);
            project.setEmployees(employees);
            log.info(Constants.EMPLOYEE_ASSIGNED);
            return Mapper.projectEmployeeToDto
                    (projectRepository.save(Mapper.dtoToProjectEmployee
                    (project)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO unAssignEmployee(int employeeId,
            int projectId)
            throws EmployeeManagementSystemException {
    	ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
                .findById(projectId).get());
    	
        if (null != project) {
            EmployeeDTO employee = employeeService
                    .getEmployeeById(employeeId);

            if (null != employee) {
                return unAssignEmployeeFromProject(project, employee);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EmployeeManagementSystemException(Constants
                		.EMPLOYEE_ID_NOT_FOUND);
            }
        } else {
            log.warn(Constants.PROJECT_ID_NOT_FOUND);
            throw new EmployeeManagementSystemException(Constants
            		.PROJECT_ID_NOT_FOUND);
        }
    }

    private ProjectDTO unAssignEmployeeFromProject
            (ProjectDTO project, EmployeeDTO employeeDto) throws
            EmployeeManagementSystemException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();
        
        if (isEmployeeAlreadyAssigned(project, employeeDto
        		.getEmployeeId())) {
            employees = project.getEmployees();

            for (EmployeeDTO employee : employees) {

                if (employeeDto.getEmployeeId() == employee.getEmployeeId()) {
                    employees.remove(employee);
                    break;
                }
            }
            project.setEmployees(employees);
            log.info(Constants.EMPLOYEE_UNASSIGNED);
            return Mapper.projectEmployeeToDto
                    (projectRepository.save(Mapper.dtoToProjectEmployee
                    (project)));
        } else {
            log.warn(Constants.EMPLOYEE_NOT_ASSIGNED);
            throw new EmployeeManagementSystemException(Constants
            		.EMPLOYEE_NOT_ASSIGNED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeAlreadyAssigned(ProjectDTO project,
    		int employeeId) throws EmployeeManagementSystemException {
        boolean isAlreadyAssigned = false;

        for (EmployeeDTO employee : project.getEmployees()) {

            if (employeeId == employee.getEmployeeId()) {
                isAlreadyAssigned = true;
                break;
            }
        }
        return isAlreadyAssigned;
    }
}