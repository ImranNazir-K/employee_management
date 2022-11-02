/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates Connection for JDBC and closes that connection.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class DbConnection {

    private static Connection connection = null;
    private static String dbUrl = "jdbc:mysql://localhost:3306/employee_management";
    private static String password = "thor";
    private static String user = "root";

    /**
     * empty constructor.
     */
    private DbConnection() {
    }

    /**
     * creates Connection for JDBC only if the connection is null
     * or it is closed.
     */
    public static Connection getConnectionInstance() {
        try {
            if (null == connection || connection.isClosed() ) {
                connection = DriverManager.getConnection(dbUrl,user,password);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * closes the connection of JDBC.
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
   
}