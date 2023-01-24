/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;
import com.ideas2it.fooddeliveryapp.service.PaymentService;

/**
 * Controller class for Handling  the incoming requests to validate
 * the user input for performing crud operations for Payments.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Gets all the payments which are available.
     *
     * @return list of payments as List<PaymentDTO>.
     */
    @GetMapping
    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
    }
}