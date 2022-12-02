
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * Validates all input given by users. Stores the employees in the Database.
 * Does Operations like Create, update, Display, Delete employees.
 *
 * @author name = IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final EmployeeDAO employeeDao = new EmployeeDAOImpl();

    public EmployeeServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createEmployee(EmployeeDTO employeeDto) throws EMSException {
        return employeeDao.createEmployee(Mapper.toEmployee(employeeDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeeDao.displayAllEmployees()) {
            employees.add(Mapper.employeeProjectToDto(employee));
        }
        return employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getParticularEmployee(int employeeId)
            throws EMSException {
        return Mapper.employeeProjectToDto(
                employeeDao.getParticularEmployee(employeeId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllEmployees() throws EMSException {
        employeeDao.deleteAllEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularEmployee(int employeeId) throws EMSException {
        employeeDao.deleteParticularEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(EmployeeDTO employee) throws EMSException {
        employeeDao.updateEmployee(Mapper.dtoToEmployeeProject(employee));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getParticularProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMailIdExists(String mailId) throws EMSException {
        boolean isExists = false;

        for (EmployeeDTO employee : getAllEmployees()) {

            if (mailId.equals(employee.getEmployeeMailId())) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContactNumberExists(long contactNumber)
            throws EMSException {
        boolean isExists = false;

        for (EmployeeDTO employee : getAllEmployees()) {

            if (contactNumber == employee.getEmployeeContactNumber()) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int employeeId) throws EMSException {
        boolean isExists = false;

        if (null != employeeDao.getParticularEmployee(employeeId)) {
            isExists = true;
        }
        return isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getProjects() throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProjectDbIsEmpty() throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.isDbIsEmpty();
    }
    
    /**
     * {@inheritDoc}
     * @throws EMSException 
     */
    @Override
    public boolean isDbIsEmpty() throws EMSException {
        boolean isEmpty = false;
        
        if (employeeDao.displayAllEmployees().isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    @Override
    public boolean isProjectIdExists(int projectId) throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.isIdExists(projectId);
    }   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyAssigned(int employeeId, int projectId)
            throws EMSException {
        boolean isAlreadyAssigned = false;

        EmployeeDTO employee = getParticularEmployee(employeeId);

        if (!(employee.getProjects().isEmpty())) {
            for (ProjectDTO project : employee.getProjects()) {
                if (projectId == project.getProjectId()) {
                    isAlreadyAssigned = true;
                    break;
                }
            }
        }
        return isAlreadyAssigned;
    }
}