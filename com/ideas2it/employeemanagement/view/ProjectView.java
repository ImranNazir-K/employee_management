/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.view;

import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.dto.ProjectDTO;

/**
 * An object is created for a public class Project Controller.
 * Contains all the statments to be shown to the users.
 * Contains Private methods to Create, Display, Update,
 * Delete Employees.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class ProjectView {

    private ProjectController projectController = new ProjectController();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Empty Constructor.
     */
    public ProjectView() {
    }

    /**
     * calls a method to Show Menu to the users and performs 
     * Operations like Create, Display, Update and Delete 
     * Project by calling respective methods using Switch Case.
     */
    public void chooseOperation() {
        byte choice;
        boolean isValid = false; 

        do {
            showUserMenu();
            choice = validateChoice();

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

        System.out.println(stringBuilder.append("\n*******************")
                .append("*****************************************")
                .append("\n********************")
                .append(" PROJECT MANGEMENT ")
                .append("********************\n")
                .append("******************************")
                .append("******************************")
                .append("\n 1. CREATE \n 2. DISPLAY \n ")
                .append("3. UPDATE \n 4. DELETE \n")
                .append(" 5. EXIT\n"));
    }

    /**
     * uses switch statements to continue Creating Project or to
     * stop creating Project.
     */
    private void createProject() {
        byte choice;
        boolean isContinue = false;

        do {
            createProjects();
            viewCreateMenu();
            choice = validateChoice();

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
        ProjectDTO projectDto = new ProjectDTO(getProjectName(),
                getProjectDomain(), getProjectDescription());

        if (projectController.insertProject(projectDto)) {
            System.out.println("\n-----Project Created-----\n");
        } else {
            System.out.println("\n-----Error in Creating Project-----\n");
        }
    }

    /**
     * Displays menu to the user  that asks users to continue 
     * Creating Project or to stop creating Project.
     */
    private void viewCreateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to \n")
                .append("Continue ?\n-> PRESS 1 to Create Another Project")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * gets Project Id from the user and validates using Try catch
     * block.
     */
    private int getProjectId() {
        boolean isValid = false;
        int projectId = 0;

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
     * Gets Project Name as input from the user
     * to validate the entered name.
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
     * Gets Project Domain as input from the user
     * to validate the entered Domain.
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
                System.out.println("\n-----Enter a valid Name-----\n");
            } 
        } while (!isValid);
        return (projectDomain.toUpperCase());
    }

    /**
     * Gets Project Decription as input from the user
     * to validate the entered name.
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
                System.out.println("\n-----Enter a valid Name-----\n");
            } 
        } while (!isValid);
        return projectDescription;
    }

    /**
     * checks whether the Database contains Projects or not.
     * If the Database is Empty it displays Custom Message or it will
     * displays the Projects Available in the Database.
     */
    private void displayProject() {
        if (projectController.isDbIsEmpty()) {
            System.out.println("\n-----No Employee Available-----\n");
        } else {
            performDisplayProject();
        }
    }

    /**
     * Displays all Projects or Particular  Project users wants
     * to display.
     */
    private void performDisplayProject() {
        byte choice;
        boolean isValid = false;

        do {
            viewDisplayMenu();
            choice = validateChoice();

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
     * Displays Menu to user with options like displaying particular
     * Project or All Project.
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
     * Displays All the Projects available in the Database;
     */
    public void displayAllProjects() {
        for (ProjectDTO project : projectController
                .getAllProjects()) {
            System.out.println(project);
        }
    }

    /**
     * Displays the particular Project user wants to Read.
     *
     * @return true when it displays particular Project. 
     */
    private boolean displayParticularProject(boolean isValid) {
        boolean isContinue = false;
        int projectId = 0;

        do {
            projectId = getProjectId();
            isValid = projectController.isIdExists(projectId); 

            if (isValid) {          
                System.out.println(projectController.
                    getParticularProject(projectId));
                isValid = true;
                isContinue = true;
            } else {
                System.out.print("\n---Project ID : " + projectId + " not ");
                System.out.print("found Try Another Project ID---\n");
                isContinue = false;
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * Updates the Project by checking whether the Database is empty
     * or not. If Database is not Empty it updates Project.
     * If Database is empty it display custom Message to the user.
     */
    private void updateProject() {
        boolean isValid = false;

        if (projectController.isDbIsEmpty()) {
            System.out.println("\n-----No Projects Available-----\n");
        } else {

            do {
                viewUpdateMenu();
                performUpdateProject();
                isValid = continueUpdate();
            } while (isValid); 
        }
    }

    /**
     * Displays menu that asks users to update all the details of an
     * Particular project or to update single detail of an Particular
     * project.
     */
    private void viewUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 to")
                .append(" update All data\n")
                .append(" --> PRESS 2 to Update Particular field: ")
                .append("\n --> PRESS 3 to Main Menu \n"));
    }

    /**
     * Update All the Details of an Project and particular details
     * of an Project
     */
    private void performUpdateProject() {
        byte choice;
        byte field;
        boolean isValid = false;

        do {
            choice = validateChoice();

            switch (choice) {
                case 1:
                    updateAllFields();
                    isValid = true;
                    break;

                case 2:
                    updateParticularField(); 
                    isValid = true;
                    break;

                default:
                    System.out.println("\n-----Invalid Choice-----\n");
                    isValid = false;
            }
        } while (!isValid);
    }

    /**
     * updates all the fields of an Project If Database is contains
     * that entered Project Id.
     * If that Project ID is not present the Database it display
     * custom Message to the user.
     */
    private void updateAllFields() {
        int projectId;
        boolean isValid = false;

        do {
            projectId = getProjectId();

            if (projectController.isIdExists(projectId)) {
               ProjectDTO projectDto = projectController
                       .getParticularProject(projectId);
               updateProject(projectDto);
               isValid = true;
            } else {
               System.out.print("\n---Project ID " + projectId);
               System.out.println(" does not exists---\n");
               isValid = false;
            }
        } while (!isValid);
    }

    /**
     * Updates all the fields of an Project.
     *
     * @param projectDto as ProjectDTO object.
     */
    private void updateProject(ProjectDTO projectDto) {

        projectDto = new ProjectDTO(getProjectName(), getProjectDomain(), getProjectDescription());

        if (projectController.updateProject(projectDto)) {
            System.out.println("\n-----Project Details Updated-----\n");
        } else {
            System.out.println("\n-----Error in Updating Project-----\n");
        }
    }

    /**
     * updates the particular field of an Project.
     */
    private void updateParticularField() {
        byte field;
        int projectId;
        boolean isContinue = false;
        boolean isValid = false;

        do {
            projectId = getProjectId();

            if (projectController.isIdExists(projectId)) {
                ProjectDTO projectDto = projectController
                        .getParticularProject(projectId);

                do {
                    viewParticularUpdateMenu();
                    field = validateChoice();
                    isContinue = selectUpdateField(field, projectDto,
                            isContinue);
                } while (isContinue);
                isValid = true;
            } else {
                System.out.print("\n---Project ID " + projectId);
                System.out.println(" does not exists---\n");
                isValid = false;
            }
        } while (!isValid);
    }

    /**
     * Displays Menu with list of fields to the user to update the
     * particular Field of an particular Project.
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
     * Chooses the particular field of an particular Project that
     * users wants to update.
     *
     * @param field as byte.
     * @param projectDto object as ProjectDTO.
     * @param isContinue as boolean.
     *
     * @return true when user wants to exit.
     * @return false until user wants to exit.
     */
    private boolean selectUpdateField(byte field, ProjectDTO projectDto,
                                      boolean isContinue) {
        int count = 0;
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
                if (projectController.updateProject(projectDto)) {
                    System.out.println("\n---Project Details Updated---\n");
                } else {
                        System.out.println("\n-Error in Updating Project-\n");
                    }
                isContinue = false;
                break;

            default:
                System.out.println("\n-----Invalid Choice-----\n");                
                break;
        }
        return isContinue;
    }

    /**
     * updates the Project Name of the particular Project.
     *
     * @param projectDto object as ProjectDTO.
     *
     */
    private void updateProjectName(ProjectDTO projectDto) {
        projectDto.setProjectName(getProjectName());
    }

    /**
     * updates the  Domain of the particular Project.
     *
     * @param projectDto object as ProjectDTO.
     *
     */
    private void updateProjectDomain(ProjectDTO projectDto) {
        projectDto.setProjectDomain(getProjectDomain());
    }

    /**
     * updates the Project Description of the particular Project.
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
        boolean isValid = false;
        boolean isContinue = false;

        do {
            viewUpdateMenuToContinue();
            choice = validateChoice();

            switch (choice) {
                case 1:
                    isValid = true;
                    isContinue = true;
                    break;

                case 2:
                    isValid = false;
                    isContinue = true;
                    break;

                case 3:
                   isContinue = true;

                default:
                    System.out.println("\n-----Invalid Input-----\n");
                    isContinue = false;
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * Displays menu to the user that contains statements that asks
     * users to continue updating Project or to stop updating
     * employee.
     */
    private void viewUpdateMenuToContinue() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append("Do You want to ")
                .append("Update Another Project?\n--> PRESS 1 to ")
                .append("CONTINUE.\n--> PRESS 2 to STOP."));
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
     * Deletes all Project or Particular Project users wants
     * to delete.  
     */
    private void performDeleteProject() {
        boolean isValid = false;

        do {
            viewDeleteMenu();
            byte choice = validateChoice();

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
     * @return true only after it deletes particular Project.
     */
    private boolean deleteParticularProject(boolean isValid) {
        int projectId;
        boolean isContinue = false;

        do {
            projectId = getProjectId();

            if (projectController.isIdExists(projectId)) {

                if (projectController.deleteParticularProject(projectId)) {
                    System.out.println("\n---Project ID " + projectId);
                    System.out.println(" Deleted---\n");
                    isValid = true;
                    isContinue = true;
                } else {
                        System.out.println("\n-Error in Deleting Project-\n");
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
     * Displays Menu for Deleting All Projects or particular
     * Project to the user.
     */
    private void viewDeleteMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 ")
                .append("to Delete All Project Details ")
                .append("\n --> PRESS 2 to Delete Particular Project ")
                .append("Detail: \n --> Press 3 to Main Menu \n"));
    }

    /**
     * Validates the choice entered by the user using try and
     * catch block.
     *
     * @return validated choice.
     */
    private byte validateChoice() {
        byte choice = 0;

        try {
            System.out.print("Enter the choice: ");
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }   
}