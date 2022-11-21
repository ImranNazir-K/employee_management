
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

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.hibernateconnection.HibernateConnection;
import com.ideas2it.employeemanagement.model.Employee;


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
    public int insertEmployee(Employee employee) throws EMSException {
        int employeeId = 0;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employeeId = (Integer)session.save(employee);
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.CREATE_EMPLOYEE_EXCEPTION);
        }       
        return employeeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees() throws EMSException {
        List<Employee> employees = new ArrayList<Employee>();
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employees = session.createQuery("SELECT DISTINCT e FROM Employee"
                    + " e LEFT JOIN FETCH e.projectList",
                    Employee.class).list();
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DISPLAY_EMPLOYEES_EXCEPTION);
        }
        return employees;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Employee getParticularEmployee (int employeeId) throws EMSException {
        Employee employee = new Employee();
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employee = (Employee) session.createQuery("SELECT e FROM Employee "
                    + "e LEFT JOIN FETCH e.projectList WHERE e.employeeId =:id")
                    .setParameter("id", employeeId).uniqueResult();
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DISPLAY_EMPLOYEE_EXCEPTION);
        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(Employee employee) throws EMSException {
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
            throw new EMSException(Constants.UPDATE_EMPLOYEE_EXCEPTION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> getContactNumber(int employeeId) throws EMSException {
        List<Long> contactNumbers = new ArrayList<Long>();
        Transaction transaction = null;

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
            throw new EMSException(Constants.CONTACT_NUMBER_ERROR);
        }
        return contactNumbers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMailId(int employeeId) throws EMSException {
        List<String> mailIds = new ArrayList<String>();
        Transaction transaction = null;
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
            throw new EMSException(Constants.MAILID_ERROR);
        }
        return mailIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllEmployees() throws EMSException {
        String hqlQuery = "DELETE FROM Employee";
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(hqlQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DELETE_EMPLOYEES_EXCEPTION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularEmployee(int employeeId) throws EMSException {
        Employee employee = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            employee = (Employee)session.load(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DELETE_EMPLOYEE_EXCEPTION);
        }
    }
}   