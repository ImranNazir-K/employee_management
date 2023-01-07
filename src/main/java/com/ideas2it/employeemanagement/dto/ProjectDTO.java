
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
import lombok.Getter;
import lombok.Setter;

import com.ideas2it.employeemanagement.constants.Constants;

/**
 * Contains variables for project like name, domain and description.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Getter
@Setter
public class ProjectDTO {

    private int projectId;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_DESCRIPTION,
            message = Constants.INVALID_PROJECT_DESCRIPTION)
    private String projectDescription;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_DOMAIN,
            message = Constants.INVALID_PROJECT_DOMAIN)
    private String projectDomain;
    
    @Pattern(regexp = Constants.VALIDATE_PROJECT_NAME,
            message = Constants.INVALID_PROJECT_NAME)
    private String projectName;
    
    Set<EmployeeDTO> employees;
}