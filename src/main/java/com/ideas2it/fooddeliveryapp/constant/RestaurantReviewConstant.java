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
 * Contains Constants for food delivery app
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public class RestaurantReviewConstant {

    public static final String DESCRIPTION_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?))";
    public static final String EMAIL_NOTNULL = "Email Not Null";
    public static final String EMAIL_REGEX = "^((([A-Za-z0-9]{1,})"
            + "([.]?)){1,})([a-z]{0,}?)([@]{1})([a-z]{2,})"
            + "((([.])([a-z]{2,3})){1,2})$";
    public static final String INVALID_NAME = "Invalid name";
    public static final String INVALID_DESCRIPTION = "Invalid Description";
    public static final String INVALID_EMAIL = "Invalid Email";
    public static final String INVALID_PHONENUMBER = "Invalid PhoneNumber";
    public static final String INVALID_REMARK = "Invalid Remark";
    public static final String NAME_NOTNULL = "Name Cannot be Null";
    public static final String PHONENUMBER_NOTNULL = "Phone Number Cannot "
            + "be Null";
    public static final String PHONENUMBER_REGEX = "[6-9][0-9]{9}$";
    public static final String RESTAURANT_NAME_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?)([a-zA-Z0-9]{1,}))";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant Id Not Found";
    public static final String REVIEW_NOT_FOUND = "Review Id Not Found";
    public static final String RESTAURANT_REMOVED = "Removed particular "
            + "Restaurant";
    public static final String REVIEW_REMOVED = "Removed "
            + "particular Review";
    public static final String REMARK_REGEX = "((([A-Za-z0-9]{2,}"
            + "([ ]?)){1,}))(([.]?))";
}