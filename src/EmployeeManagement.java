/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

import java.util.Scanner;

import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.employeemanagement.view.ProjectView;

/**
 * performs CRUD operations for employees and projects.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeManagement {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        boolean isContinue = false;

        do {
            showUserMenu();

            switch(getChoice()) {
                case 1:
                    EmployeeView employeeView = new EmployeeView(); 
                    employeeView.chooseOperation(); 
                    break;

                case 2:
                    ProjectView projectView = new ProjectView();
                    projectView.chooseOperation();
                    break;

                case 3:
                    System.out.println("\n-----Thank You-----");
                    isContinue = true;
                    break;

                default:
                    System.out.print("\n-----Invalid Choice-----\n");
            }
        } while (!isContinue);  
    }

    /**
     * Displays Menu to the users to choose the employee portal
     * or project portal.
     */
    private static void showUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n--> PRESS 1 FOR EMPLOYEE")
                .append(" PORTAL.\n--> PRESS 2 FOR PROJECT PORTAL.")
                .append("\n--> PRESS 3 TO EXIT."));
    }

    /**
     * gets choice as input.
     *
     * @return choice.
     */
    private static byte getChoice() {
        byte choice = 0;

        try {
            System.out.print("Enter the choice: ");
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }
}