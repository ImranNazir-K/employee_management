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

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;
import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.FoodHelper;
import com.ideas2it.fooddeliveryapp.model.Delivery;
import com.ideas2it.fooddeliveryapp.repository.DeliveryRepository;
import com.ideas2it.fooddeliveryapp.service.DeliveryService;

/**
 * Implementation of DeliveryService class for Delivery entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = FoodHelper.toDelivery(deliveryDTO);
        deliveryRepository.save(delivery);
        return FoodHelper.toDeliveryDto(delivery);
    }

    /**
     * {@inheritDoc}
     */
    public List<DeliveryDTO> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return FoodHelper.toDeliveryDtos(deliveries);
    }

    /**
     * {@inheritDoc}
     */
    public DeliveryDTO getDeliveryById(int id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        FoodConstant.DELIVERY_NOT_FOUND));
        return FoodHelper.toDeliveryDto(delivery);
    }

    /**
     * {@inheritDoc}
     */
    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = FoodHelper.toDelivery(deliveryDTO);
        deliveryRepository.save(delivery);
        return FoodHelper.toDeliveryDto(delivery);
    }
}
