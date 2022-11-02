/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Class Converts Employee Object into EmployeeDTO object and
 * Converts EmployeeDTO Object into Employee object.
 *
 * @author (name = "IMRAN NAZIR K", date = "")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class Mapper {

    /**
     * empty constructor.
     */
    public Mapper() {
    }

    /**
     * Converts EmployeeDTO Object into Employee object.
     *
     * @param employeeDto as EmployeeDTO object.
     *
     * @return employee
     */
    public static Employee toEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();

        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeContactNumber(employeeDto.getEmployeeContactNumber());
        employee.setEmployeeSalary(employeeDto.getEmployeeSalary());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeMailId(employeeDto.getEmployeeMailId());
        employee.setEmployeeDateOfBirth(employeeDto.getEmployeeDateOfBirth());

        return employee;
    }

    /**
     * Converts EmployeeDTO Object into Employee object.
     *
     * @param employee.
     *
     * @return employeeDto
     */
    public static EmployeeDTO toEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();

        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setEmployeeContactNumber(employee.getEmployeeContactNumber());
        employeeDto.setEmployeeSalary(employee.getEmployeeSalary());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmployeeMailId(employee.getEmployeeMailId());
        employeeDto.setEmployeeDateOfBirth(employee.getEmployeeDateOfBirth());
        employeeDto.setProjectList(toProjectDtoList(employee
                .getProjectList()));
        return employeeDto;
    }

    /**
     * gets all the employees from the Database and add those
     * employees by conveting it into EmployeeDTO object and adding
     *  it into an ArrayList.
     *
     * @param employeesList as List.
     *
     * @return list of employees as employeeDtoList.
     */
    public static List<EmployeeDTO> toEmployeeDtoList(List<Employee> employeesList) {
        List<EmployeeDTO> employeeDtoList = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeesList) {
            employeeDtoList.add(toEmployeeDto(employee));
        }
        return employeeDtoList;
    }

    /**
     * Converts ProjectDTO Object into Project object.
     *
     * @param projectDto.
     *
     * @return project.
     */
    public static Project toProject(ProjectDTO projectDto) {
        Project project = new Project();

        project.setProjectId(projectDto.getProjectId());
        project.setProjectName(projectDto.getProjectName());
        project.setProjectDomain(projectDto.getProjectDomain());
        project.setProjectDescription(projectDto.getProjectDescription());
        return project;
    }

    /**
     * Converts Project Object into ProjectDTO object.
     *
     * @param project
     *
     * @return list of projectDto
     */
    public static ProjectDTO toProjectDto(Project project) {
        ProjectDTO projectDto = new ProjectDTO();

        projectDto.setProjectId(project.getProjectId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setProjectDomain(project.getProjectDomain());
        projectDto.setProjectDescription(project.getProjectDescription());
        return projectDto;
    }

    /**
     * gets all the Projects from the Database and add those
     * Projects by conveting it into ProjectDTO object and adding
     *  it into an ArrayList
     *
     * @param projectList as List.
     *
     * @return list of projects as projectDtoList.
     */
    public static List<ProjectDTO> toProjectDtoList(List<Project> projectList) {
        List<ProjectDTO> projectDtoList = new ArrayList<ProjectDTO>();

        for (Project project : projectList) {
            projectDtoList.add(toProjectDto(project));
        }
        return projectDtoList;
    }
}