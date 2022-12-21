
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.constants;

/**
 * Constants for Employee Management System.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
public class Constants {

    public static final String CREATE_EMPLOYEE_EXCEPTION = "Error in "
            + "Creating Employee";
    public static final String CREATE_PROJECT_EXCEPTION = "Error in "
            + "Creating Project";
    public static final String DELETE_EMPLOYEE_EXCEPTION = "Error in"
            + " Deleting Employee";
    public static final String DELETE_PROJECT_EXCEPTION = "Error in"
            + " Deleting Project";
    public static final String DISPLAY_EMPLOYEE_EXCEPTION = "Error in"
            + " Displaying Employee";
    public static final String DISPLAY_EMPLOYEES_EXCEPTION = "Error "
            + "in Displaying Employees";
    public static final String DISPLAY_PROJECT_EXCEPTION = "Error in"
            + " Displaying Project";
    public static final String DISPLAY_PROJECTS_EXCEPTION = "Error in"
            + " Displaying Projects";
    public static final String EMPLOYEE_ALREADY_ASSIGNED = "Employee "
            + "Already Assigned Try Another Employee";
    public static final String EMPLOYEE_ASSIGNED = "Employee Assinged "
            + "Successfully";
    public static final String EMPLOYEE_CREATED = "Employee Created Successfully ";
    public static final String EMPLOYEE_DELETED = "Employee Deleted Successfully";
    public static final String EMPLOYEE_UNASSIGNED = "Employee "
            + "Un-Assinged Successfully";
    public static final String EMPLOYEE_UPDATED = "Employee Updated Successfully";
    public static final String EMPLOYEE_NOT_ASSIGNED =  "Employee Not Assigned "
    		+ "Try Another employee";
    public static final String ERROR_IN_ASSIGNING_EMPLOYEE = "Error in "
    		+ "Assigning Employee";
    public static final String ERROR_IN_ASSIGNING_PROJECT = "Error in Assigning"
    		+ " Project";
    public static final String ERROR_IN_UNASSIGNING_EMPLOYEE = "Error in "
    		+ " Un-Assigning Employee";
    public static final String ERROR_IN_UNASSIGNING_PROJECT = "Error in "
    		+ "Un-Assigning Project";
    public static final String EXISTING_CONTACT_NUMBER = "Contact Number " 
            + "Already Exists Try Another Number";
    public static final String EXISTING_MAIL = " Mail ID already exists Try "
    		+ "Another MailId";
    public static final String EMPLOYEE_ID_NOT_FOUND = "Employee ID does not "
    		+ "not exists";
    public static final String INVALID_REQUEST = "Invalid request! Redirecting"
    		+ " to error Page";
    public static final String MAILID_ERROR = "\n---Error Occured while "
            + "Checking Mail ID---\n ";
    public static final String NO_EMPLOYEES = "No Employees Available";
    public static final String NO_PROJECTS = "No Projects Available";
    public static final String PROJECT_ALREADY_ASSIGNED = "Project "
            + "Already Assigned Try Another Project";
    public static final String PROJECT_ASSIGNED = "Project Assinged "
            + "Successfully";
    public static final String PROJECT_CREATED = "Project Created Sucessfully";
    public static final String PROJECT_DELETED = "Project Deleted Sucessfully";
    public static final String PROJECT_ID_NOT_FOUND = "Project ID does not"
    		+ " Exists";
    public static final String PROJECT_NOT_ASSIGNED = "Project Not Assigned "
    		+ "Try Another Project";
    public static final String PROJECT_UNASSIGNED = "Project UnAssinged "
            + "Successfully";
    public static final String PROJECT_UPDATED = "Project Updated Successfully";
    public static final String UPDATE_EMPLOYEE_EXCEPTION = "Error in"
            + " Updating Employee";
    public static final String UPDATE_PROJECT_EXCEPTION = "Error in"
            + " Updating Project";
    public static final String VALIDATE_EMPLOYEE_CONTACT_NUMBER = "([6-9]{1}"
    		+ "[0-9]{9})";
    public static final String VALIDATE_EMPLOYEE_NAME = "((([A-Za-z]{2,}"
    		+ "([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})";
    public static final String VALIDATE_EMPLOYEE_MAILID = "((([A-Za-z0-9]"
    		+ "{1,})([.]?)){1,})([a-z]{0,}?)([@]{1})(([a-z])*)((([.])([a-z]"
    		+ "{2,3})){1,2})";
    public static final String VALIDATE_EMPLOYEE_SALARY = "([1-9]{1})([0-9]"
    		+ "{1,8})((([.])([0-9]{1,2}))?)";
    public static final String VALIDATE_PROJECT_DESCRIPTION = "((([A-Za-z]"
                + "{1,}([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})";
    public static final String VALIDATE_PROJECT_DOMAIN = "((([A-Za-z]{1,}"
                + "([ ]?)){1,}))((([.]?)([a-zA-Z]{1})){1,})";
    public static final String VALIDATE_PROJECT_NAME = "((([a-zA-Z0-9]{3,})"
                + "(([ ])([a-zA-Z0-9]){3,})?){1,})";
}