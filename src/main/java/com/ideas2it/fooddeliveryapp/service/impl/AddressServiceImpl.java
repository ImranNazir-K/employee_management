/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.dto.AddressDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.UserHelper;
import com.ideas2it.fooddeliveryapp.model.Address;
import com.ideas2it.fooddeliveryapp.repository.AddressRepository;
import com.ideas2it.fooddeliveryapp.service.AddressService;

/**
 * Service class for Address that implements AddressService
 * to perform CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory
            .getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO createAddress(AddressDTO addressDto) {
        Address address = addressRepository.save(UserHelper.toAddress
                (addressDto));
        return UserHelper.toAddressDto(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO getAddressById(int id) {
        Address address = addressRepository.findById(id)
                .filter(addressObject -> !addressObject.getIsDeleted())
                .orElseThrow(() -> {
                    logger.warn(UserConstant.INVALID_ID);
                    throw new NotFoundException(UserConstant.INVALID_ID);
                });
        return UserHelper.toAddressDto(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO updateAddress(AddressDTO addressDto) {
        Address address = addressRepository.save(UserHelper
                .toAddress(addressDto));
        return UserHelper.toAddressDto(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAddressById(int id) {
        boolean isDelete = false;

        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return !isDelete;
        } else {
            logger.warn(UserConstant.INVALID_ID);
            throw new NotFoundException(UserConstant.INVALID_ID);
        }
    }
}