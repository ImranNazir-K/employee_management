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

import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.model.Delivery;
import com.ideas2it.fooddeliveryapp.service.DeliveryService;

/**
 * Controller class for handling the incoming requests to validate
 * the user input for performing CRUD operations for Delivery.
 *
 * @author M Mohamed Riyas
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
     * Creates the delivery from the given data.
     *
     * @param deliveryDTO the object of DeliveryDTO.
     * @return DeliveryDTO object.
     */
    @PostMapping
    public DeliveryDTO createDelivery(@Valid @RequestBody
    DeliveryDTO deliveryDTO) {
        return deliveryService.createDelivery(deliveryDTO);
    }

    /**
     * Gets all the deliveries as list if
     * it not soft deleted.
     *
     * @return List of all deliveries as DeliveryDTO objects.
     */
    @GetMapping
    public List<DeliveryDTO> getDeliveries() {
        return deliveryService.getDeliveries();
    }

    /**
     * Gets a particular delivery by id if it is not soft deleted.
     *
     * @param id The id of delivery to be fetched.
     * @return DeliveryDTO object.
     */
    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable int id) {
        return deliveryService.getDeliveryById(id);
    }

    /**
     * Updates the delivery details with the given updated data.
     *
     * @param deliveryDTO DTO object of delivery entity.
     * @return Updated DeliveryDTO object.
     */
    @PutMapping
    public DeliveryDTO updateDelivery(@Valid @RequestBody
    DeliveryDTO deliveryDTO) {
        return deliveryService.updateDelivery(deliveryDTO);
    }

    /**
     * Gets all the Order Details as list that are
     * ready to be delivered.
     *
     * @return List of OrderDetailDTO objects.
     */
    @GetMapping("/orderdetails")
    public List<OrderDetailDTO> getActiveOrdersToDeliver() {
        return deliveryService.getActiveOrdersToDeliver();
    }

    /**
     * Gets all the deliveries of a particular user.
     *
     * @param userId The id of the user
     * @return List of deliveries as DeliveryDTO objects.
     */
    @GetMapping("/users/{userId}")
    public List<Delivery> getDeliveriesByUserId(@PathVariable int userId) {
        return deliveryService.getDeliveriesByUserId(userId);
    }
}