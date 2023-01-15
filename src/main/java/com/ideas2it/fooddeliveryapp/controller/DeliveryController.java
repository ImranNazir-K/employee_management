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
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.service.DeliveryService;

/**
 * Controller class for Delivery entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    /**
     * Inserts delivery into record
     *
     * @param deliveryDTO DTO object of delivery entity
     *
     * @return DTO object of delivery entity
     */
    @PostMapping
    public DeliveryDTO createDelivery(
            @Valid @RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.createDelivery(deliveryDTO);
    }

    /**
     * Gets all deliveries from the record
     *
     * @return List of all deliveries from the records as DTO objects
     */
    @GetMapping
    public List<DeliveryDTO> getDeliveries() {
        return deliveryService.getDeliveries();
    }

    /**
     * Gets particular delivery from the record by id
     *
     * @param id id of delivery to be fetched
     *
     * @return DTO object of delivery entity
     */
    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable int id) {
        return deliveryService.getDeliveryById(id);
    }

    /**
     * Updates delivery details from the record
     *
     * @param deliveryDTO DTO object of delivery entity
     *
     * @return updated DTO object of delivery entity
     */
    @PutMapping
    public DeliveryDTO updateDelivery(@Valid @RequestBody
            DeliveryDTO deliveryDTO) {
        return deliveryService.updateDelivery(deliveryDTO);
    }
}
