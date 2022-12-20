
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.employeemanagement.model.Project;

/**
 * Interface for Project Repository that extends JpaRepository.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}