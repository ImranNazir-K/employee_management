/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception Handler class for the Food Delivery Application.
 *
 * @author
 * @version 1.0
 */
@RestControllerAdvice
public class FoodDeliveryExceptionHandler {

    /**
     * Handles the NotFoundException thrown when the requested object
     * is not found.
     *
     * @param notFoundException instance for NotFoundException.
     * @return Exception message that to be shown to client as
     * String.
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public String notFoundException(NotFoundException notFoundException) {
        return notFoundException.getException();
    }

    /**
     * Handles the MethodArgumentNotValidException thrown when the
     * input given by client mismatches the pattern given.
     *
     * @param exception instance for MethodArgumentNotValidException.
     * @return Exception message that to be shown to client as String.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException
    (MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldError()
                .getDefaultMessage();
    }
}