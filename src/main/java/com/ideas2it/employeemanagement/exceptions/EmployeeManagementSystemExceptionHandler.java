
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class to handle all the Exceptions.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@RestControllerAdvice
public class EmployeeManagementSystemExceptionHandler {

	/**
	 * Class handles the Employee Management System Exceptions.
	 * 
	 * @param exception as EmployeeManagementSystemException object.
	 * 
	 * @return exception as Response Entity.
	 */
    @ExceptionHandler(value = EmployeeManagementSystemException.class)
	public ResponseEntity<Object> EmployeeManagementSystemException(EmployeeManagementSystemException exception) {
		return new ResponseEntity<Object>(exception.getExceptionMessage(),
				HttpStatus.BAD_REQUEST);
	}
    
    /**
     * Class handles the exception thrown while validaitng the fields
     * 		in the entity.
     * 
     * @param exception as MethodArgumentNotValidException object.
     * 
     * @return exception with HttpStatus as Response Entity.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException
            (MethodArgumentNotValidException exception) {
        return new ResponseEntity<String>(exception.getBindingResult()
                .getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Class handles the exception caused when the user is not found.
     * 
     * @param exception as UsernameNotFoundException object.
     * 
     * @return exception with HttpStatus as Response Entity.
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> UsernameNotFoundException
            (UserNotFoundException exception) {
        return new ResponseEntity<String>(exception.getExceptionMessage(),
        		HttpStatus.BAD_REQUEST);
    }
}
