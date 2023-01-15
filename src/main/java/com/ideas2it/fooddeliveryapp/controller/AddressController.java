/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.controller;

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
 * This is controller class for address entity and
 * provides methods for CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * It takes a AddressDTO object as a request body and calls
     * create function in address service if address is added
     * it return user object else it throws exception.
     *
     * @param addressDto The address object is to be added.
     * @return give address object as response.
     */
    @PostMapping()
    public AddressDTO createAddress(@RequestBody AddressDTO addressDto) {
        return addressService.createAddress(addressDto);
    }

    /**
     * It fetches address by id and returns a fetched address,
     * if address is not present, it will throw error message (Address not found)
     *
     * @param id The id of the address to be fetched.
     * @return address detail as response.
     */
    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

    /**
     * It takes a AddressDTO object as a parameter, calls the updateAddress
     * function in the addressService class, and returns
     * updated object else throws error message.
     *
     * @param addressDto The address object need to be updated.
     * @return updated address object as response.
     */
    @PutMapping()
    public AddressDTO updateAddress(@RequestBody AddressDTO addressDto) {
        return addressService.updateAddress(addressDto);
    }

    /**
     * It deletes address from the database and
     * returns boolean true if deletes else throws error message(id not found).
     *
     * Note: address's deleted status update into true.
     *
     * @param id The id of the address to be deleted.
     * @return boolean as response.
     */
    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return addressService.deleteAddressById(id);
    }
}