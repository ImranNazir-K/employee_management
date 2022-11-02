/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dto;

/**
 * 
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class ProjectDTO {

    private int id;
    private String description;
    private String domain;    
    private String projectName;

    /**
     * Empty Constructor.
     */
    public ProjectDTO() {
    }

    /**
     * Parameterized Constructor.
     */
    public ProjectDTO(String projectName, String domain,
            String description) {
        this.id = id;
        this.projectName = projectName;
        this.domain = domain;
        this.description = description;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n Project ID          :" + getProjectId())
                     .append("\n Project Name        :" + getProjectName())
                     .append("\n Project Domain      :" + getProjectDomain())
                     .append("\n Project Description :")
                     .append(getProjectDescription());
        return stringBuilder.toString();       
    }
}