
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.service.impl;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * Validates all input given by users.
 * Stores the employees in the Database.
 * Does Operations like Create, update, Display, Delete employees.
 *
 * @author name = IMRAN NAZIR K
 *
 * @version 4.0
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final EmployeeDAO employeeDao = new EmployeeDAOImpl();

    /**
     * Empty Constructor.
     */
    public EmployeeServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean validateEmployeeName (String employeeName) {
        return (Pattern.matches(Constants.VALIDATE_EMPLOYEE_NAME, employeeName));
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validateEmployeeContactNumber (String contactNumber) {
        return (Pattern.matches(Constants.VALIDATE_EMPLOYEE_CONTACT_NUMBER,
                contactNumber));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmployeeSalary (String employeeSalary) {
        return (Pattern.matches(Constants.VALIDATE_EMPLOYEE_SALARY,
                employeeSalary));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmployeeMailId (String employeeMailId) {
        return (Pattern.matches(Constants.VALIDATE_EMPLOYEE_MAILID,
                employeeMailId));
    }  

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmployeeBirthDate(String employeeBirthDate) {
        LocalDate currentDate;
        boolean isContinue = false;
        int age = 0; 
        LocalDate dateOfBirth = LocalDate.now();

        try {
            dateOfBirth = LocalDate.parse(employeeBirthDate);
            isContinue = true;
        } catch (DateTimeParseException dateTimeParseException) {
        }
        currentDate = LocalDate.now();
        age = currentDate.compareTo(dateOfBirth);

        if ((18 <= age) && (60 >= age)) {
            isContinue = true;
        } else {
            isContinue = false;
        }
        return isContinue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertEmployee(EmployeeDTO employeeDto) throws EMSException {
        return employeeDao.insertEmployee(Mapper.toEmployee(employeeDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDbIsEmpty() throws EMSException{
         return (getAllEmployees().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeeDao.getAllEmployees()) {
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
        return Mapper.employeeProjectToDto(employeeDao
                .getParticularEmployee(employeeId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int employeeId) throws EMSException {
        boolean isPresent = false;

        if (null != getParticularEmployee(employeeId)) {
           isPresent = true;
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMailIdExists(String mailId) throws EMSException {
        boolean isPresent = false;

        for (EmployeeDTO employee : getAllEmployees()) {

            if (mailId.equals(employee.getEmployeeMailId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContactNumberExists(String contactNumber)
            throws EMSException {
        boolean isPresent = false;

        for (EmployeeDTO employee : getAllEmployees()) {

            if (Long.parseLong(contactNumber) == employee
                    .getEmployeeContactNumber()) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(EmployeeDTO employeeDto) throws EMSException {
        employeeDao.updateEmployee(Mapper.dtoToEmployeeProject(employeeDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContactNumberExists(long contactNumber, int employeeId) 
            throws EMSException {
        boolean isPresent = false;

       for (Long phoneNumber : employeeDao
                .getContactNumber(employeeId)) {

            if (contactNumber == phoneNumber) {
                isPresent = true;
                break;
            } else {
                isPresent = false;
            }
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMailIdExists(String mailId, int employeeId) 
            throws EMSException {
        boolean isPresent = false;

        for (String mail : employeeDao.getMailId(employeeId)) {

            if (mail.equals(mailId)) {
                isPresent = true;
                break;
            } else {
                isPresent = false;
            }
        }
        return isPresent;
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
    public List<ProjectDTO> getAllProjects() throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProjectIdExists(int projectId) throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.isIdExists(projectId);
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
     */
    @Override
    public boolean isAlreadyAssigned(int employeeId, int projectId)
            throws EMSException {
        boolean isAlreadyAssigned = false;

        EmployeeDTO employee = getParticularEmployee(employeeId);

        if (!(employee.getProjectList().isEmpty())) {
            for (ProjectDTO project : employee.getProjectList()) {
                if (projectId == project.getProjectId()) {
                    isAlreadyAssigned = true;
                    break;
                }
            }
        }
        return isAlreadyAssigned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO getParticularProject(int projectId) throws EMSException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getParticularProject(projectId);
    }
}