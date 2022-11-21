/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

import java.util.Scanner;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.employeemanagement.view.ProjectView;
//import org.apache.log4j.xml.DOMConfigurator;

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
   //     DOMConfigurator.configure("resources/log4j.xml");

        boolean isContinue = false;

        do {
            System.out.println(Constants.USER_MENU);

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
                    System.out.println(Constants.THANK_YOU);
                    isContinue = true;
                    break;

                default:
                    System.out.print(Constants.INVALID_CHOICE);
            }
        } while (!isContinue);  
    }

    /**
     * gets choice as input.
     *
     * @return choice.
     */
    private static byte getChoice() {
        byte choice = 0;

        try {
            System.out.print(Constants.ENTER_CHOICE);
            choice = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException numberFormatException ) {
        }
        return choice;
    }
}