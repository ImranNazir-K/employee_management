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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.AddressDTO;
import com.ideas2it.fooddeliveryapp.service.AddressService;

/**
 * Controller class for the Food Delivery Application that does
 * CRUD operations for Address.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Creates a address that clients made as request.
     *
     * @param addressDto as AddressDTO instance consists address.
     * @return addressDto which was created.
     */
    @PostMapping
    public AddressDTO createAddress(@Valid @RequestBody AddressDTO addressDto) {
        return addressService.createAddress(addressDto);
    }

    /**
     * Gets particular address the client requested.
     *
     * @param id of an address as int.
     * @return addressDto as AddressDTO instance that
     * consists address.
     */
    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

    /**
     * Updates the Address.
     *
     * @param addressDto The AddressDTO object.
     * @return updated addressDto object.
     */
    @PutMapping
    public AddressDTO updateAddress(@Valid @RequestBody AddressDTO
            addressDto) {
        return addressService.updateAddress(addressDto);
    }

    /**
     * Deletes address by id.
     *
     * @param id The id of the address.
     * @return true if deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteAddressById(@PathVariable int id) {
        return addressService.deleteAddressById(id);
    }
}