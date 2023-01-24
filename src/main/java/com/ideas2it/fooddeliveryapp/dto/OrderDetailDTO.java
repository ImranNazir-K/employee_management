/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import com.ideas2it.fooddeliveryapp.constant.OrderConstant;
import com.ideas2it.fooddeliveryapp.util.OrderStatus;

/**
 * Data Transfer Object class for OrderDetail that consists of
 * fields like id, RestaurantDTO, UserDTO, OrderItemDTO and
 * orderDate, subTotal, discount and grandTotal, payment with
 * getters and setters.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public class OrderDetailDTO {

    private int orderId;

    @NotNull(message = OrderConstant.NOT_NULL_USER)
    private UserResponseDTO user;

    @NotNull(message = OrderConstant.NOT_NULL_RESTAURANT)
    private RestaurantDTO restaurant;

    private List<OrderItemDTO> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private float subTotal;

    private String discount;

    private float grandTotal;

    private LocalDateTime orderedDate;

    private LocalDateTime updatedDate;

    @NotNull(message = OrderConstant.PAYMENT_NOT_NULL)
    private PaymentDTO payment;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDateTime orderedDate) {
        this.orderedDate = orderedDate;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }
}