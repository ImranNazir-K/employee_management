
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dto;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Data;

import com.ideas2it.employeemanagement.constants.Constants;

/**
 * Contains variables for employees like
 * name, salary, mail, Id, joining date and contact Number.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
@Data
public class EmployeeDTO {

    private int employeeId;
    
    @Pattern(regexp = Constants.VALIDATE_EMPLOYEE_CONTACT_NUMBER,
            message = "Enter a valid Contact Number")
    private String employeeContactNumber;
    
    @Pattern(regexp = Constants.VALIDATE_EMPLOYEE_SALARY,
            message = "Enter a valid Salary")
    private String employeeSalary;
    
    @Pattern(regexp = Constants.VALIDATE_EMPLOYEE_NAME,
            message = "Enter a valid Emlpoyee Name")
    private String employeeName;
    
    @Email(message = "Enter a valid Employee Mail ID")
    private String employeeMailId;
    
    private LocalDate employeeDateOfBirth;
    
    private Set<ProjectDTO> projects;
}