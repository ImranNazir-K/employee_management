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
 * Class contains constants for OrderDetail.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public class OrderPaymentConstant {

    public static final String AMOUNT_REGEX = "([1-9]{1})([0-9]"
            + "{1,8})((([.])([0-9]{1,2}))?)";
    public static final String INVALID_AMOUNT = "Enter a valid Amount";
    public static final String INVALID_QUANTITY = "Invalid Quantity input";
    public static final String NOT_NULL_AMOUNT = "Amount should not be null";
    public static final String NOT_NULL_GRAND_TOTAL = "Grand Total Amount "
            + "should not be null";
    public static final String NOT_NULL_PAYMENT = "Payment should not be null";
    public static final String NOT_NULL_QUANTITY = "Quantity should not be"
            + "null";
    public static final String NOT_NULL_SUB_TOTAL = "Sub Total Amount "
            + "should not be null";
    public static final String ORDER_ITEM_NOT_FOUND = "Order Item Not Found";
    public static final String ORDER_NOT_FOUND = "Order Not Found";
    public static final String PAYMENT_NOT_FOUND = "Payment Not Found";
    public static final String QUANTITY_REGEX = "([1-9]{1})(([0-9])*)";
}