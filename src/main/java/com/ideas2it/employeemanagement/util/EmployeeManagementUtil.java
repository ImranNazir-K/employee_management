
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.util;

import org.springframework.stereotype.Component;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.repository.EmployeeRepository;

/**
 * Util class for EmployeeManagementSystem checks for mail and
 * 		contactNumber to avoid duplicate entry.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Component
public class EmployeeManagementUtil {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeManagementUtil(EmployeeRepository employeeRepository) {
		this.employeeRepository=  employeeRepository;
	}


    /**
     * checks whether the maild Id is is already available to avoid
     * 		duplicate entry.
     * 
     * @param employeeMailId of an employee as String.
     * @param employeeId of an employee as int.
     * 
     * @return true if the mail Id is present.
     */
    public boolean isMailIdExists(String employeeMailId, int employeeId) {
        boolean isExists = false;
        Employee employee = employeeRepository.findByEmployeeMailId
                (employeeMailId);
        
        if (null == employee) {
            isExists = true;
        } else if (employee.getEmployeeId() == employeeId){
            isExists = true;
        }
        return !isExists;
    }
    
    /**
     * checks whether the contactNumber is already available to avoid
     * 		duplicate entry.
     * 
     * @param contactNumber of an employee as String.
     * @param employeeId of an employee as int.
     * 
     * @return true if the mail Id is present.
     */
    public boolean isContactNumberExists(String contactNumber,
    		int employeeId) {
        boolean isExists = false;
        Employee employee = employeeRepository
                .getEmployeeByContactNumber(contactNumber);
        
        if (null == employee) {
            isExists = true;
        } else if (employee.getEmployeeId() == employeeId) {
            isExists = true;
        }
        return !isExists;
    }
}
