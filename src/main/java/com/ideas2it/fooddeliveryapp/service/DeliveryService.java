/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service;

import java.util.List;

import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.model.Delivery;

/**
 * Interface for DeliveryServiceImpl class to perform CRUD
 * operations for Delivery.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
public interface DeliveryService {

    /**
     * Creates delivery.
     *
     * @param deliveryDTO Object of DeliveryDTO.
     * @return The created DeliveryDTO object.
     */
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);

    /**
     * Gets all deliveries.
     *
     * @return List of deliveries as DeliveryDTO objects.
     */
    List<DeliveryDTO> getDeliveries();

    /**
     * Gets particular delivery by the given id or throws NotFound
     * exception if the id is not found.
     *
     * @param id The id of delivery.
     * @return DeliveryDTO object.
     */
    DeliveryDTO getDeliveryById(int id);

    /**
     * Updates delivery details.
     *
     * @param deliveryDTO Object of DeliveryDTO.
     * @return The updated DeliveryDTO object.
     */
    DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO);

    /**
     * Gets all the deliveries of a particular user.
     *
     * @param userId The id of the user
     * @return List of deliveries as Delivery objects.
     */
    List<Delivery> getDeliveriesByUserId(int userId);

    /**
     * Gets all the Order Details as OrderDetailDTO objects that are
     * ready to be delivered.
     *
     * @return List of OrderDetailDTO objects.
     */
    List<OrderDetailDTO> getActiveOrdersToDeliver();
}