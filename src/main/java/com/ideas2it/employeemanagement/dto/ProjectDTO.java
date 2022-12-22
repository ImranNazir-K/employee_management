
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dto;

import java.util.Set;
import javax.validation.constraints.Pattern;
import lombok.Data;

import com.ideas2it.employeemanagement.constants.Constants;

/**
 * Contains variables for project like name, domain and description.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
@Data
public class ProjectDTO {

    private int projectId;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_DESCRIPTION,
            message = "Enter a valid Description")
    private String projectDescription;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_DOMAIN,
            message = "Enter a valid Domain")
    private String projectDomain;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_NAME,
            message = "Enter a valid ProjectName")
    private String projectName;
    
    Set<EmployeeDTO> employees;
}