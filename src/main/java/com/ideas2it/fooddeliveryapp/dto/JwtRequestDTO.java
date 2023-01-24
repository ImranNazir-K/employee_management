/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;

/**
 * Data Transfer Object class for JwtRequest that consists of
 * fields like userName, password and its getters and setters.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public class JwtRequestDTO {

    @NotNull(message = UserConstant.USER_NOT_NULL)
    @Pattern(regexp = UserConstant.USER_NAME_REGEX, message = UserConstant
            .INVALID_CREDENTIALS)
    private String userName;

    @NotNull(message = UserConstant.PASSWORD_NOT_NULL)
    @Pattern(regexp = UserConstant.PASSWORD_REGEX, message = UserConstant
            .INVALID_CREDENTIALS)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}