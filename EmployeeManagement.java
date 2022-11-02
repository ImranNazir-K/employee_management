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
 * Asks users to select Employee Portal or Project portal.
 * An object created for a public Employee View and Project View Class.
 * calls a method using this object in that class.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.1
 *
 * @since 27-10-2022
 */
public class EmployeeManagement {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        boolean isContinue = false;

        do {
            showUserMenu();

            switch(validateChoice()) {
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
     * Displays Menu to the users to choose the Employee portal
     * or Project portal.
     */
    private static void showUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(stringBuilder.append("\n--> PRESS 1 FOR EMPLOYEE")
                .append(" PORTAL.\n--> PRESS 2 FOR PROJECT PORTAL.")
                .append("\n--> PRESS 3 TO EXIT."));
    }

    /**
     * Validates the choice entered by the user using try and
     * catch block.
     *
     * @return validated choice.
     */
    private static byte validateChoice() {
        byte choice = 0;

        try {
            System.out.print("Enter the choice: ");
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }
}