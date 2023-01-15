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


/**
 * Service class for Delivery entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
public interface DeliveryService {

    /**
     * Inserts delivery into record
     *
     * @param deliveryDTO DTO object of delivery entity
     *
     * @return DTO object of delivery entity
     */
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);

    /**
     * Gets all deliveries from the record
     *
     * @return List of all deliveries from the records as DTO objects
     */
    List<DeliveryDTO> getDeliveries();

    /**
     * Gets particular delivery from the record by id
     *
     * @param id id of delivery to be fetched
     *
     * @return DTO object of delivery entity
     */
    DeliveryDTO getDeliveryById(int id);

    /**
     * Updates delivery details from the record
     *
     * @param deliveryDTO DTO object of delivery entity
     *
     * @return updated DTO object of delivery entity
     */
    DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO);
}
