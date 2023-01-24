/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;
import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.FoodHelper;
import com.ideas2it.fooddeliveryapp.model.Delivery;
import com.ideas2it.fooddeliveryapp.repository.DeliveryRepository;
import com.ideas2it.fooddeliveryapp.service.DeliveryService;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;

/**
 * Implementation class of DeliveryService interface for Delivery
 * entity.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger logger = LoggerFactory
            .getLogger(DeliveryServiceImpl.class);

    private final DeliveryRepository deliveryRepository;
    private final OrderDetailService orderDetailService;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository,
            OrderDetailService orderDetailService) {
        this.deliveryRepository = deliveryRepository;
        this.orderDetailService = orderDetailService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryRepository.save(FoodHelper
                .toDelivery(deliveryDTO));
        logger.info("Delivery Created");
        return FoodHelper.toDeliveryDto(delivery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DeliveryDTO> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return FoodHelper.toDeliveryDtos(deliveries);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryDTO getDeliveryById(int id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn(FoodConstant.DELIVERY_NOT_FOUND);
                    throw new NotFoundException(FoodConstant.DELIVERY_NOT_FOUND);
                });

        return FoodHelper.toDeliveryDto(delivery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryRepository.save(FoodHelper
                .toDelivery(deliveryDTO));
        logger.info("Delivery updated");
        return FoodHelper.toDeliveryDto(delivery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getActiveOrdersToDeliver() {
        return orderDetailService.getActiveOrdersToDeliver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> getDeliveriesByUserId(int userId) {
        return deliveryRepository.findAllByUserId(userId);
    }
}