
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains Private variables of employees like
 * name, salary, mail, Id, joining date, contact Number.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

	@Column
    @NotNull
    private double employeeSalary;
    
	@NotNull
	@Column(unique = true)
    private String employeeContactNumber;
	
	@Column
	@NotNull
    private String employeeName;
	
	@Column(unique = true)
	@NotNull
    private String employeeMailId;
	
	@Column
	private String password;
	
	@Column
    private LocalDate employeeDateOfBirth;
    
    @ManyToMany
    @NonNull
    @Column
    @JoinTable(name = "employee_project")
    private Set<Project> project;
}