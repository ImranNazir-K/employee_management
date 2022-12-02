
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains Private variables of project like name,
 * domain, description.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class ProjectDTO {

    private int id;
    private String description;
    private String domain;    
    private String projectName;
    Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

    public ProjectDTO() {
    }

    public ProjectDTO(String projectName, String description, String domain) {
        this.projectName = projectName;
        this.description = description;
        this.domain = domain;
    }

    public void setProjectId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return this.id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectDomain(String domain) {
        this.domain = domain;
    }

    public String getProjectDomain() {
        return this.domain;
    }

    public void setProjectDescription(String description) {
        this.description = description;
    }

    public String getProjectDescription() {
        return this.description;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public Set<EmployeeDTO> getEmployees() {
        return this.employees;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n Project ID          : " + getProjectId())
                     .append("\n Project Name        : " + getProjectName())
                     .append("\n Project Domain      : " + getProjectDomain())
                     .append("\n Project Description : ")
                     .append(getProjectDescription());
        return stringBuilder.toString();       
    }
}