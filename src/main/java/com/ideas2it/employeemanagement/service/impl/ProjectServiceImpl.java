
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLoggerFactory;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.repository.ProjectRepository;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * Validates all input given by users. Stores the projects in the 
 *      Database.
 * Does operations like Create, update, Display, Delete projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private EmployeeServiceImpl employeeService;
    private ProjectRepository projectRepository;
    
    public ProjectServiceImpl(ProjectRepository projectRepository,
            @Lazy EmployeeServiceImpl employeeService) {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }
    
    Logger log = EMSLoggerFactory.getFactory(ProjectService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid
            ProjectDTO projectDto) throws EMSException {
        log.info("Request got for creating a project");
        log.info(Constants.PROJECT_CREATED);
        return new ResponseEntity<ProjectDTO>(Mapper.projectEmployeeToDto
                (projectRepository.save(Mapper.toProject(projectDto))),
                HttpStatus.CREATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<ProjectDTO>> getProjects() throws EMSException {
        List<ProjectDTO> projectDtos = new ArrayList<ProjectDTO>();
        List<Project> projects = projectRepository.findAll();

        log.info("Request got for Displaying All Project");
        
        if (!(projects.isEmpty())) {
            
            for (Project project : projects) {
                projectDtos.add(Mapper.projectEmployeeToDto(project));
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProjectDTO>>(projectDtos,
                HttpStatus.OK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> getProject(int projectId)
            throws EMSException {
        log.info("Request got for displaying Particular Project");
        
        if (!(projectRepository.findAll().isEmpty())) {
            
            if (projectRepository.existsById(projectId)) {
                return new ResponseEntity<ProjectDTO>(Mapper
                        .projectEmployeeToDto(projectRepository
                        .findById(projectId).get()),
                        HttpStatus.OK);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> deleteProject(int projectId)
            throws EMSException {
        log.info("Request got to delete Particular Project");
        
        if (!(projectRepository.findAll().isEmpty())) {

            if (projectRepository.existsById(projectId)) {
                projectRepository.deleteById(projectId);
                log.info(Constants.PROJECT_DELETED);
                throw new EMSException(Constants.PROJECT_DELETED,
                        HttpStatus.NO_CONTENT);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> updateProject(int projectId, @RequestBody
            @Valid ProjectDTO projectDto) throws EMSException {
        if (!(projectRepository.findAll().isEmpty())) {

            if (projectRepository.existsById(projectId)) {
                ProjectDTO project = Mapper.projectEmployeeToDto(
                        projectRepository.findById(projectId).get());
                projectDto.setEmployees(project.getEmployees());
                projectDto.setProjectId(projectId);
                log.info(Constants.PROJECT_UPDATED);
                return new ResponseEntity<ProjectDTO>(Mapper
                        .projectEmployeeToDto(projectRepository.save(Mapper
                        .dtoToProjectEmployee(projectDto))), HttpStatus.OK);
            } else {
                log.warn(Constants.PROJECT_ID_NOT_FOUND);
                throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> assignEmployee(int projectId,
            int employeeId) throws EMSException {
        if (!(projectRepository.findAll().isEmpty())) {

            if (employeeService.isEmployeesAvailable()) {
                return assign(projectId, employeeId);
            } else {
                log.info(Constants.NO_EMPLOYEES);
                throw new EMSException(Constants.NO_EMPLOYEES,
                        HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> assign(int projectId,
            int employeeId) throws EMSException {
        if (projectRepository.existsById(projectId)) {
            EmployeeDTO employee = employeeService.getParticularEmployee
                    (employeeId);
            
            if (null != employee) {
                return assignEmployeeToProject(projectId, employee);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn(Constants.PROJECT_ID_NOT_FOUND);
            throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> assignEmployeeToProject(int projectId,
            EmployeeDTO employee) throws EMSException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        if (isEmployeeAlreadyAssigned(projectId, employee.getEmployeeId())) {
            log.warn(Constants.EMPLOYEE_ALREADY_ASSIGNED);
            throw new EMSException(Constants.EMPLOYEE_ALREADY_ASSIGNED,
                    HttpStatus.BAD_REQUEST);
        } else {
            ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
                    .findById(projectId).get());
            employees.add(employee);
            project.setEmployees(employees);
            log.info(Constants.EMPLOYEE_ASSIGNED);
            return new ResponseEntity<ProjectDTO>(Mapper.projectEmployeeToDto
                    (projectRepository.save(Mapper.dtoToProjectEmployee
                    (project))), HttpStatus.OK);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> unAssignEmployee(int employeeId,
            int projectId)
            throws EMSException {
        if (!(projectRepository.findAll().isEmpty())) {

            if (employeeService.isEmployeesAvailable()) {
               return unAssign(projectId, employeeId);
            } else {
                log.info(Constants.NO_EMPLOYEES);
                throw new EMSException(Constants.NO_EMPLOYEES,
                        HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(Constants.NO_PROJECTS);
            throw new EMSException(Constants.NO_PROJECTS,
                    HttpStatus.NO_CONTENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> unAssign(int projectId, int employeeId)
            throws EMSException {
        if (projectRepository.existsById(projectId)) {
            EmployeeDTO employee = employeeService
                    .getParticularEmployee(employeeId);

            if (null != employee) {
                return unAssignEmployeeFromProject(projectId, employee);
            } else {
                log.warn(Constants.EMPLOYEE_ID_NOT_FOUND);
                throw new EMSException(Constants.EMPLOYEE_ID_NOT_FOUND,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn(Constants.PROJECT_ID_NOT_FOUND);
            throw new EMSException(Constants.PROJECT_ID_NOT_FOUND,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ProjectDTO> unAssignEmployeeFromProject
            (int projectId, EmployeeDTO employeeDto) throws EMSException {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();
        
        if (isEmployeeAlreadyAssigned(projectId, employeeDto.getEmployeeId())) {
            ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
                    .findById(projectId).get());
            employees = project.getEmployees();

            for (EmployeeDTO employee : employees) {

                if (employeeDto.getEmployeeId() == employee.getEmployeeId()) {
                    employees.remove(employee);
                    break;
                }
            }
            project.setEmployees(employees);
            log.info(Constants.EMPLOYEE_UNASSIGNED);
            return new ResponseEntity<ProjectDTO>(Mapper.projectEmployeeToDto
                    (projectRepository.save(Mapper.dtoToProjectEmployee
                    (project))), HttpStatus.OK);
        } else {
            log.warn(Constants.EMPLOYEE_NOT_ASSIGNED);
            throw new EMSException(Constants.EMPLOYEE_NOT_ASSIGNED,
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeAvailable(int employeeId) throws EMSException {
        boolean isAvailable = false;

        if (null != employeeService.getEmployee(employeeId)) {
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmployeeAlreadyAssigned(int projectId, int employeeId)
            throws EMSException {
        boolean isAlreadyAssigned = false;
        ProjectDTO project = Mapper.projectEmployeeToDto(projectRepository
                .findById(projectId).get());

        for (EmployeeDTO employee : project.getEmployees()) {

            if (employeeId == employee.getEmployeeId()) {
                isAlreadyAssigned = true;
                break;
            }
        }
        return isAlreadyAssigned;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) {
        if (projectRepository.existsById(projectId)) {
            return Mapper.projectEmployeeToDto(projectRepository
                    .findById(projectId).get());
        } else {
            return null;
        }
    }
    
    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean isProjectsAvailable() {
        return !projectRepository.findAll().isEmpty();
    }
}