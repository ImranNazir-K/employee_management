
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains Private variables of project like name,
 * domain, description.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Project {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
	
	@Column
	@NotNull
    private String projectDescription;
	
	@Column
	@NotNull
    private String projectDomain;
	
	@Column
	@NotNull
    private String projectName;
	
	@ManyToMany
	@JoinTable(name = "employee_project")
	private Set<Employee> employee;
}