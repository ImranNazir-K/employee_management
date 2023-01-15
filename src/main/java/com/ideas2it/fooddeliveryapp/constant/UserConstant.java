/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.constant;

/**
 * This is constant class for user provides repeated
 * String messages.
 *
 * @author Govindaraj
 * @version 1.0
 */
public class UserConstant {
    public static final String ADDRESS_ERROR = "Enter valid name:"
            +"Eg: NEW STREET  (only alphabets)";
    public static final String CONTACT_REGEX = "^([6-9]{1})([0-9]{9})$";
    public static final String CONTACT_ERROR = "Enter valid contact"
            +"Eg:9876543210 (only numerics allowed) ";
    public static final String DOOR_NO_REGEX = "^[a-zA-Z0-9/]{1,10}$";
    public static final String DOOR_NO_ERROR = "Enter valid door number"
            +"Eg:11A, 13/11 (no special characters allowed)";
    public static final String EMAIL_REGEX = "^[A-Za-z]{1}[A-Za-z0-9]"
            +"+(\\.[A-Za-z0-9-]+)*@[A-Za-z]"
            +"{2,9}+(\\.([A-Za-z]+){1,})"
            +"*(\\.[A-Za-z]{2,3})$";
    public static final String EMAIL_ERROR = "Enter valid email "
            +"Eg: abc123@gmail.com";
    public static final String ID_ERROR = "PLEASE ENTER VALID ID";

    public static final String NAME_REGEX = "^([A-Za-z]{3,40})+([ ]?[A-Za-z]"
            +"{1,30})?([ ]?[A-Za-z]{1,30})?$";
    public static final String NAME_ERROR = "Enter a valid name Eg:Ram"
            +"(only alphabets allowed)";
    public static final String PIN_CODE_REGEX = "^[1-9][0-9]{5}";
    public static final String PIN_CODE_ERROR = "Enter valid pin code"
            +"Eg: 635752 (only numerics allowed)";
}