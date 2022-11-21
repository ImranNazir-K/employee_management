
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
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * class for Logger.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class LoggerFactory {

    private static Logger loggerFactory = null;
    /**
     * creates connection for database only if the connection is null.
     */
    public static Logger getFactory() {
        if (null == loggerFactory) {
            File file = new File("E:/employee_management/src/resources/log4j2.xml");
            LoggerContext loggerContext = (LoggerContext)LogManager.getContext(false);
            loggerContext.setConfigLocation(file.toURI());
            loggerFactory = LogManager.getLogger(LoggerFactory.class);
        }
        return loggerFactory;
    }
}