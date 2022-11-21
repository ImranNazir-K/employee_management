
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.hibernateconnection;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * Creates connection for session factory.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class HibernateConnection {

    private static SessionFactory factory = null;

    private HibernateConnection() {
    }

    /**
     * creates connection for database only if the connection is null.
     */
    public static SessionFactory getFactory() {
        try {
            if (null == factory) {
                factory = new Configuration().configure("hibernate.cfg.xml")
                        .buildSessionFactory();
            }
        } catch (HibernateException hiberateException) {
            hiberateException.printStackTrace();
        }
        return factory;
    }
}