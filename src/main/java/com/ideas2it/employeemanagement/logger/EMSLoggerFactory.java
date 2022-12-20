
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */
package com.ideas2it.employeemanagement.logger;

import java.io.File;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.slf4j.SLF4JLoggerContext;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class for Logger.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EMSLoggerFactory {

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
            File file = new File("E:/SPRINGBOOT/employeemanagement/src/main/resources/log4j2.properties");
//            SLF4JLoggerContext loggerContext = (SLF4JLoggerContext)LogManager.getContext(false);
            LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
            context.setConfigLocation(file.toURI());
          loggerFactory = LoggerFactory.getLogger(className);
        }
        return loggerFactory;
    }
}