
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * Interface of Employee Repository that extends JpaRepository.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Gets the employee of that Mail Id.
     * 
     * @param mailId by which an employee to be get as String.
     * 
     * @return employee object contains an employee.
     */
    Employee findByEmployeeMailId(String mailId);
    
    /**
     * Gets the employee of that Contact Number.
     * 
     * @param mailId by which an employee to be get as String.
     * 
     * @return employee object contains an employee.
     */
    Employee findByEmployeeContactNumber(String contactNumber);
}