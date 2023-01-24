/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.exception;

/**
 * Exception class as DuplicateFoundException which is thrown when
 * the request object contains duplicate or redundant value
 * attribute.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public class DuplicateFoundException extends RuntimeException {

    private String exception;

    public DuplicateFoundException(String exception) {
        super(exception);
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }
}