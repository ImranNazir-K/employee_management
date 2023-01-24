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
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
public class OrderHelper {

    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Converts OrderDetailDTO Object into OrderDetail entity.
     *
     * @param orderDetailDto as OrderDetailDTO Object consists an
     *                       orderDetail.
     * @return order as Order entity Object.
     */
    public static OrderDetail toOrderDetail(OrderDetailDTO orderDetailDto) {
        return mapper.map(orderDetailDto, OrderDetail.class);
    }

    /**
     * Converts OrderDetail entity into OrderDetailDTO Object.
     *
     * @param orderDetail as OrderDetail Object.
     * @return orderDetailDTO as OrderDetailDTO Object.
     */
    public static OrderDetailDTO toOrderDetailDto(OrderDetail orderDetail) {
        return mapper.map(orderDetail, OrderDetailDTO.class);
    }

    /**
     * Converts list of OrderDetail Objects into OrderDetailDTO
     * objects.
     *
     * @param orderDetails as List<Order>
     * @return List<OrderDTO> as list of OrderDTO Objects.
     */
    public static List<OrderDetailDTO> toOrderDetailDtos(List<OrderDetail>
            orderDetails) {
        return orderDetails.stream().map(orderDetail -> toOrderDetailDto
                (orderDetail)).collect(Collectors.toList());
    }

    /**
     * Converts PaymentDTO Object into Payment entity.
     *
     * @param paymentDto as PaymentDTO Object consisting a payment.
     * @return payment as Payment entity.
     */
    public static Payment toPayment(PaymentDTO paymentDto) {
        return mapper.map(paymentDto, Payment.class);
    }

    /**
     * Converts Payment entity Object into PaymentDTO object.
     *
     * @param payment as Payment entity Object consisting a payment.
     * @return paymentDto as PaymentDTO Object.
     */
    public static PaymentDTO toPaymentDto(Payment payment) {
        return mapper.map(payment, PaymentDTO.class);
    }

    /**
     * Converts list of Payment entity object into PaymentDTO objects.
     *
     * @param payments as List<Payment>
     * @return List<PaymentDTO> as list of PaymentDTO objects.
     */
    public static List<PaymentDTO> toPaymentDtos(List<Payment> payments) {
        return payments.stream().map(payment -> toPaymentDto(payment))
                .toList();
    }

    /**
     * Converts OrderItemDTO Object into OrderItem entity.
     *
     * @param orderItemDto as OrderItemDTO Object consists an orderItem.
     * @return orderItem as OrderItem entity Object.
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
}