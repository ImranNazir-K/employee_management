
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */
package com.ideas2it.employeemanagement.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class for Logger.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
public class EmployeeManagementSystemLoggerFactory {

    private static Logger loggerFactory = null;
    
    /**
     * creates connection for Logger only if the connection is null.
     * 
     * @param <T> Name of the class that need to be logged.
     * 
     * @return loggerfactory instance of Logger.
     */
    public static Logger getFactory(Class<?> className) {
        if (null == loggerFactory) {
          loggerFactory = LoggerFactory.getLogger(className);
        }
        return loggerFactory;
    }
}