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

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
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

    private EmployeeDAO employeeDao = new EmployeeDAOImpl();

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
        return (Pattern.matches("((([A-Za-z]{2,}([ ]?)){1,}))((([.]?)"
                + "([a-zA-Z]{1})){1,})", employeeName));
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validateEmployeeContactNumber (String contactNumber) {
        return (Pattern.matches("([6-9]{1}[0-9]{9})",
                contactNumber));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmployeeSalary (String employeeSalary) {
        return (Pattern.matches("([1-9]{1})([0-9]{1,8})((([.])"
                + "([0-9]{1,2}))?)", employeeSalary));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmployeeMailId (String employeeMailId) {
        return (Pattern.matches("((([A-Za-z0-9]{1,})([.]?)){1,})"
                + "([a-z]{0,}?)([@]{1})(([a-z])*)((([.])([a-z]{2,3}))"
                + "{1,2})", employeeMailId));
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
    public int insertEmployee(EmployeeDTO employeeDto) {
        return employeeDao.insertEmployee(Mapper.toEmployee(employeeDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDbIsEmpty() {
         return (getAllEmployees().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDtoList = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeeDao.getAllEmployees()) {
            employeeDtoList.add(Mapper.employeeProjectToDto(employee));
        }
        return employeeDtoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getParticularEmployee(int employeeId) {
        return Mapper.employeeProjectToDto(employeeDao
                .getParticularEmployee(employeeId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExists(int employeeId) {
        boolean isPresent = false;

        for (Integer id : employeeDao.getAllEmployeeId()) {

            if (employeeId == id) {
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
    public boolean isMailIdExists(String mailId) {
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
    public boolean isContactNumberExists(String contactNumber) {
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
    public void updateEmployee(EmployeeDTO employeeDto) {
        employeeDao.updateEmployee(Mapper.dtoToEmployeeProject(employeeDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isContactNumberExists(long contactNumber, int employeeId) {
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
    public boolean isMailIdExists(String mailId, int employeeId) {
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
    public boolean deleteAllEmployees() {
        boolean isDeleted = false;

        if (0 < (employeeDao.deleteAllEmployees())) {
            isDeleted = true;
        }
        return isDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteParticularEmployee(int employeeId) {
        employeeDao.deleteParticularEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectDTO> getAllProjects() {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProjectIdExists(int projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.isIdExists(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
   public boolean isProjectDbIsEmpty() {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.isDbIsEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyAssigned(int employeeId, int projectId) {
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
    public ProjectDTO getParticularProject(int projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getParticularProject(projectId);
    }
}