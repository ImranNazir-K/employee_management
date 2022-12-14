
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains Private variables of project like name,
 * domain, description.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 *
 * 
 */
public class Project {

    private int projectId;
    private String projectDescription;
    private String projectDomain;    
    private String projectName;
    private Set<Employee> employees;

    public Project() {
    }

    public Project(int projectId, String projectName, String domain,
            String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDomain = domain;
        this.projectDescription = description;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectDomain(String domain) {
        this.projectDomain = domain;
    }

    public String getProjectDomain() {
        return this.projectDomain;
    }

    public void setProjectDescription(String description) {
        this.projectDescription = description;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n Project ID           :" + getProjectId())
                     .append("\n Project Name         :" + getProjectName())
                     .append("\n Project Domain       :" + getProjectDomain())
                     .append("\n Project Description  :")
                     .append(getProjectDescription());
        return stringBuilder.toString();       
    }
}