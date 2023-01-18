
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class Contains username and password of an employee.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class AthenticationRequest {
    String username;
    String password;
}
