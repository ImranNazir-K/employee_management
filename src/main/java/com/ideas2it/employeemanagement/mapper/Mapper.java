
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.mapper;

import java.util.HashSet;
import java.util.Set;

import com.ideas2it.employeemanagement.dto.EmployeeDTO;
import com.ideas2it.employeemanagement.dto.ProjectDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.Project;

/**
 * Class converts Employee object into EmployeeDTO object and
 * converts EmployeeDTO object into Employee object.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
public class Mapper {

    /**
     * Converts EmployeeDTO object into Employee object.
     *
     * @param employeeDto as EmployeeDTO object that contains an
     *      employee.
     *
     * @return employee as Employee object.
     */
    public static Employee toEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();

        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeContactNumber(employeeDto
                .getEmployeeContactNumber());
        employee.setEmployeeSalary(Double.parseDouble(employeeDto.
                getEmployeeSalary()));
        employee.setEmployeeName(employeeDto.getEmployeeName().toUpperCase());
        employee.setEmployeeMailId(employeeDto.getEmployeeMailId
        		().toLowerCase());
        employee.setEmployeeDateOfBirth(employeeDto.getEmployeeDateOfBirth());
        return employee;
    }

    /**
     * Converts EmployeeDTO object into Employee object.
     *
     * @param employee as Employee object that contains an
     *      employee.
     *
     * @return employeeDto as EmployeeDTO object.
     */
    public static EmployeeDTO toEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
				
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setEmployeeContactNumber(employee.
                getEmployeeContactNumber());
        employeeDto.setEmployeeSalary(String.valueOf(employee.
                getEmployeeSalary()));
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmployeeMailId(employee.getEmployeeMailId());
        employeeDto.setEmployeeDateOfBirth(employee.getEmployeeDateOfBirth());
        return employeeDto;
    }

    /**
     * Converts ProjectDTO object into Project object.
     *
     * @param projectDto as ProjectDTO object that contains an
     *      project.
     *
     * @return project as Project object.
     */
    public static Project toProject(ProjectDTO projectDto) {
        Project project = new Project();

        project.setProjectId(projectDto.getProjectId());
        project.setProjectName(projectDto.getProjectName().toUpperCase());
        project.setProjectDomain(projectDto.getProjectDomain());
        project.setProjectDescription(projectDto.getProjectDescription());
        return project;
    }

    /**
     * Converts Project object into ProjectDTO object.
     *
     * @param project as Project object that contains an
     *      project.
     *
     * @return projectDto as ProjectDTO object.
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
     * Converts Employee object into EmployeeDTO object and Project
     * object into ProjectDTO object.
     *
     * @param employee as Employee object that contains an
     *      employee.
     *
     * @return employeeDto as EmployeeDTO object.
     */
    public static EmployeeDTO employeeProjectToDto(Employee employee) {
        EmployeeDTO employeeDto = toEmployeeDto(employee);
        Set<ProjectDTO> projectDtoList = new HashSet<ProjectDTO>();

        if (null != employee.getProject()) {
            for (Project project : employee.getProject()) {
                projectDtoList.add(toProjectDto(project));
            }
        }
        employeeDto.setProjects(projectDtoList);
        return employeeDto;
    }

    /**
     * Converts EmployeeDTO object into Employee object and
     * ProjectDTO object int Project object.
     *
     * @param employeeDto as EmployeeDTO object that contains an
     *      employee.
     *
     * @return employee as Employee object.
     */
    public static Employee dtoToEmployeeProject(EmployeeDTO employeeDto) {
        Employee employee = toEmployee(employeeDto);
        Set<Project> projects = new HashSet<Project>();

        if (null != employeeDto.getProjects()) {
            for (ProjectDTO projectDto : employeeDto.getProjects()) {
                projects.add(toProject(projectDto));
            }
        }
        employee.setProject(projects);
        return employee;
    }

    /**
     * Converts Project object into ProjectDTO object and Employee
     * object into EmployeeDTO object
     *
     * @param project as Project object that contains an
     *      project.
     *
     * @return projectDto as ProjectDTO object. 
     */
    public static ProjectDTO projectEmployeeToDto(Project project) {
        Set<EmployeeDTO> employeeDtoList = new HashSet<EmployeeDTO>();
        ProjectDTO projectDto = toProjectDto(project);

        if (null != project.getEmployee()) {
            for (Employee employee : project.getEmployee()) {
                employeeDtoList.add(Mapper.toEmployeeDto(employee));
            }
        }  
        projectDto.setEmployees(employeeDtoList);
        return projectDto;
    }

    /**
     * Converts ProjectDTO object into Project object and EmployeeDTO
     * as Employee object.
     *
     * @param projectDto as ProjectDTO object that contains an
     *      project.
     *
     * @return project as Project object.
     */
    public static Project dtoToProjectEmployee(ProjectDTO projectDto) {
        Project project = toProject(projectDto);
        Set<Employee> employees = new HashSet<Employee>();

        if (null != projectDto.getEmployees()) {
            for (EmployeeDTO employeeDto : projectDto.getEmployees()) {
                employees.add(toEmployee(employeeDto));
            }
        }  
        project.setEmployee(employees);
        return project;
    }
}