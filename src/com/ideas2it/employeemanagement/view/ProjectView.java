
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.view;

import org.hibernate.HibernateException;
import java.lang.NumberFormatException;
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
 * Contains all the statments to be shown to the users.
 * does operations like create, display, update,
 * delete Employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class ProjectView {

    private static final Logger logger = LoggerFactory.getFactory();
    private static final ProjectController projectController = new ProjectController();
    private static final Scanner scanner = new Scanner(System.in);

    public ProjectView() {
    }

    /**
     * Does create, display, update and delete operations for
     * employee.
     */
    public void chooseOperation() {
        byte choice;
        boolean isValid = false; 

        do {
            logger.info(Constants.PROJECT_USER_MENU);
            choice = getChoice();

            switch (choice) {
                case 1: 
                    createProject();
                    break;

                case 2:
                    displayProject();
                    break;

                case 3:
                    updateProject();
                    break;

                case 4:
                    deleteProject();
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
     * Creates project.
     */
    private void createProject() {
        byte choice;
        boolean isContinue = false;

        do {
            createProjects();
            logger.info(Constants.PROJECT_CREATE_MENU);
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
     * Creates Projects
     */
    private void createProjects() {
        int projectId;

        ProjectDTO projectDto = new ProjectDTO(getProjectName(),
                getProjectDomain(), getProjectDescription());

        try {
            projectId = projectController.insertProject(projectDto);
            logger.info(Constants.PROJECT_CREATED + projectId);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Gets Project Name to validate the entered name.
     *
     * @return validated Project Name.
     */
    private String getProjectName() {
        String projectName = new String();
        boolean isValid = false;

        do {
            logger.info(Constants.ENTER_PROJECT_NAME);
            projectName = scanner.nextLine();
            isValid = projectController.validateProjectName(projectName);

            if (!isValid) {
                logger.warn(Constants.INVALID_PROJECT_NAME);
            } 
        } while (!isValid);
        return (projectName.toUpperCase());
    }

    /**
     * Gets project domain to validate the entered Domain.
     *
     * @return validated Project Domain.
     */
    private String getProjectDomain() {
        boolean isValid = false;
        String projectDomain = new String();

        do {
            logger.info(Constants.ENTER_PROJECT_DOMAIN);
            projectDomain = scanner.nextLine();
            isValid = projectController.validateProjectDomain(projectDomain);

            if (!isValid) {
                logger.warn(Constants.INVALID_PROJECT_DOMAIN);
            } 
        } while (!isValid);
        return (projectDomain.toUpperCase());
    }

    /**
     * Gets project decription to validate the entered name.
     *
     * @return validated Project Name.
     */
    private String getProjectDescription() {
        boolean isValid = false;
        String projectDescription = new String();

        do {
            logger.info(Constants.ENTER_PROJECT_DESCRIPTION);
            projectDescription = scanner.nextLine();

            isValid = projectController
                    .validateProjectDescription(projectDescription);

            if (!isValid) {
                logger.warn(Constants.INVALID_PROJECT_DESCRIPTION);
            } 
        } while (!isValid);
        return projectDescription;
    }

    /**
     * gets Project Id from the user.
     *
     * @return projectId.
     */
    private int getProjectId() {
        boolean isValid = false;
        int projectId = 0;

        try {
 
            do {  
                logger.info(Constants.ENTER_PROJECT_ID );
                projectId = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } while (!isValid);
        } catch (NumberFormatException numberFormatException) {
            logger.error(Constants.INVALID_FORMAT);
            isValid = true;
        }
        return projectId ;
    }

    /**
     * checks whether the database contains projects or not.
     * If the database is empty it displays custom message or it will
     * displays the projects available in the database.
     */
    private void displayProject() {
        try {

            if (projectController.isDbIsEmpty()) {
                logger.info(Constants.NO_PROJECTS);
            } else {
            performDisplayProject();
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Displays all projects or particular project.
     */
    private void performDisplayProject() {
        byte choice;
        boolean isValid = false;

        do {
            logger.info(Constants.PROJECT_DISPLAY_MENU);
            choice = getChoice();

            switch (choice) {
                case 1:
                    displayAllProjects();
                    isValid = true;
                    break;

                case 2:
                    isValid = displayParticularProject(isValid) ;
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
     * Displays all the projects available in the Database.
     */
    public void displayAllProjects() {

        try {

            for (ProjectDTO projectDto : projectController.getAllProjects()) {
                logger.info(projectDto);
                displayEmployees(projectDto);
                logger.info(Constants.LINE);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Displays the particular project user wants to read.
     *
     * @param isValid as boolean.
     *
     * @return true when it displays particular Project. 
     */
    private boolean displayParticularProject(boolean isValid) {
        boolean isContinue = false;
        int projectId = 0;
        ProjectDTO projectDto = null;

        try {

            do {
                projectId = getProjectId();
                isValid = projectController.isIdExists(projectId); 

                if (isValid) {
                    projectDto = projectController
                            .getParticularProject(projectId);
                    logger.info(projectDto);
                    displayEmployees(projectDto);
                    isContinue = true;
                } else {
                    logger.info(projectId + Constants.ID_NOT_FOUND);
                }
            } while (!isContinue);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }

    /**
     * Assigns project to employee.
     */
    private void displayEmployees(ProjectDTO projectDto) {
        if (projectDto.getEmployeeList().isEmpty()) {
            logger.info(Constants.EMPLOYEE_NOT_ASSIGNED);
        } else {
            logger.info(Constants.EMPLOYEES);

            for (EmployeeDTO employee : projectDto.getEmployeeList()) {
                logger.info(employee);
            }
        } 
    }

    /**
     * Updates the Project by checking whether the database is empty
     * or not. If database is not empty it updates project.
     * If database is empty it display custom Message to the user.
     */
    private void updateProject() {
        boolean isContinue = false;

        try {

            if (projectController.isDbIsEmpty()) {
                logger.info(Constants.NO_PROJECTS);
            } else {

                do {
                    performUpdateProject();
                    isContinue = continueUpdate();
                } while (isContinue); 
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Update All the Details of an Project and particular details
     * of an Project.
     */
    private void performUpdateProject() {
        byte choice;
        int projectId;
        boolean isValid = false;
        boolean isExists = false;

        try {

            do {
                projectId = getProjectId();

                if (projectController.isIdExists(projectId)) {

                    do {
                        logger.info(Constants.PROJECT_UPDATE_MENU);
                        choice = getChoice();

                        switch (choice) {
                            case 1:
                                updateAllFields(projectId);
                                break;

                            case 2:
                                updateParticularField(projectId);
                                break;

                            case 3:
                                assignEmployee(projectId);
                                break;

                            case 4:
                                unAssignEmployee(projectId);
                                break;

                            case 5:
                                isValid = true;
                                isExists = false;
                                break;

                            default:
                                logger.warn(Constants.INVALID_CHOICE);
                        }
                    } while (!isValid);
                } else {
                    logger.info(projectId + Constants.ID_NOT_FOUND);
                    isExists = true;
                }
            } while (isExists);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * updates all the fields of an Project.
     */
    private void updateAllFields(int projectId) {
        ProjectDTO projectDto = null;

        try {
            projectDto = projectController
                .getParticularProject(projectId);
            projectDto.setProjectName(getProjectName());
            projectDto.setProjectDomain(getProjectDomain());
            projectDto.setProjectDescription(getProjectDescription());

            projectController.updateProject(projectDto);
            logger.info(Constants.PROJECT_UPDATED);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * updates the particular field of an Project.
     */
    private void updateParticularField(int projectId) {
        byte field;
        boolean isContinue = false;
        ProjectDTO projectDto = null;

        try {
            projectDto = projectController
                    .getParticularProject(projectId);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }

        do {
            logger.info(Constants.PARTICULAR_PROJECT_UPDATE_MENU);
            field = getChoice();
            isContinue = selectUpdateField(field, projectDto);
        } while (isContinue);
    }

    /**
     * Chooses the particular field of an project to update.
     *
     * @param field as byte.
     * @param projectDto object as ProjectDTO.
     *
     * @return true when user wants to exit.
     */
    private boolean selectUpdateField(byte field, ProjectDTO projectDto) {
        boolean isContinue = false;

        switch (field) {
            case 1:
                updateProjectName(projectDto);
                isContinue = true;
                break; 

            case 2:
                updateProjectDomain(projectDto);
                isContinue = true;
                break;

            case 3:
                updateProjectDescription(projectDto);
                isContinue = true;
                break;

            case 4:
                try {
                    projectController.updateProject(projectDto);
                    logger.info(Constants.PROJECT_UPDATED);
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
     * updates the Name of the Project.
     *
     * @param projectDto object as ProjectDTO.
     *
     */
    private void updateProjectName(ProjectDTO projectDto) {
        projectDto.setProjectName(getProjectName());
    }

    /**
     * updates the  Domain of the project.
     *
     * @param projectDto object as ProjectDTO.
     *
     */
    private void updateProjectDomain(ProjectDTO projectDto) {
        projectDto.setProjectDomain(getProjectDomain());
    }

    /**
     * updates the description of the Project.
     *
     * @param projectDto object as ProjectDTO.
     *
     */
    private void updateProjectDescription(ProjectDTO projectDto) {
        projectDto.setProjectDescription(getProjectDescription());
    }

    /**
     * Continues to Update another Project if Users wants to update.
     *
     * @return true if users wants to continue Update.
     * @return false if users doesn't wants to continue Update.
     */
    private boolean continueUpdate() {
        byte choice;
        boolean isContinue = false;
        boolean isValid = false;

        do {
            logger.info(Constants.PROJECT_UPDATE_MENU_TO_CONTINUE);
            choice = getChoice();

            switch (choice) {
                case 1:
                    isContinue = true;
                    break;

                case 2:
                    isValid = true;
                    break;

                case 3:
                   isContinue = true;

                default:
                    logger.warn(Constants.INVALID_CHOICE);
                    isValid = false;
            }
        } while (!isValid);
        return isContinue;
    }

    /**
     * Assigns project to employee.
     */
    private void assignEmployee(int projectId) {
        byte choice;
        boolean isContinue = false;

        try {

            if (projectController.isEmployeeDbIsEmpty()) {
                logger.info(Constants.NO_EMPLOYEES);
            } else {

                do {
                    assignEmployees(projectId);
                    logger.info(Constants.CONTINUE_ASSIGN_EMPLOYEES);
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
     * Assigns project to employee.
     *
     * @param projectId as int.
     */
    private void assignEmployees(int projectId) {
        int employeeId;
        boolean isPresent = false;

        try {

            do {
                displayAvailableEmployees();
                employeeId = getEmployeeId();
                
                if (projectController.isAlreadyAssigned(projectId,
                        employeeId)) {
                    logger.info(Constants.EMPLOYEE_ALREADY_ASSIGNED);
                    isPresent = true;
                } else {
                    assignProjectToEmployee(projectId, employeeId);
                    isPresent = false;
                }
            } while (isPresent);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Checks whether the project is assigned to an employee
     *
     * @param projectId as int.
     * @param employeeId as int.
     */
    private void assignProjectToEmployee(int projectId, int employeeId) {
        EmployeeDTO employeeDto = null;
        ProjectDTO projectDto = null;
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        try {
            employeeDto = projectController
                    .getParticularEmployee(employeeId);
            projectDto = projectController
                    .getParticularProject(projectId);
            employees = projectDto.getEmployeeList();
            employees.add(employeeDto);
            projectDto.setEmployeeList(employees);

            projectController.updateProject(projectDto);
            logger.info(Constants.EMPLOYEE_ASSIGNED);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * UnAssigns project from employee.
     *
     * @param projectId as int.
     */
    private void unAssignEmployee(int projectId) {
        byte choice;
        boolean isContinue = false;

        try {

            if (projectController.isDbIsEmpty()) {
                logger.info(Constants.NO_PROJECTS);
            } else {

                do {
                    unAssignEmployees(projectId);
                    logger.info(Constants.CONTINUE_UNASSIGN_EMPLOYEES);
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
     * Un-Assigns project from employee.
     *
     * @param projectId as int.
     */
    private void unAssignEmployees(int projectId) {
        boolean isPresent = false;
        int employeeId = 0;
        ProjectDTO projectDto = null;
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        try {
            projectDto = projectController
                    .getParticularProject(projectId);
            employees = projectDto.getEmployeeList();

            do { 
                logger.info(Constants.EMPLOYEES);

                for (EmployeeDTO employee : employees) {
                    logger.info(employee);
                }
                employeeId = getEmployeeId();

                if (projectController.isAlreadyAssigned(projectId,
                        employeeId)) {

                    for (EmployeeDTO employee : employees) {

                        if (employeeId == employee.getEmployeeId()) {
                            employees.remove(employee);
                            break;
                        }
                    }
                    projectDto.setEmployeeList(employees);
                    isPresent = unAssignProjectFromEmployee(projectDto);
                } else {
                    logger.info(Constants.EMPLOYEE_ALREADY_ASSIGNED);
                    isPresent = false;
                }
            } while (!isPresent);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Un-Assigns project from employee.
     *
     * @param projectDto as ProjectDTO object.
     */
    private boolean unAssignProjectFromEmployee(ProjectDTO projectDto) {
        boolean isUnAssigned = false;

        try {
            projectController.updateProject(projectDto);
            logger.info(Constants.EMPLOYEE_UNASSIGNED);
            isUnAssigned = true;
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return isUnAssigned;
    }

    /**
     * Displays all the employees available in the database.
     */
    private void displayAvailableEmployees() {
        logger.info(Constants.AVAILABLE_EMPLOYEES);

        try {

            for (EmployeeDTO employee : projectController.getAllEmployees()) {
                logger.info(employee);
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }   
    }

    /**
     * Gets id of an employee.
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

                if (projectController.isEmployeeIdExists(employeeId)) {
                    isValid = true;
                } else {
                    logger.info(employeeId + Constants.ID_NOT_FOUND);
                    isValid = false;
                }
            } while (!isValid);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(Constants.INVALID_FORMAT);
            isValid = false;
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return employeeId;
    }

    /**
     * checks whether the Database contains Projects or not.
     * If the Database is Empty it displays custom Message or it will
     * Deletes Project from the Database.
     */
    private void deleteProject() {
        try {

            if (projectController.isDbIsEmpty()) {
                logger.info(Constants.NO_EMPLOYEES);
            } else {
                performDeleteProject();
            }
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * Deletes all project or particular project.
     */
    private void performDeleteProject() {
        boolean isValid = false;

        do {
            logger.info(Constants.PROJECT_DELETE_MENU);
            byte choice = getChoice();

            switch (choice) {
                case 1:
                    try {
                        projectController.deleteAllProjects();
                        logger.info(Constants.PROJECTS_DELETED);
                        isValid = true;
                    } catch (EMSException exception) {
                        logger.error(exception.getMessage());
                    }
                    break;

                case 2:
                    isValid = deleteParticularProject(isValid);
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
     * Deletes particular Project that user wants to delete.
     *
     * @param isValid as boolean.
     *
     * @return true only after it deletes particular Project.
     */
    private boolean deleteParticularProject(boolean isValid) {
        int projectId;
        boolean isContinue = false;

        try {

            do {
                projectId = getProjectId();

                if (projectController.isIdExists(projectId)) {
                    projectController.deleteParticularProject(projectId);
                    logger.info(projectId + Constants.PROJECT_DELETED);
                    isValid = true;
                    isContinue = true;
                } else {
                     logger.info(projectId + Constants.ID_NOT_FOUND);
                     isValid = true;
                }
            } while (!isContinue);
        } catch (EMSException exception) {
            logger.error(exception.getMessage());
        }
        return isValid;
    }

    /**
     * Gets choice from the user.
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