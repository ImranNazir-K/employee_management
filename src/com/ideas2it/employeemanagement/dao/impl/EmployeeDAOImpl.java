/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.SessionFactory; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.hibernateconnection.HibernateConnection;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;


/**
 * class for performing insert, update, delete, retrieve
 * projects in the database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    public EmployeeDAOImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertEmployee(Employee employee) {
        Transaction transaction = null;
        int employeeId = 0;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employeeId = (Integer)session.save(employee);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }       
        return employeeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees() {
        Transaction transaction = null;
        List<Employee> employeeList = new ArrayList<Employee>();

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employeeList = session.createQuery("FROM Employee").list();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            hiberateException.printStackTrace();
        }
        return employeeList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Employee getParticularEmployee (int employeeId) {
        Employee employee = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, employeeId);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            hiberateException.printStackTrace();
        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new HibernateException("\n--Error in Updating Employee--\n");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getAllEmployeeId() {
        Transaction transaction = null;
        List<Integer> employeeIds = new ArrayList<Integer>();
        String hqlQuery = "select E.employeeId from Employee E";

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employeeIds = session.createQuery(hqlQuery).list();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }
        return employeeIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> getContactNumber(int employeeId) {
        Transaction transaction = null;
        List<Long> contactNumbers = new ArrayList<Long>();
        String hqlQuery = "select E.employeeContactNumber from Employee E"
                + " where E.employeeId !=: id";

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            contactNumbers = session.createQuery(hqlQuery).setParameter("id",
                    employeeId).list();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }
        return contactNumbers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMailId(int employeeId) {
        Transaction transaction = null;
        List<String> mailIds = new ArrayList<String>();
        String hqlQuery = "select E.employeeMailId from Employee E where "
                + "E.employeeId !=: id";

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            mailIds = session.createQuery(hqlQuery).setParameter("id",
                    employeeId).list();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }
        return mailIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteAllEmployees() {
        Transaction transaction = null;
        int result = 0;
        String hqlQuery = "DELETE FROM Employee";

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            result = session.createQuery(hqlQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        } 
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularEmployee(int employeeId) {
        Employee employee = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employee = (Employee)session.get(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new HibernateException("\n-Error in Deleting Employee-\n");
        }
    }
}   