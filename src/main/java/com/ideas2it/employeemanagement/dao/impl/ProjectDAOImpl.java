
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.hibernateconnection.HibernateConnection;
import com.ideas2it.employeemanagement.model.Project;

/**
 * class for performing insert, update, delete, retrieve projects in the
 * database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 5.0
 */
@Repository
public class ProjectDAOImpl implements ProjectDAO {

    public ProjectDAOImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createProject(Project project) throws EMSException {
        int projectId = 0;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            projectId = (Integer) session.save(project);
            transaction.commit();
        } catch (HibernateException exception) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.CREATE_PROJECT_EXCEPTION);
        }
        return projectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProjects() throws EMSException {
        List<Project> projects = new ArrayList<Project>();
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            projects = session.createQuery("SELECT DISTINCT p FROM Project"
                    + " p LEFT JOIN FETCH p.employees", Project.class).list();
            transaction.commit();
        } catch (HibernateException exception) {

            if (null != transaction) {
                transaction.rollback();
            }
            exception.printStackTrace();
            throw new EMSException(Constants.DISPLAY_PROJECTS_EXCEPTION);
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getParticularProject(int projectId) throws EMSException {
        Project project = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.createQuery("SELECT p FROM Project p"
                    + " LEFT JOIN FETCH p.employees WHERE p.projectId =:id")
                    .setParameter("id", projectId).uniqueResult();
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DISPLAY_PROJECT_EXCEPTION);
        }
        return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProject(Project project) throws EMSException {
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(project);
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.UPDATE_PROJECT_EXCEPTION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllProjects() throws EMSException {
        String hqlQuery = "DELETE FROM Project";
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(hqlQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DELETE_PROJECTS_EXCEPTION);
        }
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularProject(int projectId) throws EMSException {
        Project project = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, projectId);
            session.delete(project);
            transaction.commit();
        } catch (HibernateException hiberateException) {

            if (null != transaction) {
                transaction.rollback();
            }
            throw new EMSException(Constants.DELETE_PROJECT_EXCEPTION);
        }
    }
}