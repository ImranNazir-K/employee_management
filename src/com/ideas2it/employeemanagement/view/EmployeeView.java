
/*
 * Copyright (c) 2022, Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.view;

import org.hibernate.HibernateException; 
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;

/**
 * Contains all the statements to be shown to the users.
 * Does operatiosn like create, display, update,
 * delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeView {

    private static final EmployeeController employeeController = new EmployeeController();
    private static final Logger logger = LoggerFactory.getFactory();
    private static final Scanner scanner = new Scanner(System.in);

    public EmployeeView() {
    }

    /**
     * Does create, display, update and delete operations for
     * employee.
     */
    public void chooseOperation() {
        byte choice;
        boolean isValid = false; 

        do {
            logger.info(Constants.EMPLOYEE_USER_MENU);
            choice = getChoice();

            switch (choice) {
                case 1: 
                    createEmployee();
                    break;

                case 2:
                    displayEmployee();
                    break;

                case 3:
                    updateEmployee();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    isValid = true;
                    break;

                default:
                    logger.warn(Constants.INVALID_CHOICE);
            }
        } while (!isValid); 
    }

    /**
     * creates employee.
     */
    private void createEmployee() {
        byte choice;
        boolean isContinue = false;

        do {
            createEmployees();
            logger.info(Constants.CREATE_MENU);
            choice = getChoice();

            switch (choice) {
                case 1:
                    isContinue = true;
                    break;
            
                case 2:
                    isContinue = false;
                    break;
             
                default:
                    logger.warn(Constants.INVALID_CHOICE);
                    isContinue = true;
            }
        } while (isContinue);
    }

    /**
     * Creates Employee.
     */
    private void createEmployees() {
        int employeeId;

        EmployeeDTO employeeDto = new EmployeeDTO(getEmployeeName(),
                getEmployeeMailId(), getEmployeeContactNumber(),
                getEmployeeSalary(), getEmployeeBirthDate());

        try {
            employeeId = employeeController.insertEmployee(employeeDto);
            logger.info(Constants.CREATED_ID + employeeId);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Gets employee name to validate.
     *
     * @return validated employee's Name.
     */
    private String getEmployeeName() {
        boolean isValid = false;
        String employeeName = new String();

        do {
            logger.info(Constants.ENTER_NAME);
            employeeName = scanner.nextLine();
            isValid = employeeController.validateEmployeeName(employeeName);

            if (!isValid) {
                logger.warn(Constants.INVALID_NAME);
            } 
        } while (!isValid);
        return (employeeName.toUpperCase());
    } 

    /**
     * Gets Employee mail Id to validate.
     *
     * @return the validated employee's mail Id.
     */
    private String getEmployeeMailId() {
        boolean isValid = false;
        String employeeMailId = new String();

        do {
            logger.info(Constants.ENTER_MAIL_ID);
            employeeMailId = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeMailId(employeeMailId);

            if (!isValid) {
                logger.warn(Constants.INVALID_MAIL);
            } 
 
            try {

                if (employeeController.isMailIdExists(employeeMailId)) {
                    logger.warn(employeeMailId + Constants.EXISTING_MAIL);
                    isValid = false;
                }
            } catch (EMSException exception) {
                logger.error(exception.getMessage());
            }
    
        } while (!isValid);
        return (employeeMailId.toLowerCase());
    } 

    /**
     * Gets employee contact number to validate.
     *
     * @return validated employee's contact number.
     */
    private long getEmployeeContactNumber() {
        boolean isValid = false;
        String employeeContactNumber = new String();

        do {
            logger.info(Constants.ENTER_CONTACT_NUMBER);
            employeeContactNumber = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeContactNumber(employeeContactNumber);

            try {

                if (!isValid) {
                    logger.warn(Constants.INVALID_CONTACT_NUMBER );
                } else if (employeeController
                        .isContactNumberExists(employeeContactNumber)) {
                    logger.warn(employeeContactNumber 
                            + Constants.EXISTING_CONTACT_NUMBER);
                    isValid = false;
                }
            } catch (EMSException exception) {
                logger.error(exception.getMessage());
            }
        } while (!isValid);
        return Long.parseLong(employeeContactNumber);
    } 

    /**
     * Gets Employee Salary to validate.
     *
     * @return the validated employee's Salary.
     */
    private double getEmployeeSalary() {
        boolean isValid = false;
        String employeeSalary = new String();

        do {
            logger.info(Constants.ENTER_SALARY);
            employeeSalary = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeSalary(employeeSalary);

            if (!isValid) {
                logger.warn(Constants.INVALID_SALARY);
            }
        } while (!isValid);
        return Double.parseDouble(employeeSalary);
    }

    /**
     * Gets Employees Date Of Birth to validate.
     *
     * @return the validated employee's dateOfBirth.
     */
    private LocalDate getEmployeeBirthDate() {
        boolean isValid = false;
        String dateOfBirth = new String();

        do {
            logger.info(Constants.ENTER_DATE_OF_BIRTH);
            dateOfBirth = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeBirthDate(dateOfBirth);

            if (!isValid) {
                logger.warn(Constants.INVALID_DATE_OF_BIRTH);
            }
        } while (!isValid);
        return LocalDate.parse(dateOfBirth);
    }

    /**
     * checks whether the database is empty.
     */
    private void displayEmployee() {
        try {

            if (employeeController.isDbIsEmpty()) {
                logger.info(Constants.NO_EMPLOYEES);
            } else {
                performDisplayEmployee();
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Displays all employees or particular employee.
     */
    private void performDisplayEmployee() {
        byte choice;
        boolean isValid = false;

        do {
            logger.info(Constants.DISPLAY_MENU);
            choice = getChoice();

            switch (choice) {
                case 1:
                    displayAllEmployees();
                    isValid = true;
                    break;

                case 2:
                    isValid = displayParticularEmployee(isValid) ;
                    break;

                case 3:
                    isValid = false;
                    break;

                default:
                    logger.warn(Constants.INVALID_CHOICE);
                    isValid = true;
            }
        } while (isValid);
    }

    /**
     * Displays All Employees available in the Database.
     */ 
    private void displayAllEmployees() {
        try {

            for (EmployeeDTO employee : employeeController
                    .getAllEmployees()) {
                logger.info(employee);
                displayProjects(employee);
                logger.info(Constants.LINE);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Displays the particular Employee.
     *
     * @param isValid as boolean.
     *
     * @return true when it displays particular Employee. 
     */
    private boolean displayParticularEmployee(boolean isValid) {
        boolean isContinue = false;
        int employeeId = 0;
        EmployeeDTO employeeDto = null;

        try {

            do {
                employeeId = getEmployeeId(); 
                isValid = employeeController.isIdExists(employeeId);

                if (isValid) {
                    employeeDto = employeeController
                            .getParticularEmployee(employeeId);
                    logger.info(employeeDto);
                    displayProjects(employeeDto);
                    isContinue = true;
                } else {
                    logger.info(employeeId + Constants.ID_NOT_FOUND);
                }
            } while (!isContinue);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }

    /**
     * Gets employee Id.
     *
     * @return employeeId.
     */
    private int getEmployeeId() {
        boolean isValid = false;
        int employeeId = 0;

        try { 

            do {  
                logger.info(Constants.ENTER_EMPLOYEE_ID);
                employeeId = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } while (!isValid);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(Constants.INVALID_FORMAT);
            isValid = true;
        }
        return employeeId;
    }

    /**
     * Displays the projects of the employee.
     *
     * @param employee as EmployeeDTO object.
     */
    private void displayProjects(EmployeeDTO employee) {
        if (employee.getProjectList().isEmpty()) {
            logger.info(Constants.PROJECTS_NULL);
        } else {
            logger.info(Constants.PROJECTS);

            for (ProjectDTO project : employee.getProjectList()) {
                logger.info(project);
            }
        }
    }

    /**
     * Updates the employee by checking whether the Database is
     * empty or not. If Database is not Empty it updates Employee.
     * else it display custom Message to the user.
     */
    private void updateEmployee() {
        boolean isContinue = false;

        try {

            if (employeeController.isDbIsEmpty()) {
                logger.info(Constants.NO_EMPLOYEES);
            } else {

                do {
                    performUpdateEmployee();
                    isContinue = continueUpdate();
                } while (isContinue); 
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Update all the details of an employee and particular details
     * of an employee.
     */
    private void performUpdateEmployee() {
        byte choice;
        int employeeId;
        boolean isValid = false;
        boolean isExists = false;


        try {

            do {
                employeeId = getEmployeeId();

                if (employeeController.isIdExists(employeeId)) {

                    do {
                        logger.info(Constants.UPDATE_MENU);
                        choice = getChoice();

                        switch (choice) {
                            case 1:
                                updateAllFields(employeeId);
                                break;

                            case 2:
                                updateParticularField(employeeId);
                                break;

                            case 3:
                                assignProject(employeeId);
                                break;

                            case 4:
                                unAssignProject(employeeId);
                                break;

                            case 5:
                                isValid = true;
                                break;

                            default:
                                logger.warn(Constants.INVALID_CHOICE);
                        }
                    } while (!isValid);
                } else {
                    logger.info(employeeId + Constants.ID_NOT_FOUND);
                    isExists = true;
                }
            } while (isExists);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Updates all the fields of an Employee.
     */
    private void updateAllFields(int employeeId) {
        EmployeeDTO employeeDto = null;

        try {
            employeeDto = employeeController
                    .getParticularEmployee(employeeId);
                       
            employeeDto.setEmployeeName(getEmployeeName());
            employeeDto.setEmployeeMailId(getEmployeeMailId(employeeId));
            employeeDto.setEmployeeContactNumber
                    (getEmployeeContactNumber(employeeId));
            employeeDto.setEmployeeSalary(getEmployeeSalary());
            employeeDto.setEmployeeDateOfBirth(getEmployeeBirthDate());

            employeeController.updateEmployee(employeeDto);
            logger.info(Constants.EMPLOYEE_UPDATED);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * updates the particular field of an employee.
     */
    private void updateParticularField(int employeeId) {
        byte field;
        boolean isContinue = false;
        EmployeeDTO employeeDto = null;

        try {
            employeeDto = employeeController.
                    getParticularEmployee(employeeId);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }

        do {
            logger.info(Constants.PARTICULAR_UPDATE_MENU);
            field = getChoice();
            isContinue = selectUpdateField(field, employeeDto, employeeId);
        } while (isContinue);
    }

    /**
     * Chooses the particular field of an particular Employee to
     * update.
     *
     * @param field as byte.
     * @param employeeDto as EmployeeDTO object.
     * @param employeeId as int.
     *
     * @return false when user wants to exit.
     */
    private boolean selectUpdateField(byte field, EmployeeDTO employeeDto,
            int employeeId) {
        boolean isContinue = false;

        switch (field) {
            case 1:
                updateEmployeeName(employeeDto);
                isContinue = true;
                break; 

            case 2:
                updateEmployeeMailId(employeeDto, employeeId);
                isContinue = true;
                break;

            case 3:
                updateEmployeeContactNumber(employeeDto, employeeId);
                isContinue = true;
                break;

            case 4:
                updateEmployeeSalary(employeeDto);
                isContinue = true;
                break;

            case 5:
                updateEmployeeBirthDate(employeeDto);
                isContinue = true;
                break;

            case 6:
                try {
                    employeeController.updateEmployee(employeeDto);
                    logger.info(Constants.EMPLOYEE_UPDATED);
                } catch (EMSException exception) {
                    logger.error(exception.getMessage());
                } 
                isContinue = false;
                break;

            default:
                logger.warn(Constants.INVALID_CHOICE);  
                isContinue = true;              
                break;
        }
        return isContinue;
    }

    /**
     * updates the name of the particular employee.
     *
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeName(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeName(getEmployeeName());
    }

    /**
     * updates the Email Id of that particular employee.
     *
     * @param employeeId as int.
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeMailId(EmployeeDTO employeeDto,
            int employeeId) {
        String mailId = getEmployeeMailId(employeeId);
        employeeDto.setEmployeeMailId(mailId);
    }

    /**
     * updates the Contact Number of that particular employee.
     *
     * @param employeeId as int.
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeContactNumber(EmployeeDTO employeeDto,
            int employeeId) {
        long contactNumber = getEmployeeContactNumber(employeeId);
        employeeDto.setEmployeeContactNumber(contactNumber);
    }

    /**
     * updates the Salary of that particular employee.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    private void updateEmployeeSalary(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeSalary(getEmployeeSalary());
    }

    /**
     * updates the Date Of Birth of that particular employee
     *
     * @param employeeDto as EmployeeDTO object.
     */
    private void updateEmployeeBirthDate(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeDateOfBirth(getEmployeeBirthDate());
    }

    /**
     * Continues to update another employee if Users wants to update.
     *
     * @return true if users wants to continue Update.
     */
    private boolean continueUpdate() {
        byte choice;
        boolean isContinue = false;
        boolean isValid = false;

        do {
            logger.info(Constants.UPDATE_MENU_TO_CONTINUE);
            choice = getChoice();

            switch (choice) {
                case 1:
                    isContinue = true;
                    isValid = true;
                    break;

                case 2:
                    isContinue = false;
                    isValid = true;
                    break;

                default:
                    logger.warn(Constants.INVALID_CHOICE);
                    isValid = false;
            }
        } while (!isValid);
        return isContinue;
    }

    /**
     * gets mailId of an employee to check whether the mail Id
     * is present in the database or not.
     *
     * @param employeeId as int.
     *
     * @return mailId.
     */
    private String getEmployeeMailId(int employeeId) {
        String mailId;
        boolean isValid = false;

        do {
            logger.info(Constants.ENTER_MAIL_ID);
            mailId = scanner.nextLine();

            if (employeeController.validateEmployeeMailId(mailId)) {

                try {

                    if (employeeController.isMailIdExists(mailId,
                            employeeId)) {
                        logger.info(mailId + Constants.EXISTING_MAIL);
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                } catch (EMSException exception) {
                    logger.error(exception.getMessage());
                    isValid = false;
                }
            } else {
                logger.warn(Constants.INVALID_MAIL);
                isValid = false;
            }
        } while (!isValid);
        return (mailId.toLowerCase());
    }

    /**
     * gets employee Contact number to check whether the contact
     * number is present in the database or not.
     *
     * @param employeeId as int.
     *
     * @return contactNumber.
     */
    private long getEmployeeContactNumber(int employeeId) {
        String contactNumber;
        boolean isValid = false;

        do {
            logger.info(Constants.ENTER_CONTACT_NUMBER);
            contactNumber = scanner.nextLine();

            if (employeeController
                    .validateEmployeeContactNumber(contactNumber)) {

                try {

                    if (employeeController.isContactNumberExists(Long
                            .parseLong(contactNumber), employeeId)) {
                        logger.info(contactNumber 
                               + Constants.EXISTING_CONTACT_NUMBER);
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                } catch (EMSException exception) {
                    logger.error(exception.getMessage());
                    isValid = false;
                }
            } else {
                logger.error(Constants.INVALID_CONTACT_NUMBER);
                isValid = false;
            }
        } while (!isValid);
        return Long.parseLong(contactNumber);
    }

   /**
     * Assigns employee to Project.
     *
     * @param employeeId as int.
     */
    private void assignProject(int employeeId) {
        byte choice;
        boolean isContinue = false;

        try {

            if (employeeController.isProjectDbIsEmpty()) {
                logger.info(Constants.NO_PROJECTS);
            } else {

                do {
                    assignProjects(employeeId);
                    logger.info(Constants.CONTINUE_ASSIGN_PROJECTS);

                    choice = getChoice();

                    switch (choice) {
                        case 1:
                            isContinue = true;
                            break;

                        case 2:
                            isContinue = false;
                            break;

                        default:
                            logger.warn(Constants.INVALID_CHOICE);
                            isContinue = true;
                    }
                } while (isContinue);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Assigns Employees to Project.
     *
     * @param employeeId as int.
     */
    private void assignProjects(int employeeId) {
        boolean isAssigned = false;
        int projectId;

        try {
            do {
                displayProjects();
                projectId = getProjectId();

                if (employeeController.isAlreadyAssigned(employeeId,
                        projectId)) {
                    logger.info(Constants.PROJECT_ALREADY_ASSIGNED);
                } else {
                    assignEmployeeToProject(employeeId, projectId);
                    isAssigned = true;
                }
            } while (!isAssigned);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Checks whether the Employee is Assigned to a project or not.
     *
     * @param employeeId as int.
     * @param projectId as int.
     */
    private void assignEmployeeToProject(int employeeId, int projectId) {
        EmployeeDTO employeeDto = null;
        ProjectDTO projectDto = null;
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

        try {
            employeeDto = employeeController
                    .getParticularEmployee(employeeId);
            projectDto = employeeController
                    .getParticularProject(projectId);

            projects = employeeDto.getProjectList();
            projects.add(projectDto);
            employeeDto.setProjectList(projects);
          
            employeeController.updateEmployee(employeeDto);
            logger.info(Constants.PROJECT_ASSIGNED);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Un-assigns employee from project.
     *
     * @param employeeId as int.
     */
    private void unAssignProject(int employeeId) {
        byte choice;
        boolean isContinue = false;

        try {

            if (employeeController.isProjectDbIsEmpty()) {
                logger.info(Constants.NO_PROJECTS);
            } else {

                do {
                    unAssignProjects(employeeId);
                    logger.info(Constants.CONTINUE_UNASSIGN_PROJECTS);
                    choice = getChoice();

                    switch (choice) {
                        case 1:
                            isContinue = true;
                            break;

                        case 2:
                           isContinue = false;
                           break;

                        default:
                           logger.warn(Constants.INVALID_CHOICE);
                    }
                } while (isContinue);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Un-assigns employee from project.
     *
     * @param employeeId as int.
     */
    private void unAssignProjects(int employeeId) {
        boolean isUnassigned = false;
        int projectId = 0;
        EmployeeDTO employeeDto = null;
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

        try {
            employeeDto = employeeController
                    .getParticularEmployee(employeeId);
            projects = employeeDto.getProjectList();

            do {            
                logger.info(Constants.PROJECTS);

                for (ProjectDTO project : projects) {
                    logger.info(project);
                }
                projectId = getProjectId();

                if (employeeController.isAlreadyAssigned(employeeId,
                        projectId)) {

                    for (ProjectDTO project : projects) {

                        if (projectId == project.getProjectId()) {
                            projects.remove(project);
                            break;
                        }
                    }
                    employeeDto.setProjectList(projects);
                    unAssignEmployeeFromProject(employeeDto);
                    isUnassigned = true;
                } else {
                    logger.info(Constants.PROJECT_NOT_ASSIGNED);
                }
            } while (!isUnassigned); 
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }      
    }

    /**
     * Displays menu to continue Assigning Project.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    private void unAssignEmployeeFromProject(EmployeeDTO employeeDto) {

        try {
            employeeController.updateEmployee(employeeDto);
            logger.info(Constants.PROJECT_UNASSIGNED);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Displays all the project available in the database.
     */
    private void displayProjects() {
        logger.info(Constants.AVAILABLE_PROJECTS);

        try {

            for (ProjectDTO project : employeeController.getAllProjects()) {
                logger.info(project);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Gets Project ID of an Employee.
     *
     * @return the validated employee's project Id.
     */
    private int getProjectId() {
        int projectId = 0;
        boolean isValid = false;

        try {
            do {   
                logger.info(Constants.ENTER_PROJECT_ID);
                projectId = Integer.parseInt(scanner.nextLine());

                if (employeeController.isProjectIdExists(projectId)) {
                    isValid = true;
                } else {
                    logger.info(projectId + Constants.ID_NOT_FOUND);
                    isValid = false;
                }
            } while (!isValid);
        } catch (NumberFormatException  numberFormatException) {
            logger.warn(Constants.INVALID_FORMAT);
            isValid = false;
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return projectId;
    }

    /**
     * checks whether the Database contains Employees or not.
     * If the Database is Empty it displays custom Message or it will
     * Deletes Employee from the Database.
     */
    private void deleteEmployee() {
        try {
            if (employeeController.isDbIsEmpty()) {
                logger.info(Constants.NO_EMPLOYEES);
            } else {
                performDeleteEmployee();
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Deletes all Employees or Particular Employee users wants
     * to delete.  
     */
    private void performDeleteEmployee() {
        boolean isValid = false;

        do {
            logger.info(Constants.DELETE_MENU);
            byte choice = getChoice();

            switch (choice) {
                case 1:
                    try {
                        employeeController.deleteAllEmployees();
                        logger.info(Constants.EMPLOYEES_DELETED);
                        isValid = true;
                    } catch (EMSException exception) {
                        logger.error(exception.getMessage());
                    }
                    break;

                case 2:
                    isValid = deleteParticularEmployee(isValid);
                    break;

                case 3:
                    isValid = true;
                    break;

                default:
                    logger.warn(Constants.INVALID_CHOICE);
                    isValid = false;
            }
        } while (!isValid);
    }

    /**
     * Deletes particular employee.
     *
     * @param isValid as boolean.
     *
     * @return true only after it deletes particular Employee.
     */
    private boolean deleteParticularEmployee(boolean isValid) {
        int employeeId;
        boolean isContinue = false;

        try {

            do {
                employeeId = getEmployeeId();

                if (employeeController.isIdExists(employeeId)) {
                    employeeController.deleteParticularEmployee(employeeId);
                    logger.info(employeeId + Constants.EMPLOYEE_DELETED);
                    isValid = true;
                    isContinue = true;
                } else {
                    logger.info(employeeId + Constants.ID_NOT_FOUND);
                }
            } while (!isContinue);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }

    /**
     * Gets choice as input from the user.
     *
     * @return choice as byte.
     */
    private byte getChoice() {
        byte choice = 0;

        try {
            logger.info(Constants.ENTER_CHOICE);
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }
}