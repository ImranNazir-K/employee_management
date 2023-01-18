
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EmployeeManagementSystemException;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * performs operations like create, Display, Update, Delete for 
 *      projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Creates project.
     *
     * @param projectDto as ProjectDTO object that contains an
     *        project to be created.
     *
     * @return the project Created as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @PostMapping
    private ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid
            ProjectDTO projectDto) throws EmployeeManagementSystemException {
        return new ResponseEntity<ProjectDTO>(projectService.createProject
        		(projectDto), HttpStatus.OK);
    }

    /**
     * Gets all the projects from the Database.
     * 
     * @return ResponseEntity<List<ProjectDTO>> all the projects as
     * 		Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *      of the employee management system.
     */
    @GetMapping
    private ResponseEntity<List<ProjectDTO>> displayAllProjects()
            throws EmployeeManagementSystemException {
        return new ResponseEntity<List<ProjectDTO>>(projectService
        		.getProjects(), HttpStatus.OK);
    }

    /**
     * Gets particular project from the Database.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ResponseEntity<ProjectDTO> object contains that
     * 		particular project as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @GetMapping("/{projectId}")
    private ResponseEntity<ProjectDTO> displayProject(
            @PathVariable int projectId) throws
            EmployeeManagementSystemException {
        return new ResponseEntity<ProjectDTO>(projectService
        		.getProjectById(projectId), HttpStatus.OK);
    }

    /**
     * updates the project.
     * 
     * @param projectId id of an project as int.
     * @param projectDto as ProjectDTO object that contains an
     *        project.
     * 
     * @return the updated project as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @PutMapping
    private ResponseEntity<ProjectDTO> updateProject(@RequestBody
    		ProjectDTO projectDto) throws EmployeeManagementSystemException {
        return new ResponseEntity<ProjectDTO>(projectService.updateProject
        		(projectDto), HttpStatus.OK);
    }

    /**
     * Adds the Employee to that particular project.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated project as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @PutMapping("/{projectId}/employees/{employeeId}")
    private ResponseEntity<ProjectDTO> assignProject(
            @PathVariable int projectId, @PathVariable int employeeId)
            throws EmployeeManagementSystemException {
        return new ResponseEntity<ProjectDTO>(projectService.assignEmployee
        		(projectId, employeeId), HttpStatus.OK);
    }

    /**
     * Deletes that specific employee from that project.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated project as Response Entity.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @DeleteMapping("/{projectId}/employees/{employeeId}")
    private ResponseEntity<ProjectDTO> unAssignEmployee(
            @PathVariable int projectId, @PathVariable int employeeId)
            throws EmployeeManagementSystemException {
        return new ResponseEntity<ProjectDTO>(projectService.unAssignEmployee
        		(employeeId, projectId), HttpStatus.OK);
    }

    /**
     * Deletes particular project.
     * 
     * @param projectId of an project as int.
     *
     * @return deleted message as ResponseEntity<String>.
     * 
     * @throws EmployeeManagementSystemException exception message
     *         of the employee management system.
     */
    @DeleteMapping("/{projectId}")
    private ResponseEntity<String> deleteParticularProject(
            @PathVariable int projectId) throws
            EmployeeManagementSystemException {
        return new ResponseEntity<String>(projectService.deleteProject
        		(projectId), HttpStatus.OK);
    }
}