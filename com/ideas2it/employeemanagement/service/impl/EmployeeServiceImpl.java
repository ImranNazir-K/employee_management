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

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.mapper.Mapper;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * Validates all input given by users.
 * Stores the employees in the Database.
 * Does Operations like Create, update, Display, Delete employees.
 *
 * @author { name = "IMRAN NAZIR K" }
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAOImpl employeeDao = new EmployeeDAOImpl();

    List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO> ();

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
    public boolean insertEmployee(EmployeeDTO employeeDto) {
        boolean isInserted = false;

        if (1 == (employeeDao.insertEmployee(Mapper.toEmployee(employeeDto)))){
            isInserted = true;
        }
        return isInserted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return  Mapper.toEmployeeDtoList(employeeDao.getAllEmployees());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeDTO getParticularEmployee(int employeeId) {
        return Mapper.toEmployeeDto(employeeDao
                .getParticularEmployee(employeeId));
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
    public boolean isIdExists(int employeeId) {
        boolean isPresent = false;

        for (EmployeeDTO employees : getAllEmployees()) {
            if (employeeId == employees.getEmployeeId()) {
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

        for (EmployeeDTO employees : getAllEmployees()) {
            if (mailId.equals(employees.getEmployeeMailId())) {
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

        for (EmployeeDTO employees : getAllEmployees()) {
            if (Long.parseLong(contactNumber) == employees
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
    public boolean updateEmployee(EmployeeDTO employeeDto) {
        boolean isUpdated = false;

        if (0 < (employeeDao.updateEmployee(Mapper.toEmployee(employeeDto)))) {
            isUpdated = true;
        }
        return isUpdated;     
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhoneNumberExists(long phoneNumber, int employeeId) {
        boolean isPresent = false;

        for (EmployeeDTO employee : Mapper.toEmployeeDtoList(employeeDao.getEmployees(employeeId))) {
            if (phoneNumber == employee.getEmployeeContactNumber()) {
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
    public boolean isMailExists(String mailId, int employeeId) {
        boolean isPresent = false;

        for (EmployeeDTO employee : Mapper.toEmployeeDtoList(employeeDao.getEmployees(employeeId))) {
            if (mailId.equals(employee.getEmployeeMailId())) {
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
    public boolean deleteParticularEmployee(int employeeId) {
        boolean isDeleted = false;

        if (0 < (employeeDao.deleteParticularEmployee(employeeId))) {
            isDeleted = true;
        }
        return isDeleted; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean assignEmployeeToProject(int employeeId, int projectId) {
        boolean isAssigned = false;

        if (0 < (employeeDao.assignEmployeeToProject(employeeId, projectId))) {
            isAssigned = true;
        }
        return isAssigned;
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkRedundancy(int employeeId, int projectId) {
        boolean isPresent = false;

        EmployeeDTO employee = getParticularEmployee(employeeId);

            for (ProjectDTO project : employee.getProjectList()) {
                if ((employeeId == employee.getEmployeeId()) && (projectId == project.getProjectId())) {
                    isPresent = true;
                    break;
                }
            }
        return isPresent;
    }
}