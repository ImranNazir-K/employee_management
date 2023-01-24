package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;
import com.ideas2it.fooddeliveryapp.helper.OrderHelper;
import com.ideas2it.fooddeliveryapp.model.Payment;
import com.ideas2it.fooddeliveryapp.repository.PaymentRepository;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;
import com.ideas2it.fooddeliveryapp.service.PaymentService;

/**
 * Service class for Payment that implements PaymentService to
 * perform CRUD operations for Payment.
 *
 * @author Imran Nazir K
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory
            .getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;
    private final OrderDetailService orderDetailService;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
            OrderDetailService orderDetailService) {
        this.paymentRepository = paymentRepository;
        this.orderDetailService = orderDetailService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentDTO> getPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return OrderHelper.toPaymentDtos(payments);
    }
}