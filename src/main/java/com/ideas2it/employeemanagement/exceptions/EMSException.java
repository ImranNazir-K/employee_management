
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class for Employee Management System Exception.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
@Getter
@Setter
@AllArgsConstructor
public class EMSException extends RuntimeException{

    private String exceptionMessage;
    private HttpStatus httpStatus;
}