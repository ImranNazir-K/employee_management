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
 * Exception class as NotFoundException which is thrown when the
 * requested object is not found in Runtime.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public class NotFoundException extends RuntimeException {

    private String exception;

    public NotFoundException(String exception) {
        super(exception);
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}