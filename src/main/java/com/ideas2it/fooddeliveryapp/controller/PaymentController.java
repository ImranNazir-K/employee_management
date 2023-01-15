/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;
import com.ideas2it.fooddeliveryapp.service.PaymentService;

/**
 * Class as controller for the Food Delivery Application that does
 *        CRUD operations for payments.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * creates a payment that client made as request.
     *
     * @param paymentDto as PaymentDTO instance consists a payment.
     * @return paymentDto which was created.
     */
    @PostMapping
    public PaymentDTO createPayment(@RequestBody @Valid PaymentDTO
            paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    /**
     * Gets all the payments which are available.
     *
     * @return List<PaymentDTO> list of payments.
     */
    @GetMapping
    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
    }

    /**
     * Gets that particular payment the client requested.
     *
     * @param paymentId of a payment as int.
     * @return paymentDTO as PaymentDTO instance that consists a
     *         payment.
     */
    @GetMapping("/{paymentId}")
    public PaymentDTO getPaymentById(@PathVariable int paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    /**
     * updates the particular payment the client requested.
     *
     * @param paymentDto as PaymentDTO instance consists a payment.
     * @return paymentDTO as PaymentDTO instance which was updated.
     */
    @PutMapping
    public PaymentDTO updatePayment(@RequestBody @Valid PaymentDTO
            paymentDto) {
        return paymentService.updatePayment(paymentDto);
    }
}