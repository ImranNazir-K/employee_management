package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.OrderPaymentConstant;
import com.ideas2it.fooddeliveryapp.dto.PaymentDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.OrderPaymentHelper;
import com.ideas2it.fooddeliveryapp.model.Payment;
import com.ideas2it.fooddeliveryapp.repository.PaymentRepository;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;
import com.ideas2it.fooddeliveryapp.service.PaymentService;

/**
 * Service class for Payment that implements PaymentService to
 * perform CRUD operations for Food Delivery Application.
 *
 * @author IMRAN NAZIR K
 * @version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderDetailService orderDetailService;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
            @Lazy OrderDetailService orderDetailService) {
        this.paymentRepository = paymentRepository;
        this.orderDetailService = orderDetailService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDto) {
//        PaymentDTO paymentDTO = calculateTotalAmount(paymentDto);
        Payment payment = paymentRepository.save(OrderPaymentHelper
                .toPayment(paymentDto));
        PaymentDTO pay = OrderPaymentHelper.toPaymentDto(payment);
//        pay.setDiscount(paymentDTO.getDiscount());
        return pay;
    }

    /**
     * Calculates the total amount, discount and grand total for the
     * order ordered by a user and sets those values in the
     * PaymentDTO object.
     *
     * @param paymentDto for the order made by client as PaymentDTO
     *                   object.
     * @return paymentDto as PaymentDTO object that to be saved.
     */
//    private PaymentDTO calculateTotalAmount(PaymentDTO paymentDto) {
//        float subTotal = orderDetailService.calculateSubTotal();
//        byte discount = calculateDiscount(subTotal);
//        float grandTotal = calculateGrandTotal(subTotal, discount);
//
//        paymentDto.setDiscount(String.valueOf(discount) + "%");
//        paymentDto.setGrandTotal(grandTotal);
//        paymentDto.setSubTotal(subTotal);
//
//        return paymentDto;
//    }

    /**
     * Calculates the discount amount for the order using the
     * subtotal amount.
     *
     * @param subTotal total amount of the order made by client.
     * @return discount calculated as byte.
     */
//    private byte calculateDiscount(float subTotal) {
//        byte discount = 0;
//
//        if (1000 <= subTotal) {
//            discount = 5;
//        } else if (2000 <= subTotal) {
//            discount = 10;
//        } else if (5000 <= subTotal) {
//            discount = 20;
//        }
//        return discount;
//    }

    /**
     * Calculates the grand total amount for the order by subtracting
     * the discount amount from the subtotal amount.
     *
     * @param subTotal total amount of the order as float.
     * @param discount applied for that subtotal amount as byte.
     * @return calculated grand total amount as float.
     */
//    private float calculateGrandTotal(float subTotal, byte discount) {
//        if (0 > discount) {
//            subTotal = subTotal - ((subTotal / 100) * discount);
//        }
//        return subTotal;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentDTO> getPayments() {
        return OrderPaymentHelper.toPaymentDtos(paymentRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDTO getPaymentById(int paymentId) throws NotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new NotFoundException(OrderPaymentConstant
                .PAYMENT_NOT_FOUND));
        return OrderPaymentHelper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDto) throws
            NotFoundException {
        Payment payment = paymentRepository.findById(paymentDto.getId())
                .orElseThrow(() -> new NotFoundException(OrderPaymentConstant
                        .PAYMENT_NOT_FOUND));
        return OrderPaymentHelper.toPaymentDto(paymentRepository.save
                (OrderPaymentHelper.toPayment(paymentDto)));
    }
}