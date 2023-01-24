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
 * Contains Constants for restaurant and review.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public class RestaurantConstant {

    public static final String DESCRIPTION_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?))";
    public static final String EMAIL_ALREADY_EXIST = "Email Already Exists try"
            + " another email";
    public static final String EMAIL_NOT_NULL = "Email should Not be Null";
    public static final String EMAIL_REGEX = "((([A-Za-z0-9]{1,})"
            + "([.]?)){1,})([a-z]{0,}?)([@]{1})([a-z]{2,})"
            + "((([.])([a-z]{2,3})){1,2})";
    public static final String EXPERIENCE_NOT_NULL = "Experience should not "
            + "be null";
    public static final String FEEDBACK_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?))";
    public static final String INVALID_NAME = "Please enter valid name";
    public static final String INVALID_DESCRIPTION = "Please enter valid "
            + "Description";
    public static final String INVALID_EMAIL = "Please enter valid email";
    public static final String INVALID_PHONE_NUMBER = "Please enter valid phone"
            + " number";
    public static final String INVALID_FEEDBACK = "Please enter valid Remark";
    public static final String PHONE_NUMBER_ALREADY_EXIST = "Phone Number "
            + "Already Exists try another phone number";
    public static final String PHONE_NUMBER_NOT_NULL = "Phone Number should not"
            + " be Null";
    public static final String PHONE_NUMBER_REGEX = "[6-9][0-9]{9}$";
    public static final String RATING_NOT_NULL = "Rating should not be null";
    public static final String RESTAURANT_NAME_NOT_NULL = "Restaurant name"
            + " should not be null";
    public static final String RESTAURANT_NAME_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?)([a-zA-Z0-9]{1,}))";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant Not Found";
    public static final String RESTAURANT_NOT_NULL = "Restaurant should not"
            + " be null";
    public static final String REVIEW_NOT_FOUND = "Review Not Found";
    public static final String USER_NOT_NULL = "User should not be null";
}