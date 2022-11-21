
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.model;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;
import java.time.Period;

import com.ideas2it.employeemanagement.model.Project;
/**
 * Contains Private variables of employees like
 * name, salary, mail, Id, joining date, contact Number.
 *
 * @author IMRAN NAZIR K
 *
 * @version 4.0
 *
 */
public class Employee {

    private int employeeId;
    private long employeeContactNumber;
    private double employeeSalary;
    private String employeeName;
    private String employeeMailId;
    private Set<Project> projectList = new HashSet<Project>();
    private LocalDate employeeDateOfBirth;

    public Employee() {
    }

    public Employee(String name, String mailId,
            long contactNumber, double salary, LocalDate birthDate) {
        this.employeeName = name;
        this.employeeMailId = mailId;
        this.employeeContactNumber = contactNumber;
        this.employeeSalary = salary;
        this.employeeDateOfBirth = birthDate;
    }

    public Employee(int id, String name, String mailId,
            long contactNumber, double salary, LocalDate birthDate, Set<Project> projectList) {
        this.employeeId = id;
        this.employeeName = name;
        this.employeeMailId = mailId;
        this.employeeContactNumber = contactNumber;
        this.employeeSalary = salary;
        this.employeeDateOfBirth = birthDate;
        this.projectList = projectList;     
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeMailId(String employeeMailId) {
        this.employeeMailId = employeeMailId;
    }

    public String getEmployeeMailId() {
        return this.employeeMailId;
    }

    public void setEmployeeContactNumber(long employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public long getEmployeeContactNumber() {
        return this.employeeContactNumber;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public double getEmployeeSalary() {
        return this.employeeSalary;
    }

    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    } 

    public LocalDate getEmployeeDateOfBirth() {
        return this.employeeDateOfBirth;
    }

    public void setProjectList(Set<Project> projectList) {
        this.projectList = projectList;
    }

    public Set<Project> getProjectList() {
        return this.projectList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n Employee ID         : ")
                     .append(getEmployeeId())
                     .append("\n Name                : ")
                     .append(getEmployeeName())
                     .append("\n Employee MailID     : ")
                     .append(getEmployeeMailId())
                     .append("\n Contact Number      : ")
                     .append(getEmployeeContactNumber())
                     .append("\n Salary              : ")
                     .append(getEmployeeSalary())
                     .append("\n Date of Birth       : ")
                     .append(getEmployeeDateOfBirth());
        return stringBuilder.toString();
    }
}