/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.mapper.Mapper;

/**
 * contains methods to Validate all input given by users.
 * Stores the Projects in the Database.
 * Does Operations like Create, update, Display, Delete Projects.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public interface ProjectService {

    /**
     * validates the ProjectName
     *
     * @param projectsName as String.
     *
     * @return true if the validation is true.
     * @return false if the validation is false.
     */
    boolean validateProjectName(String projectName);

    /**
     * validates the Domain.
     *
     * @param projectsDomain as String.
     *
     * @return true if the validation is true.
     * @return false if the validation is false.
     */
    boolean validateProjectDomain(String projectDomain);

    /**
     * validates the Description
     *
     * @param projectsDescription as String.
     *
     * @return true if the validation is true.
     * @return false if the validation is false.
     */
    boolean validateProjectDescription(String projectDescription);


    /**
     * Inserts Projects into the Database.
     *
     * @param projectDto as ProjectDTO object.
     *
     */
    boolean insertProject(ProjectDTO projectDto);

    /**
     * checks the Database whether it contains Project or not.
     *
     * @return true if the Database is empty.
     * @return false if the Database is not empty.
     */
    boolean isDbIsEmpty();

    /**
     * Retrieves all the Project from the Database as an ArrayList.
     *
     * @return ArrayList that contains all the Project.
     */
    List<ProjectDTO> getAllProjects();

    /** 
     * Retrieves particular Project from the Database.
     *
     * @return Employee. 
     */
    ProjectDTO getParticularProject(int projectId);

    /**
     * checks whether the Id is present in the Database or not.
     *
     * @return true if the Database contains that particular 
     * Project Id.
     *
     * @return false if the Database contains that particular 
     * Project Id.
     *
     * @param projectId as long.
     */
    boolean isIdExists(int projectId);

    /**
     * Updates the Projects in the Database.
     *
     * @param projectDto as ProjectDTO object.
     *
     * @return the no of Rows affected as int.
     */
    boolean updateProject(ProjectDTO projectDto);

    /**
     * Deletes All the Projects in the Database.
     *
     * @return if the Project is Deleted.
     *
     * @retun if the Project is not Deleted.
     */
    boolean deleteAllProjects();

    /**
     * Deletes the particular Project in the Database.
     *
     * @param projectId as int.
     *
     * @return if the Project is Deleted.
     * @retun if the Project is not Deleted.
     */
    boolean deleteParticularProject(int projectId);
}