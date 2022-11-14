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

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.view.ProjectView;

/**
 * Contains all the statements to be shown to the users.
 * Does operatiosn like create, display, update,
 * delete employees.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 *
 */
public class EmployeeView {

    private EmployeeController employeeController = new EmployeeController();
    private Scanner scanner = new Scanner(System.in);

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
                    isValid = true;
                    break;

                default:
                    System.out.println("\n-----Invalid choice-----\n");
            }
        } while (!isValid); 
    }

    /**
     * Displays menu to the users to choose the operations like
     * create, update, display, delete operations.
     */
    private void showUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n*************************")
                .append("*************************************\n************")
                .append("****** EMPLOYEE MANGEMENT ********************\n***")
                .append("***************************************************")
                .append("******\n 1. CREATE\n 2. DISPLAY\n 3. UPDATE\n")
                .append(" 4. DELETE\n 5. EXIT\n"));
    }

    /**
     * creates employee.
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
     * Displays menu for continuing creating employee or
     * to stop creating employee.
     */
    private void viewCreateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to Continue ?")
                .append("\n-> PRESS 1 to Create Another Employee")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Creates Employee.
     */
    private void createEmployees() {
        int employeeId;

        EmployeeDTO employeeDto = new EmployeeDTO(getEmployeeName(),
                getEmployeeMailId(), getEmployeeContactNumber(),
                getEmployeeSalary(), getEmployeeBirthDate());

        employeeId = employeeController.insertEmployee(employeeDto);
            System.out.print("\n---Employee Created with Id : ");
            System.out.println(employeeId + "---\n");
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
     * Gets Employee mail Id to validate.
     *
     * @return the validated employee's mail Id.
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
     * Gets employee contact number to validate.
     *
     * @return validated employee's contact number.
     */
    private long getEmployeeContactNumber() {
        boolean isValid = false;
        String employeeContactNumber = new String();

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
     * Gets Employee Salary to validate.
     *
     * @return the validated employee's Salary.
     */
    private double getEmployeeSalary() {
        boolean isValid = false;
        String employeeSalary = new String();

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
     * Gets Employees Date Of Birth to validate.
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
     * checks whether the database is empty.
     */
    private void displayEmployee() {
        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n-----No Employee Available-----\n");
        } else {
            performDisplayEmployee();
        }
    }

    /**
     * Displays all employees or particular employee.
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
            displayProjects(employee);
            System.out.println("\n----------------------------\n");
        }
    }

    /**
     * Displays Menu to user to display particular or all employees.
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
     * Displays the particular Employee.
     *
     * @param isValid as boolean.
     *
     * @return true when it displays particular Employee. 
     */
    private boolean displayParticularEmployee(boolean isValid) {
        EmployeeDTO employeeDto = null;
        boolean isContinue = false;
        int employeeId = 0;

        do {
            employeeId = getEmployeeId();
            isValid = employeeController.isIdExists(employeeId); 

            if (isValid) {          
                employeeDto = employeeController
                        .getParticularEmployee(employeeId);
                System.out.println(employeeDto);
                displayProjects(employeeDto);
                isContinue = true;
            } else {
                System.out.print("\n---Employee ID : " + employeeId);
                System.out.print(" not found Try Another Employee ID---\n");
            }
        } while (!isContinue);
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
     * Displays the projects of the employee.
     *
     * @param employee as EmployeeDTO object.
     */
    private void displayProjects(EmployeeDTO employee) {
        if (employee.getProjectList().isEmpty()) {
            System.out.println(" Projects\t     : NOT ASSIGNED");
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
     * else it display custom Message to the user.
     */
    private void updateEmployee() {
        boolean isContinue = false;

        if (employeeController.isDbIsEmpty()) {
            System.out.println("\n-----No Employees Available-----\n");
        } else {

            do {
                performUpdateEmployee();
                isContinue = continueUpdate();
            } while (isContinue); 
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

        do {
            employeeId = getEmployeeId();

            if (employeeController.isIdExists(employeeId)) {

                do {
                    viewUpdateMenu();
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
                            System.out.println("\n-----Invalid Choice-----\n");
                    }
                } while (!isValid);
            } else {
                System.out.print("\n---Employee ID " + employeeId);
                System.out.println(" does not exists---\n");
                isExists = true;
            }
        } while (isExists);
    }

    /**
     * Displays menu to update all the details of an
     * employee or to update single field of an employee.
     */
    private void viewUpdateMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.print(stringBuilder.append("\n --> PRESS 1 to UPDATE ALL")
                .append(" FIELDS\n --> PRESS 2 to UPDATE PARTICULAR FIELD: ")
                .append("\n --> PRESS 3 to ASSIGN PROJECT\n --> PRESS 4 to ")
                .append("UNASSIGN PROJECT \n --> PRESS 5 to Main Menu \n"));
    }

    /**
     * Updates all the fields of an Employee.
     */
    private void updateAllFields(int employeeId) {
        EmployeeDTO employeeDto = employeeController
                .getParticularEmployee(employeeId);
                       
        employeeDto.setEmployeeName(getEmployeeName());
        employeeDto.setEmployeeMailId(getEmployeeMailId(employeeId));
        employeeDto.setEmployeeContactNumber
                (getEmployeeContactNumber(employeeId));
        employeeDto.setEmployeeSalary(getEmployeeSalary());
        employeeDto.setEmployeeDateOfBirth(getEmployeeBirthDate());

        try {
            employeeController.updateEmployee(employeeDto);
            System.out.println("\n---Employee Details Updated---\n");
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
        }
    }

    /**
     * updates the particular field of an employee.
     */
    private void updateParticularField(int employeeId) {
        byte field;
        boolean isContinue = false;

        EmployeeDTO employeeDto = employeeController.
                getParticularEmployee(employeeId);

        do {
            viewParticularUpdateMenu();
            field = getChoice();
            isContinue = selectUpdateField(field, employeeDto, employeeId);
        } while (isContinue);
    }

    /**
     * Displays the fields to update the particular Field of an 
     * employee.
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
                    System.out.println("\n---Employee Details Updated---\n");
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
            viewUpdateMenuToContinue();
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
                    System.out.println("\n-----Invalid Input-----\n");
                    isValid = false;
            }
        } while (!isValid);
        return isContinue;
    }

    /**
     * Displays menu to continue updating Employee or to stop
     * updating employee.
     */
    private void viewUpdateMenuToContinue() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append("\nDo You want to ")
                .append("Update Another Employee?\n--> PRESS 1 to ")
                .append("CONTINUE.\n--> PRESS 2 to STOP."));
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
            System.out.print("Enter the Employee Mail ID\t\t\t: ");
            mailId = scanner.nextLine();

            if (employeeController.validateEmployeeMailId(mailId)) {

                if (employeeController.isMailIdExists(mailId,
                        employeeId)) {
                    System.out.print("\n---" + mailId + " Already Exists" );
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
            System.out.print("Enter the Employee Contact Number\t\t: ");
            contactNumber = scanner.nextLine();

            if (employeeController
                    .validateEmployeeContactNumber(contactNumber)) {

                if (employeeController.isContactNumberExists(Long
                        .parseLong(contactNumber), employeeId)) {
                    System.out.print("\n" + contactNumber + " Already Exists");
                    System.out.println(" Try Another Number\n");
                    isValid = false;
                } else {
                    isValid = true;
                }
            } else {
                System.out.println("\n----Enter a Valid Contact Number---\n");
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

        if (employeeController.isProjectDbIsEmpty()) {
            System.out.println("\n---No Projects Available---\n");
        } else {

            do {
                assignProjects(employeeId);
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
                        isContinue = true;
                }
            } while (isContinue);
        }
    }

    /**
     * Assigns Employees to Project.
     *
     * @param employeeId as int.
     */
    private void assignProjects(int employeeId) {
        boolean isPresent = false;
        int projectId;

        do {
            displayProjects();
            projectId = getProjectId();

            if (employeeController.isAlreadyAssigned(employeeId, projectId)) {
                System.out.print("\n---Project Already Assigned Try");
                System.out.println(" Another Project---\n");
                isPresent = true;
              System.out.println("in assign");
            } else {
                assignEmployeeToProject(employeeId, projectId);
                isPresent = false;
            }
        } while (isPresent);
    }

    /**
     * Checks whether the Employee is Assigned to a project or not.
     *
     * @param employeeId as int.
     * @param projectId as int.
     */
    private void assignEmployeeToProject(int employeeId, int projectId) {
        Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

        EmployeeDTO employeeDto = employeeController
                .getParticularEmployee(employeeId);
        ProjectDTO projectDto = employeeController
                .getParticularProject(projectId);

        projects = employeeDto.getProjectList();
        projects.add(projectDto);
        employeeDto.setProjectList(projects);

        try {            
            employeeController.updateEmployee(employeeDto);
            System.out.println("\n---Project Assinged Successfully---\n");
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
        }
    }

    /**
     * Displays menu to continue Assigning Project.
     */
    private void viewContinueAssignProject() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to Continue ?")
                .append("\n-> PRESS 1 to Assign Another Project to Employee")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Un-assigns employee from project.
     *
     * @param employeeId as int.
     */
    private void unAssignProject(int employeeId) {
        byte choice;
        boolean isContinue = false;

        if (employeeController.isProjectDbIsEmpty()) {
            System.out.println("\n---No Projects Available---\n");
        } else {

            do {
                unAssignProjects(employeeId);
                viewContinueUnAssignProject();
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
     * Un-assigns employee from project.
     *
     * @param employeeId as int.
     */
    private void unAssignProjects(int employeeId) {
        boolean isPresent = false;
        int projectId = 0;

        EmployeeDTO employeeDto = employeeController
                .getParticularEmployee(employeeId);
        Set<ProjectDTO> projects = employeeDto.getProjectList();

        do {            
            System.out.println("\n---Projects ----\n");

            for (ProjectDTO project : projects) {
                 System.out.println(project);
            }
            projectId = getProjectId();

            if (employeeController.isAlreadyAssigned(employeeId, projectId)) {

                for (ProjectDTO project : projects) {

                    if (projectId == project.getProjectId()) {
                        projects.remove(project);
                        break;
                    }
                }
                employeeDto.setProjectList(projects);
                isPresent = unAssignEmployeeFromProject(employeeDto);
            } else {
                System.out.print("\n---Project Not Assigned Try");
                System.out.println(" Another Project---\n");
                isPresent = false;
            }
        } while (!isPresent);       
    }

    /**
     * Displays menu to continue Assigning Project.
     *
     * @param employeeDto as EmployeeDTO object.
     *
     * @return true if project is un-assigned.
     */
    private boolean unAssignEmployeeFromProject(EmployeeDTO employeeDto) {
        boolean isUnAssigned = false;

        try {
            employeeController.updateEmployee(employeeDto);
            System.out.println("\n---Project UnAssinged Successfully---\n");
            isUnAssigned = true;
        } catch(HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
            isUnAssigned  = false;
        }
        return isUnAssigned;
    }

    /**
     * Displays menu to continue Assigning Project.
     */
    private void viewContinueUnAssignProject() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append(" Do you want to Continue ?")
                .append("\n-> PRESS 1 to UnAssign Another Project")
                .append("\n-> PRESS 2 to Main Menu"));
    }

    /**
     * Displays all the project available in the database.
     */
    private void displayProjects() {
        System.out.println("\n---Available Projects---\n");
                
        for (ProjectDTO project : employeeController.getAllProjects()) {
            System.out.println(project);
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

        do {
            try {   
                System.out.print("\nEnter the Project ID\t\t\t\t: ");
                projectId = Integer.parseInt(scanner.nextLine());

                if (employeeController.isProjectIdExists(projectId)) {
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
     * Deletes particular employee.
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

                try {
                    employeeController.deleteParticularEmployee(employeeId);
                    System.out.print("\n---Employee ID " + employeeId);
                    System.out.println(" Deleted---\n");
                    isValid = true;
                    isContinue = true;
                } catch (HibernateException hibernateException) {
                    System.out.println(hibernateException.getMessage());
                }
            } else {
                 System.out.print("\n---Employee ID " + employeeId + " - Id");
                 System.out.print(" not found Try another ID---\n");
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
     * Gets choice as input from the user.
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