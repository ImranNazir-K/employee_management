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

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.hibernateconnection.HibernateConnection;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;

/**
 * class for performing insert, update, delete, retrieve
 * projects in the database.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 */
public class ProjectDAOImpl implements ProjectDAO {

    public ProjectDAOImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertProject(Project project) {
        Transaction transaction = null;
        int projectId = 0;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            projectId = (Integer) session.save(project);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }       
        return projectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getAllProjectId() {
        Transaction transaction = null;
        List<Integer> projectIds = new ArrayList<Integer>();

        String hqlQuery = "select E.projectId from Project E";

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            projectIds = session.createQuery(hqlQuery).list();
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            hiberateException.printStackTrace();
        }
        return projectIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProjects() {
        Transaction transaction = null;
        List<Project> projects = new ArrayList<Project>();    

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            projects = session.createQuery("SELECT DISTINCT p FROM Project"
                    + " p LEFT JOIN FETCH p.employeeList",
                    Project.class).list();
            transaction.commit();  
        } catch (HibernateException hiberateException) {
            hiberateException.printStackTrace();
        }
        return projects;
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getParticularProject (int projectId) {
        Project project = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            project = (Project)session.createQuery("SELECT p FROM Project p"
                    + " LEFT JOIN FETCH p.employeeList WHERE p.projectId =:id")
                    .setParameter("id", projectId).uniqueResult();
        } catch (HibernateException hiberateException) {
            hiberateException.printStackTrace();
        }
        return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProject(Project project) {
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new HibernateException("\n--Error in Updating project--\n");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteAllProjects() {
        Transaction transaction = null;
        int result = 0;
        String hqlQuery = "DELETE FROM Project";

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

    /*
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularProject(int projectId) {
        Project project = null;
        Transaction transaction = null;

        try (Session session = HibernateConnection.getFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            project = (Project)session.get(Project.class, projectId);
            session.delete(project);
            transaction.commit();
        } catch (HibernateException hiberateException) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new HibernateException("\n-Error in Deleting Employee-\n");
        }
    }
}