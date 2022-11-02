/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

import com.ideas2it.employeemanagement.model.Project;
/**
 * Contains Private Variables of Employee with Details like
 * Name, Age, Salary, Email, Id, joining Date, Contact Number.
 *
 * @author (name = "IMRAN NAZIR K")
 *
 * @version 3.2
 *
 * @since 29-10-2022
 */
public class Employee {

    private List<Project> projectList = new ArrayList<Project>();

    private int employeeId;
    private long employeeContactNumber;
    private double employeeSalary;
    private String employeeName;
    private String employeeMailId;
    private LocalDate employeeDateOfBirth;

    /**
     * empty constructor.
     */
    public Employee() {
    }

    /**
     *  parameterized constructor.
     */
    public Employee(int id, String name, String mailId,
            long contactNumber, double salary, LocalDate birthDate) {
        this.employeeId = id;
        this.employeeName = name;
        this.employeeMailId = mailId;
        this.employeeContactNumber = contactNumber;
        this.employeeSalary = salary;
        this.employeeDateOfBirth = birthDate;
    }

    /**
     *  parameterized constructor.
     */
    public Employee(int id, String name, String mailId,
            long contactNumber, double salary, LocalDate birthDate, List<Project> projectList) {
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

    public int getEmployeeAge() {
        LocalDate date = LocalDate.now();
        LocalDate birthDate = getEmployeeDateOfBirth();
        Period age = Period.between(birthDate, date);
        return age.getYears();
    } 

    public void setProjectList(List<Project> projectList) {
         for (Project project : projectList) { 
             this.projectList.add(project);
         }
    }

    public List<Project> getProjectList() {
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
                     .append(getEmployeeDateOfBirth())
                     .append("\n Employee Age        : ")
                     .append(getEmployeeAge());
        return stringBuilder.toString();
    }
}