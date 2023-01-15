/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.dto.OrderItemDTO;
import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;
import com.ideas2it.fooddeliveryapp.model.OrderDetail;
import com.ideas2it.fooddeliveryapp.model.OrderItem;
import com.ideas2it.fooddeliveryapp.model.Payment;

/**
 * Converts DTO Objects into Entity Objects and Entity Objects
 * into DTO Objects using ModelMapper.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
public class OrderPaymentHelper {
    private static final ModelMapper mapper = new ModelMapper();

    /**
     * converts OrderDTO Object into Order entity.
     *
     * @param orderDetailDto as OrderDTO Object consists an order.
     * @return order as Order entity Object.
     */
    public static OrderDetail toOrderDetail(OrderDetailDTO orderDetailDto) {
        return mapper.map(orderDetailDto, OrderDetail.class);
    }

    /**
     * Converts Order entity Object into OrderDTO Object.
     *
     * @param orderDetail as Order Object.
     * @return orderDTO as OrderDTO Object.
     */
    public static OrderDetailDTO toOrderDetailDto(OrderDetail orderDetail) {
        return mapper.map(orderDetail, OrderDetailDTO.class);
    }

    /**
     * Converts list of orders Objects into OrderDTO Objects as
     * list.
     *
     * @param orderDetails as List<Order>
     * @return List<OrderDTO> as list of OrderDTO Objects.
     */
    public static List<OrderDetailDTO> toOrderDetailDtos(List<OrderDetail> orderDetails) {
        return orderDetails.stream().map(orderDetail -> toOrderDetailDto(orderDetail))
                .collect(Collectors.toList());
    }

    /**
     * converts PaymentDTO Object into Payment entity.
     *
     * @param paymentDto as PaymentDTO Object consisting a payment.
     * @return payment as Payment entity Object.
     */
    public static Payment toPayment(PaymentDTO paymentDto) {
        return mapper.map(paymentDto, Payment.class);
    }

    /**
     * converts Payment entity Object into PaymentDTO object.
     *
     * @param payment as Payment entity Object consisting a payment.
     * @return paymentDto as PaymentDTO Object.
     */
    public static PaymentDTO toPaymentDto(Payment payment) {
        return mapper.map(payment, PaymentDTO.class);
    }

    /**
     * Converts list of Payment entity object into PaymentDTO objects
     * as list.
     *
     * @param payments as List<Payment>
     * @return List<PaymentDTO> as list of PaymentDTO objects.
     */
    public static List<PaymentDTO> toPaymentDtos(List<Payment> payments) {
        return payments.stream().map(payment -> toPaymentDto(payment))
                .collect(Collectors.toList());
    }

    /**
     * converts OrderItemDTO Object into OrderItemRepository entity.
     *
     * @param orderItemDto as OrderItemDTO Object consists an orderItem.
     * @return orderItem as OrderItemRepository entity Object.
     */
    public static OrderItem toOrderItem(OrderItemDTO orderItemDto) {
        return mapper.map(orderItemDto, OrderItem.class);
    }

    /**
     * Converts OrderItem entity Object into OrderItemDTO Object.
     *
     * @param orderItem as OrderItem Object.
     * @return orderDTO as OrderDTO Object.
     */
    public static OrderItemDTO toOrderItemDto(OrderItem orderItem) {
        return mapper.map(orderItem, OrderItemDTO.class);
    }

    /**
     * Converts list of orderItems Objects into OrderItemDTO Objects as
     * list.
     *
     * @param orderItems as List<OrderItemRepository>
     * @return List<OrderItemDTO> as list of OrderItemDTO Objects.
     */
    public static List<OrderItemDTO> toOrderItemDtos(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> toOrderItemDto(orderItem))
                .collect(Collectors.toList());
    }
}