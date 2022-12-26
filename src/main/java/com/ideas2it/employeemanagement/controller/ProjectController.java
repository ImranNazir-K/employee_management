
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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
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

    private ProjectService projectService;
    
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Creates project.
     *
     * @param projectDto as ProjectDTO object that contains an
     *        project to be created.
     *
     * @return the project Created.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @PostMapping
    private ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid
            ProjectDTO projectDto) throws EMSException {
        return projectService.createProject(projectDto);
    }

    /**
     * Gets all the projects from the Database.
     * 
     * @return List<ProjectDTO> all the projects as list.
     * 
     * @throws EMSException exception message of the employee 
     *      management system.
     */
    @GetMapping
    private ResponseEntity<List<ProjectDTO>> displayAllProjects()
            throws EMSException {
        return projectService.getProjects();
    }

    /**
     * Gets particular project from the Database.
     * 
     * @param projectId id of an project as int.
     * 
     * @return ProjectDTO object contains that particular project.
     * 
     * @throws EMSException exception message of the employee 
     *         management system.
     */
    @GetMapping("/{projectId}")
    private ResponseEntity<ProjectDTO> displayProject(
            @PathVariable int projectId) throws EMSException {
        return projectService.getProject(projectId);
    }

    /**
     * updates the project.
     * 
     * @param projectId id of an project as int.
     * @param projectDto as ProjectDTO object that contains an
     *        project.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @PutMapping("/{projectId}")
    private ResponseEntity<ProjectDTO> updateProject(
            @PathVariable int projectId, @RequestBody ProjectDTO projectDto)
            throws EMSException {
        return projectService.updateProject(projectId, projectDto);
    }

    /**
     * Adds the Employee to that particular project.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @PutMapping("/{projectId}/employees/{employeeId}")
    private ResponseEntity<ProjectDTO> assignProject(
            @PathVariable int projectId, @PathVariable int employeeId)
            throws EMSException {
        return projectService.assignEmployee(projectId, employeeId);
    }

    /**
     * Deletes that specific employee from that project.
     * 
     * @param employeeId of an employee as int.
     * @param projectId of an project as int.
     * 
     * @return the updated project.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @DeleteMapping("/{projectId}/employees/{employeeId}")
    private ResponseEntity<ProjectDTO> unAssignEmployee(
            @PathVariable int projectId, @PathVariable int employeeId)
            throws EMSException {
        return projectService.unAssignEmployee(employeeId, projectId);
    }

    /**
     * Deletes particular project.
     * 
     * @param projectId of an project as int.
     *
     * @return deleted message as String.
     * 
     * @throws EMSException exception message of the employee
     *         management system.
     */
    @DeleteMapping("/{projectId}")
    private String deleteParticularProject(
            @PathVariable int projectId) throws EMSException {
        projectService.deleteProject(projectId);
        return Constants.PROJECT_DELETED;
    }
}
