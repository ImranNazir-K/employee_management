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

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;

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

    private ProjectController projectController = new ProjectController();
    private Scanner scanner = new Scanner(System.in);

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
            showUserMenu();
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
                    System.out.println("\n-----Invalid choice-----\n");
            }
        } while (!isValid); 
    }

    /**
     * Displays Menu to the users to choose the operations like
     * create, Update, Display, Delete operations.
     */
    private void showUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n*************************")
                .append("*************************************\n************")
                .append("****** PROJECT MANGEMENT ********************\n***")
                .append("***************************************************")
                .append("******\n 1. CREATE\n 2. DISPLAY\n 3. UPDATE\n")
                .append(" 4. DELETE\n 5. EXIT\n"));
    }

    /**
     * Creates project.
     */
    private void createProject() {
        byte choice;
        boolean isContinue = false;

        do {
            createProjects();
            viewCreateMenu();
            choice = getChoice();

            switch (choice) {
                case 1:
                    isContinue = true;
                    break;
            
                case 2:
                    isContinue = false;
                    break;
             
                default:
                    System.out.print("\n-----Invalid Choice-----\n");
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

        projectId = projectController.insertProject(projectDto);
            System.out.print("\n---Project Created with Id : ");
            System.out.println(projectId + "---\n");
    }

    /**
     * Displays menu to continue for creating Project or to stop
     * creating Project.
     */
    private void viewCreateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to \n")
                .append("Continue ?\n-> PRESS 1 to Create Another Project")
                .append("\n-> PRESS 2 to Main Menu"));
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
            System.out.print("\nEnter the Project Name\t\t\t\t: ");
            projectName = scanner.nextLine();
            isValid = projectController.validateProjectName(projectName);

            if (!isValid) {
                System.out.println("\n-----Enter a valid Name-----\n");
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
        String projectDomain = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Project Domain\t\t\t: ");
            projectDomain = scanner.nextLine();
            isValid = projectController.validateProjectDomain(projectDomain);

            if (!isValid) {
                System.out.println("\n-----Enter a valid Domain-----\n");
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
        String projectDescription = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Project Description\t\t\t: ");
            projectDescription = scanner.nextLine();

            isValid = projectController
                    .validateProjectDescription(projectDescription);

            if (!isValid) {
                System.out.println("\n-----Enter a valid Description-----\n");
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
        Integer projectId = 0;

        do {
            try {   
                System.out.print("\nEnter the Project ID\t\t\t\t: ");
                projectId = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("\n-----Invalid Input Format-----\n");
                isValid = true;
            }
        } while (!isValid);
        return projectId ;
    }

    /**
     * checks whether the database contains projects or not.
     * If the database is empty it displays custom message or it will
     * displays the projects available in the database.
     */
    private void displayProject() {
        if (projectController.isDbIsEmpty()) {
            System.out.println("\n-----No Projects Available-----\n");
        } else {
            performDisplayProject();
        }
    }

    /**
     * Displays all projects or particular project.
     */
    private void performDisplayProject() {
        byte choice;
        boolean isValid = false;

        do {
            viewDisplayMenu();
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
                    System.out.print("\n-----Invalid Input-----\n");
                    isValid = true;
            }
        } while (isValid);
    }	

    /**
     * Displays Menu for displaying particular project or all project.
     */
    private void viewDisplayMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n --> PRESS 1 to ")
                .append("print ALL Project Details,")
                .append("\n --> PRESS 2 to print ")
                .append(" Particular Project Detail, ")
                .append("\n --> PRESS 3 to Main Menu\n"));
    }

    /**
     * Displays all the projects available in the Database.
     */
    public void displayAllProjects() {
        System.out.println(projectController.getAllProjects());
        for (ProjectDTO projectDto : projectController.getAllProjects()) {
            System.out.println(projectDto);
            displayEmployees(projectDto);
            System.out.println("\n----------------------------\n");
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
        ProjectDTO projectDto = null;
        boolean isContinue = false;
        int projectId = 0;

        do {
            projectId = getProjectId();
            isValid = projectController.isIdExists(projectId); 

            if (isValid) {          
                projectDto = projectController.getParticularProject(projectId);
                System.out.println(projectDto);
                displayEmployees(projectDto);
                isValid = true;
                isContinue = true;
            } else {
                System.out.print("\n---Project ID : " + projectId + " not ");
                System.out.print("found Try Another Project ID---\n");
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * Assigns project to employee.
     */
    private void displayEmployees(ProjectDTO projectDto) {
        if (projectDto.getEmployeeList().isEmpty()) {
            System.out.println(" Employees\t     : NOT ASSIGNED");
        } else {
            System.out.print("\n---Employees---\n");

            for (EmployeeDTO employee : projectDto.getEmployeeList()) {
                System.out.println(employee);
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

        if (projectController.isDbIsEmpty()) {
            System.out.println("\n-----No Projects Available-----\n");
        } else {

            do {
                performUpdateProject();
                isContinue = continueUpdate();
            } while (isContinue); 
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

        do {
            projectId = getProjectId();

            if (projectController.isIdExists(projectId)) {

                do {
                    viewUpdateMenu();
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
                            System.out.println("\n-----Invalid Choice-----\n");
                    }
                } while (!isValid);
            } else {
                System.out.print("\n---Project ID " + projectId);
                System.out.println(" does not exists---\n");
                isExists = true;
            }
        } while (isExists);
    }

    /**
     * Displays menu for updating all the details of an particular 
     * project or to update single detail of an particular project.
     */
    private void viewUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 to UPDATE ALL")
                .append(" FIELDS \n --> PRESS 2 to UPDATE PARTICULAR FIELD:")
                .append("\n --> PRESS 3 to ASSIGN EMPLOYEE\n --> PRESS 4 to ")
                .append("UNASSIGN EMPLOYEE \n --> PRESS 5 to Main Menu \n"));
    }

    /**
     * updates all the fields of an Project.
     */
    private void updateAllFields(int projectId) {
        ProjectDTO projectDto = projectController
                .getParticularProject(projectId);

        projectDto.setProjectName(getProjectName());
        projectDto.setProjectDomain(getProjectDomain());
        projectDto.setProjectDescription(getProjectDescription());

        try {
            projectController.updateProject(projectDto);
            System.out.println("\n-----Project Details Updated-----\n");
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
        }
    }

    /**
     * updates the particular field of an Project.
     */
    private void updateParticularField(int projectId) {
        byte field;
        boolean isContinue = false;

        ProjectDTO projectDto = projectController
                .getParticularProject(projectId);

        do {
            viewParticularUpdateMenu();
            field = getChoice();
            isContinue = selectUpdateField(field, projectDto);
        } while (isContinue);
    }

    /**
     * Displays Menu to update the particular Field of an particular
     * project.
     */
    private void viewParticularUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\nEnter\n  -->")
                .append(" 1 for PROJECT NAME. \n")
                .append("  --> 2 for PROJECT DOMAIN.\n")
                .append("  --> 3 for PROJECT DESCRIPTION.\n")
                .append("  --> 4 to EXIT."));
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
                    System.out.println("\n---Project Details Updated---\n");
                } catch(HibernateException hibernateException) {
                    System.out.println(hibernateException.getMessage());
                }
                isContinue = false;
                break;

            default:
                System.out.println("\n-----Invalid Choice-----\n"); 
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
            viewUpdateMenuToContinue();
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
                    System.out.println("\n-----Invalid Input-----\n");
                    isValid = false;
            }
        } while (!isValid);
        return isContinue;
    }

    /**
     * Displays menu to the user to continue updating Project or to
     * stop updating employee.
     */
    private void viewUpdateMenuToContinue() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append("\nDo You want to ")
                .append("Update Another Project?\n--> PRESS 1 to ")
                .append("CONTINUE.\n--> PRESS 2 to STOP."));
    }

    /**
     * Assigns project to employee.
     */
    private void assignEmployee(int projectId) {
        byte choice;
        boolean isContinue = false;

        if (projectController.isEmployeeDbIsEmpty()) {
            System.out.println("\n---No Employees Available---\n");
        } else {

            do {
                assignEmployees(projectId);
                viewContinueAssignEmployee();
                choice = getChoice();

                switch (choice) {
                    case 1:
                        isContinue = true;
                        break;

                    case 2:
                        isContinue = false;
                        break;
    
                    default:
                        System.out.println("\n---Invalid Input---\n");
                       isContinue = true;
                }
            } while (isContinue);
        }
    }

    /**
     * Assigns project to employee.
     *
     * @param projectId as int.
     */
    private void assignEmployees(int projectId) {
        int employeeId;
        boolean isPresent= false;

        do {
            displayAvailableEmployees();
            employeeId = getEmployeeId();
                
            if (projectController.isAlreadyAssigned(projectId, employeeId)) {
                System.out.print("\n---Employee Already Assigned Try");
                System.out.println(" Another Employee---\n");
                isPresent = true;
            } else {
                assignProjectToEmployee(projectId, employeeId);
                isPresent = false;
            }
        } while (isPresent);
    }

    /**
     * Checks whether the project is assigned to an employee
     *
     * @param projectId as int.
     * @param employeeId as int.
     */
    private void assignProjectToEmployee(int projectId, int employeeId) {
        Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

        EmployeeDTO employeeDto = projectController
                .getParticularEmployee(employeeId);
        ProjectDTO projectDto = projectController
                .getParticularProject(projectId);

        employees = projectDto.getEmployeeList();
        employees.add(employeeDto);
        projectDto.setEmployeeList(employees);

        try {
            projectController.updateProject(projectDto);
            System.out.println("\n---Employee Assinged Successfully---\n");
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
        }
    }

    /**
     * Displays menu for continue Assigning Project.
     */
    private void viewContinueAssignEmployee() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n Do you want to Continue ?")
                .append("\n-> PRESS 1 to Assign Another Employee to Project")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * UnAssigns project from employee.
     *
     * @param projectId as int.
     */
    private void unAssignEmployee(int projectId) {
        byte choice;
        boolean isContinue = false;

        if (projectController.isDbIsEmpty()) {
            System.out.println("\n---No Projects Available---\n");
        } else {

            do {
                unAssignEmployees(projectId);
                viewContinueUnAssignEmployee();
                choice = getChoice();

                switch (choice) {
                    case 1:
                        isContinue = true;
                        break;

                    case 2:
                        isContinue = false;
                        break;
    
                    default:
                        System.out.println("\n---Invalid Input---\n");
                       isContinue = true;
                }
            } while (isContinue);
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

        ProjectDTO projectDto = projectController
                .getParticularProject(projectId);
        Set<EmployeeDTO> employees = projectDto.getEmployeeList();

        do { 
            System.out.println("\n--- Employees ----\n");

            for (EmployeeDTO employee : employees) {
                System.out.println(employee);
            }
            employeeId = getEmployeeId();

            if (projectController.isAlreadyAssigned(projectId, employeeId)) {

                for (EmployeeDTO employee : employees) {

                    if (employeeId == employee.getEmployeeId()) {
                        employees.remove(employee);
                        break;
                    }
                }
                projectDto.setEmployeeList(employees);
                isPresent = unAssignProjectFromEmployee(projectDto);
            } else {
                System.out.print("\n---Employee Already Assigned Try");
                System.out.println(" Another Employee---\n");
                isPresent = false;
            }
        } while (!isPresent);
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
            System.out.println("\n---Employee Un-Assinged Successfully---\n");
            isUnAssigned = true;
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
            isUnAssigned  = false;
        }
        return isUnAssigned;
    }

    /**
     * Displays menu for continue Assigning Project.
     */
    private void viewContinueUnAssignEmployee() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n Do you want to Continue ?")
                .append("\n-> PRESS 1 to UnAssign Another Employee")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Displays all the employees available in the database.
     */
    private void displayAvailableEmployees() {
        System.out.println("\n---Available Employees---\n");

        for (EmployeeDTO employee : projectController.getAllEmployees()) {
            System.out.println(employee);
        }   
    }

    /**
     * Gets id of an employee.
     *
     * @return employeeId.
     */
    private int getEmployeeId() {
        int employeeId = 0;
        boolean isValid = false;

        do {
            try {   
                System.out.print("\nEnter the Employee ID\t\t\t\t: ");
                employeeId = Integer.parseInt(scanner.nextLine());

                if (projectController.isEmployeeIdExists(employeeId)) {
                    isValid = true;
                } else {
                    System.out.print("\n---Employee Id " + employeeId);
                    System.out.println(" does not exists---\n");
                    isValid = false;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("\n-----Invalid Input Format-----\n");
                isValid = false;
            }
        } while (!isValid);
        return employeeId;
    }

    /**
     * checks whether the Database contains Projects or not.
     * If the Database is Empty it displays custom Message or it will
     * Deletes Project from the Database.
     */
    private void deleteProject() {
        if (projectController.isDbIsEmpty()) {
            System.out.println("\n-----No Employees Available-----\n");
        } else {
            performDeleteProject();
        }
    }

    /**
     * Deletes all project or particular project.
     */
    private void performDeleteProject() {
        boolean isValid = false;

        do {
            viewDeleteMenu();
            byte choice = getChoice();

            switch (choice) {
                case 1:
                    if (projectController.deleteAllProjects()) {
                        System.out.println("\n-----Projects Cleared-----\n");
                    } else {
                        System.out.println("\n-Error in Deleting Project-\n");
                    }
                    isValid = true;
                    break;

                case 2:
                    isValid = deleteParticularProject(isValid);
                    break;

                case 3:
                    isValid = true;
                    break;

                default:
                    System.out.print("\n-----Invalid Choice-----\n");
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

        do {
            projectId = getProjectId();

            if (projectController.isIdExists(projectId)) {

                try {
                    projectController.deleteParticularProject(projectId);
                    System.out.println("\n---Project ID " + projectId);
                    System.out.println(" Deleted---\n");
                    isValid = true;
                    isContinue = true;
                } catch (HibernateException hibernateException) {
                    System.out.println(hibernateException.getMessage());
                }
            } else {
                 System.out.print("\n---Project ID " + projectId);
                 System.out.print(" - Id not found Try another ID---\n");
                 isValid = true;
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * Displays Menu for Deleting all projects or particular project.
     */
    private void viewDeleteMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 ")
                .append("to Delete All Project Details ")
                .append("\n --> PRESS 2 to Delete Particular Project ")
                .append("Detail: \n --> Press 3 to Main Menu \n"));
    }

    /**
     * Gets choice from the user.
     *
     * @return choice as byte.
     */
    private byte getChoice() {
        byte choice = 0;

        try {
            System.out.print("Enter the choice: ");
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }   
}