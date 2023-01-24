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
 * Class contains constant for user and address.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
public class UserConstant {

    public static final String AREA_NOT_NULL = "Null value not allowed for"
            + " area";
    public static final String CITY_NOT_NULL = "Null value not allowed "
            + " for city";
    public static final String CONTACT_NOT_NULL = "Null value not allowed"
            + " for contact ";
    public static final String CONTACT_EXIST = "Contact Number already Exists"
            + "Please Enter a valid Contact Number";
    public static final String CONTACT_REGEX = "([6-9]{1})([0-9]{9})";
    public static final String DOOR_NO_REGEX = "^[a-zA-Z0-9/]{1,10}$";
    public static final String DOOR_NOT_NULL = "Null value not allowed "
            + "for door_no";
    public static final String EMAIL_EXIST_ALREADY = "Email Id already Exists"
            + " Please Enter a valid Email Id";
    public static final String EMAIL_NOT_NULL = "Null value not allowed"
            + " for email";
    public static final String EMAIL_REGEX = "^[A-Za-z]{1}[A-Za-z0-9]"
            + "+(\\.[A-Za-z0-9-]+)*@[A-Za-z]{2,9}+(\\.([A-Za-z]+){1,})"
            + "*(\\.[A-Za-z]{2,3})$";
    public static final String GENDER_NOT_NULL = "Null value not allowed"
            + " for gender";
    public static final String INVALID_CONTACT = "Please Enter a valid Contact"
            + " Number Eg:9876543210 (Only Numerics allowed) ";
    public static final String INVALID_CREDENTIALS = "Please Enter a valid"
            + " Username or Password";
    public static final String INVALID_DOOR_NO = "Please Enter a valid Door No"
            + " Eg:11A, 13/11 (No Special Characters Allowed)";
    public static final String INVALID_EMAIL = "Please Enter a valid Mail Id"
            + " Eg: abc123@gmail.com";
    public static final String INVALID_ID = "Please Enter a valid Id";
    public static final String INVALID_NAME = "Please Enter a valid Name Eg:Ram"
            + " (only alphabets are allowed)";
    public static final String INVALID_PASSWORD = "Password must contain "
            + "AtLeast 8 Characters and One Uppercase & Lowercase & number";
    public static final String INVALID_PIN_CODE = "Please Enter a valid "
            + "PinCode Eg: 635752 (ONLY NUMERICS ALLOWED)";
    public static final String INVALID_CITY = "Please Enter a valid City";
    public static final String INVALID_AREA = "Please Enter a valid Area";
    public static final String INVALID_STREET_NAME = "Please Enter a valid "
            + " Street Name";
    public static final String INVALID_USER_NAME = "Please Enter a valid "
            + "Username (user_name should be at_least 7 characters) "
            + "\n (Eg: Abc_123,  characters allowed :Ab12_.)";
    public static final String NAME_NOT_NULL = "Null value not allowed "
            + "for name";
    public static final String NAME_REGEX = "^([A-Za-z]{3,40})+([ ]?[A-Za-z]"
            + "{1,30})?([ ]?[A-Za-z]{1,30})?$";
    public static final String TEXT_REGEX = "^[a-zA-z][a-zA-Z\\s]*$";
    public static final String PASSWORD_NOT_NULL = "Password should "
            + " not be null";
    public static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])"
            + "(?=.*[A-Z])(?=\\S+$).{8,}";
    public static final String PIN_CODE_NOT_NULL = "Null value not allowed"
            + " for pin code";
    public static final String PIN_CODE_REGEX = "^[1-9][0-9]{5}$";
    public static final String ROLE_NOT_NULL = "Null value not allowed"
            + " for role";
    public static final String STREET_NOT_NULL = "Null value not allowed for "
            + " street name";
    public static final String USER_NOT_NULL = "Null value not allowed for "
            + " name";
    public static final String USER_NAME_NOT_NULL = "Null value not allowed "
            + " for userName";
    public static final String USER_NAME_REGEX = "^[a-zA-Z0-9_.]{7,20}$";
}