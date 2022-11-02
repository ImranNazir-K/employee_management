/*
 * Copyright (c) 2022, Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.view;

import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.view.ProjectView;

/**
 * An object is created for a public class Employee Controller.
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
public class EmployeeView {

    private EmployeeController employeeController = new EmployeeController();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Empty constructor.
     */ 
    public EmployeeView() {
    }

    /**
     * calls a method to Show Menu to the users and performs 
     * Operations like Create, Display, Update and Delete 
     * Employee by calling respective methods using Switch Case.
     */
    public void chooseOperation() {
        byte choice;
        boolean isValid = false; 

        do {
            showUserMenu();
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
                    assignProject();
                    break;

                case 6:
                    isValid = true;
                    break;

                default:
                    System.out.println("\n-----Invalid choice-----\n");
            }
        } while (!isValid); 
    }

    /**
     * Displays Menu to the users to choose the operations like
     * create, Update, Display, Delete operations
     */
    private void showUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n*******************")
                .append("*****************************************")
                .append("\n********************")
                .append(" EMPLOYEE MANGEMENT ")
                .append("********************\n")
                .append("******************************")
                .append("******************************")
                .append("\n 1. CREATE\n 2. DISPLAY\n ")
                .append("3. UPDATE\n 4. DELETE \n 5. ASSIGN PROJECT\n")
                .append(" 6. EXIT\n"));
    }

    /**
     * Calls Create Employee method to create Employee.
     * uses switch statements to continue Creating Employee or to
     * stop creating employee.
     */
    private void createEmployee() {
        byte choice;
        boolean isContinue = false;

        do {
            createEmployees();
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
     * Displays menu to the user that contains statements that asks
     * users to continue Creating Employee or to stop creating employee.
     */
    private void viewCreateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to Continue ?")
                .append("\n-> PRESS 1 to Create Another Employee")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Creates Employees.
     */
    private void createEmployees() {
        EmployeeDTO employeeDto = new EmployeeDTO(getEmployeeName(),
                getEmployeeMailId(), getEmployeeContactNumber(),
                getEmployeeSalary(), getEmployeeBirthDate());

        if (employeeController.insertEmployee(employeeDto)) {
            System.out.println("\n-----Employee Created-----\n");
        } else {
            System.out.println("\n-----Error in Creating Employee-----\n");
        }
    }

    /**
     * gets Employee Id from the user and validates using Try catch
     * block.
     */
    private int getEmployeeId() {
        boolean isValid = false;
        int employeeId = 0;

        do {
            try {   
                System.out.print("\nEnter the Employee ID\t\t\t\t: ");
                employeeId = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("\n-----Invalid Input Format-----\n");
                isValid = true;
            }
        } while (!isValid);
        return employeeId;
    }

    /**
     * Gets Employee Name as input from the user
     * to validate the entered name.
     *
     * @return validated employee's Name.
     */
    private String getEmployeeName() {
        String employeeName = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Name\t\t\t\t: ");
            employeeName = scanner.nextLine();
            isValid = employeeController.validateEmployeeName(employeeName);

            if (!isValid) {
                System.out.println("\n-----Enter a valid Name-----\n");
            } 
        } while (!isValid);
        return (employeeName.toUpperCase());
    } 

    /**
     * Gets Employee Contact Number as input from the user to
     * validate the entered Contact Number.
     *
     * @return validated employee's Contact Number.
     */
    private long getEmployeeContactNumber() {
        String employeeContactNumber = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Contact Number\t\t: ");
            employeeContactNumber = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeContactNumber(employeeContactNumber);

            if (!isValid) {
                System.out.println("\n----Enter a valid Contact Number----\n");
            } else if (employeeController
                    .isContactNumberExists(employeeContactNumber)) {
                System.out.print("\n" + employeeContactNumber);
                System.out.println(" Already Exists Try Another Number\n");
                isValid = false;
            }
        } while (!isValid);
        return Long.parseLong(employeeContactNumber);
    } 

    /**
     * Gets Employee Salary as input from the user to
     * validate the entered salary.
     *
     * @return the validated employee's Salary.
     */
    private double getEmployeeSalary() {
        String employeeSalary = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Salary\t\t\t: ");
            employeeSalary = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeSalary(employeeSalary);

            if (!isValid) {
                System.out.println("\n-----Enter a valid salary-----\n");
            }
        } while (!isValid);
        return Double.parseDouble(employeeSalary);
    }

    /**
     * Gets Employee Email Id as input from the user to
     * validate the entered Email Id.
     *
     * @return the validated employee's Email Id.
     */
    private String getEmployeeMailId() {
        String employeeMailId = new String();
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Mail ID\t\t\t: ");
            employeeMailId = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeMailId(employeeMailId);

            if (!isValid) {
                System.out.println("\n-----Enter Valid Email ID-----\n");
            } 
 
            if (employeeController.isMailIdExists(employeeMailId)) {
                System.out.print("\n" + employeeMailId);
                System.out.println(" Already Exists Try Another MailId\n");
                isValid = false;
            }
        } while (!isValid);
        return (employeeMailId.toLowerCase());
    } 

    /**
     * Gets Employees Date Of Birth as input from the user to
     * validate the entered Date Of Birth.
     *
     * @return the validated employee's dateOfBirth.
     */
    private LocalDate getEmployeeBirthDate() {
        boolean isValid = false;
        String dateOfBirth = new String();

        do {
            System.out.print("Enter the Date Of Birth\t\t\t\t: ");
            dateOfBirth = scanner.nextLine();

            isValid = employeeController
                    .validateEmployeeBirthDate(dateOfBirth);

            if (!isValid) {
                System.out.println("\n-----Enter a valid date-----\n");
            }
        } while (!isValid);
        return LocalDate.parse(dateOfBirth);
    }

    /**
     * checks whether the Database contains Employees or not.
     * If the Database is Empty it displays Custom Message or it will
     * displays the employee.
     */
    private void displayEmployee() {
        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n-----No Employee Available-----\n");
        } else {
            performDisplayEmployee();
        }
    }

    /**
     * Displays all Employees or Particular Employee users wants
     * to display.
     */
    private void performDisplayEmployee() {
        byte choice;
        boolean isValid = false;

        do {
            viewDisplayMenu();
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
                    System.out.print("\n-----Invalid Input-----\n");
                    isValid = true;
            }
        } while (isValid);
    }

     /**
     * Displays All Employees available in the Database.
     */ 
    private void displayAllEmployees() {
        for (EmployeeDTO employee : employeeController
                .getAllEmployees()) {
            System.out.println(employee);
            displayProject(employee);
        }
    }

    /**
     * Displays Menu to user with options like displaying particular
     * or All Employees.
     */
    private void viewDisplayMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n --> PRESS 1 to ")
                .append("print ALL Employee Details,")
                .append("\n --> PRESS 2 to print")
                .append(" Particular Employee Detail: ")
                .append("\n --> PRESS 3 to Main Menu\n"));
    }

    /**
     * Displays the particular Employee user wants to Read.
     *
     * @return true when it displays particular Employee. 
     */
    private boolean displayParticularEmployee(boolean isValid) {
        EmployeeDTO employeeDto;
        boolean isContinue = false;
        int employeeId = 0;

        do {
            employeeId = getEmployeeId();
            isValid = employeeController.isIdExists(employeeId); 

            if (isValid) {          
                employeeDto = employeeController
                        .getParticularEmployee(employeeId);
                System.out.println(employeeDto);
                displayProject(employeeDto);
                isContinue = true;
            } else {
                System.out.print("\n---Employee ID : " + employeeId);
                System.out.print(" not found Try Another Employee ID---\n");
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * 
     */
    private void displayProject(EmployeeDTO employee) {
        if (employee.getProjectList().isEmpty()) {
            System.out.println(" Projects\t     : NULL");
        } else {
            System.out.print("\n---Projects---\n");

            for (ProjectDTO project : employee.getProjectList()) {
                System.out.println(project);
            }
        }
    }

    /**
     * Updates the employee by checking whether the Database is
     * empty or not. If Database is not Empty it updates Employee.
     * If Database is empty it display custom Message to the user.
     */
    private void updateEmployee() {
        boolean isValid = false;

        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n-----No Employees Available-----\n");
        } else {

            do {
                viewUpdateMenu();
                performUpdateEmployee();
                isValid = continueUpdate();
            } while (isValid); 
        }
    }

    /**
     * Displays menu that asks users to update all the details of an
     * Particular employee or to update single detail of an Particular
     * employee.
     */
    private void viewUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 to")
                .append(" update All data\n")
                .append(" --> PRESS 2 to Update Particular field: ")
                .append("\n --> PRESS 3 to Main Menu \n"));
    }

    /**
     * Update All the Details of an employee and particular details
     * of an employee.
     */
    private void performUpdateEmployee() {
        byte choice;
        byte field;
        boolean isValid = false;

        do {
            choice = getChoice();

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
     * updates all the fields of an employee If Database contains
     * that entered Employee Id.
     * If that Employee ID is not present the Database it display
     * custom Message to the user.
     */
    private void updateAllFields() {
        int employeeId;
        boolean isValid = false;

        do {
            employeeId = getEmployeeId();

            if (employeeController.isIdExists(employeeId)) {
               EmployeeDTO employeeDto = employeeController
                       .getParticularEmployee(employeeId);
               updateEmployees(employeeDto,employeeId);
               isValid = true;
            } else {
               System.out.print("\n---Employee ID " + employeeId);
               System.out.println(" does not exists---\n");
               isValid = false;
            }
        } while (!isValid);
    }

    /**
     * Updates all the fields of an Employees.
     */
    private void updateEmployees(EmployeeDTO employeeDto, int employeeId) {

        employeeDto = new EmployeeDTO(getEmployeeName(), getMailId(employeeId),
                getContactNumber(employeeId), getEmployeeSalary(),
                getEmployeeBirthDate());

        if (employeeController.updateEmployee(employeeDto)) {
            System.out.println("\n-----Employee Details Updated-----\n");
        } else {
            System.out.println("\n-----Error in Updating Employee-----\n");
        }
    }

    /**
     * updates the particular field of an employee.
     */
    private void updateParticularField() {
        byte field;
        int employeeId;
        boolean isContinue = false;
        boolean isValid = false;

        do {
            employeeId = getEmployeeId();

            if (employeeController.isIdExists(employeeId)) {
                EmployeeDTO employeeDto = employeeController.
                        getParticularEmployee(employeeId);

                do {
                    viewParticularUpdateMenu();
                    field = getChoice();
                    isContinue = selectUpdateField(field, employeeDto,
                            isContinue,employeeId);
                } while (isContinue);
                isValid = true;
            } else {
                System.out.print("\n Employee ID " + employeeId + " does");
                System.out.println("not exists\n");
                isValid = false;
            }
        } while (!isValid);
    }

    /**
     * Displays Menu with list of fields to the user to update the
     * particular Field of an particular employee.
     */
    private void viewParticularUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\nEnter\n  -->")
                .append(" 1 for NAME. \n")
                .append("  --> 2 for MAIL ID. \n")
                .append("  --> 3 for CONTACT NUMBER. \n")
                .append("  --> 4 for SALARY. \n")
                .append("  --> 5 for DATE OF BIRTH ")
                .append("\n  --> 6 to Exit "));
    }

    /**
     * Chooses the particular field of an particular Employee that
     * users wants to update.
     *
     * @param field as byte.
     * @param employeeDto as EmployeeDTO object.
     * @param employeeId as int.
     * @param isContinue as boolean.
     *
     * @return true when user wants to exit.
     * @return false until user wants to exit.
     */
    private boolean selectUpdateField(byte field, EmployeeDTO employeeDto,
            boolean isContinue, int employeeId) {

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
                if (employeeController.updateEmployee(employeeDto)) {
                    System.out.println("\n---Employee Details Updated---\n");
                } else {
                    System.out.println("\n---Error in Updating Employee---\n");
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
     * updates the name of the particular employee by getting 
     * input from the user to update in the Database.
     *
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeName(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeName(getEmployeeName());
    }

    /**
     * updates the Email Id of the particular employee by getting 
     * input from the user to update in the Database.
     *
     * @param employeeId as int.
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeMailId(EmployeeDTO employeeDto, int employeeId) {
        String mailId = getMailId(employeeId);
        employeeDto.setEmployeeMailId(mailId);
    }

    /**
     * updates the Contact Number of the particular employee by
     * getting input from the user to update in the Database.
     *
     * @param employeeId as int.
     * @param employeeDto as EmployeeDTO object.
     *
     */
    private void updateEmployeeContactNumber(EmployeeDTO employeeDto, int employeeId) {
        long phoneNumber = getContactNumber(employeeId);
        employeeDto.setEmployeeContactNumber(phoneNumber);
    }

    /**
     * updates the Salary of the particular employee by getting 
     * input from the user to update in the Database.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    private void updateEmployeeSalary(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeSalary(getEmployeeSalary());
    }

    /**
     * updates the Date Of Birth of the particular employee by getting 
     * input from the user to update in the Database.
     *
     * @param employeeDto as EmployeeDTO object.
     */
    private void updateEmployeeBirthDate(EmployeeDTO employeeDto) {
        employeeDto.setEmployeeDateOfBirth(getEmployeeBirthDate());
    }

    /**
     * Continues to Update another Employee if Users wants to update.
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
            choice = getChoice();

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
     * users to continue updating Employee or to stop updating
     * employee.
     */
    private void viewUpdateMenuToContinue() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append("Do You want to ")
                .append("Update Another Employee?\n--> PRESS 1 to ")
                .append("CONTINUE.\n--> PRESS 2 to STOP."));
    }

    /**
     * checks whether the Database contains Employees or not.
     * If the Database is Empty it displays custom Message or it will
     * Deletes Employee from the Database.
     */
    private void deleteEmployee() {
        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n-----No Employees Available-----\n");
        } else {
            performDeleteEmployee();
        }
    }

    /**
     * Deletes all Employees or Particular Employee users wants
     * to delete.  
     */
    private void performDeleteEmployee() {
        boolean isValid = false;

        do {
            viewDeleteMenu();
            byte choice = getChoice();

            switch (choice) {
                case 1:
                    if (employeeController.deleteAllEmployees()) {
                        System.out.println("\n-----Employees Cleared-----\n");
                    } else {
                        System.out.println("\n-Error in Deleting Employee-\n");
                    }
                    isValid = true;
                    break;

                case 2:
                    isValid = deleteParticularEmployee(isValid);
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
     * Deletes particular employee that user wants to delete.
     *
     * @param isValid as boolean.
     *
     * @return true only after it deletes particular Employee.
     */
    private boolean deleteParticularEmployee(boolean isValid) {
        int employeeId;
        boolean isContinue = false;

        do {
            employeeId = getEmployeeId();

            if (employeeController.isIdExists(employeeId)) {

                if (employeeController.deleteParticularEmployee(employeeId)) {
                    System.out.print("\n---Employee ID " + employeeId);
                    System.out.println(" Deleted---\n");
                    isValid = true;
                    isContinue = true;
                } else {
                        System.out.println("\n-Error in Deleting Employee-\n");
                    }
            } else {
                 System.out.print("\n---nEmployee ID " + employeeId + " - Id");
                 System.out.print(" not found Try another ID---\n");
                 isContinue = false;
            }
        } while (!isContinue);
        return isValid;
    }

    /**
     * Displays Menu for Deleting All employees or particular
     * Employee to the user.
     */
    private void viewDeleteMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 ")
                .append("to Delete All Employee Details ")
                .append("\n --> PRESS 2 to Delete Particular Employee ")
                .append("Detail: \n --> Press 3 to Main Menu \n"));
    }

    /**
     * gets employee MailId from the user.
     *
     * @param employeeId as int.
     *
     * @return mailId.
     */
    private String getMailId(int employeeId) {
        String mailId;
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Mail ID\t\t\t: ");
            mailId = scanner.nextLine();

            if (employeeController.validateEmployeeMailId(mailId)) {

                if (employeeController.isMailExists(mailId,
                        employeeId)) {
                    System.out.print("\n---" + mailId + "Already Exists" );
                    System.out.println(" Try Another Mail Id---\n");
                    isValid = false;
                } else {
                    isValid = true;
                }
            } else {
                System.out.println("\n----Invalid Email Id----\n");
                isValid = false;
            }
        } while (!isValid);
        return (mailId.toLowerCase());
    }

    /**
     * gets employee Contact number from the user.
     *
     * @param employeeId as int.
     *
     * @return phoneNumber.
     */
    private long getContactNumber(int employeeId) {
        String phoneNumber;
        boolean isValid = false;

        do {
            System.out.print("Enter the Employee Contact Number\t\t: ");
            phoneNumber = scanner.nextLine();

            if (employeeController.validateEmployeeContactNumber(phoneNumber)) {

                if (employeeController.isPhoneNumberExists(Long
                        .parseLong(phoneNumber), employeeId)) {
                    System.out.print("\n" + phoneNumber + " Already Exists ");
                    System.out.println("Try Another Number\n");
                    isValid = false;
                } else {
                    isValid = true;
                }
            } else {
                System.out.println("\n----Enter a Valid Contact Number---\n");
                isValid = false;
            }
        } while (!isValid);
        return Long.parseLong(phoneNumber);
    }

   /**
     * Assigns Employees to Project.
     */
    private void assignProject() {
        ProjectController projectController = new ProjectController();
        byte choice;
        boolean isContinue = false;

        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n---No Employees Available---\n");
        } else if (projectController.isDbIsEmpty()) {
            System.out.println("\n---No Projects Available---\n");
        } else {
            do {
                assignProjects();
                viewContinueAssignProject();
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
                }
            } while (isContinue);
        }
    }

    /**
     * Assigns Employees to Project.
     */
    private void assignProjects() {
        ProjectView projectView = new ProjectView();

        int employeeId;
        int projectId;
        boolean isPresent= false;

        do {
            System.out.println("\n---Available Employees---\n");
            displayAllEmployees();
            employeeId = getEmployeeId();

            if (employeeController.isIdExists(employeeId)) {
                System.out.println("\n---Available Projects---\n");
                projectView.displayAllProjects();
                projectId = getProjectId();
                
                if (checkRedundancy(employeeId, projectId)) {
                    isPresent = true;
                } else {
                   assignEmployeeToProject(employeeId, projectId);
                    isPresent = false;
                }
            } else {
                System.out.println("\n---Employee Id " + employeeId);
                System.out.print(" does not exists---\n");
                isPresent = true;
            }
        } while (isPresent);
    }

    /**
     * checks the Database whether the Project is Already Assigned or not.
     *
     * @return true if the Project is already assigned.
     *
     * @return false if the Project is not assigned.
     */
    private boolean checkRedundancy(int employeeId, int projectId) {
        boolean isPresent = false;

        if (employeeController.checkRedundancy(employeeId, projectId)) {
            System.out.print("\n---Project Already Assigned Try");
            System.out.println(" Another Project---\n");
            isPresent = true;
        }
        return isPresent;
    }

    /**
     * Checks whether the Employee is Assigned to a project or not.
     */
    private void assignEmployeeToProject(int employeeId, int projectId) {
        if (employeeController.assignEmployeeToProject(employeeId, projectId)) {
            System.out.println("\n---Employee Assinged Successfully---\n");
        } else {
            System.out.println("\n---Error in Assinging Employee---\n");
        }
    }

    /**
     * Displays menu to the user that contains statements that asks
     * users to continue Assigning Project.
     */
    private void viewContinueAssignProject() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to Continue ?")
                .append("\n-> PRESS 1 to Assign Another Project to Employee")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Gets Project ID of an Employee as input from the user to
     * validate the entered Date Of Birth.
     *
     * @return the validated employee's project Id.
     */
    private int getProjectId() {
        ProjectController projectController = new ProjectController();
        int projectId = 0;
        boolean isValid = false;

        do {
            try {   
                System.out.print("\nEnter the Project ID\t\t\t\t: ");
                projectId = Integer.parseInt(scanner.nextLine());

                if (projectController.isIdExists(projectId)) {
                    isValid = true;
                } else {
                    System.out.print("\n---Project Id " + projectId);
                    System.out.println(" does not exists---\n");
                    isValid = false;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("\n-----Invalid Input Format-----\n");
                isValid = false;
            }
        } while (!isValid);
        return projectId;
    }

    /**
     * Validates the choice entered by the user using try and
     * catch block.
     *
     * @return validated choice.
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

