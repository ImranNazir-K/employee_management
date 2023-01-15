/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.dto.AddressDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.UserHelper;
import com.ideas2it.fooddeliveryapp.model.Address;
import com.ideas2it.fooddeliveryapp.repository.AddressRepository;
import com.ideas2it.fooddeliveryapp.service.AddressService;

/**
 * This class is a service class that implements the
 * AddressService interface and provides methods
 * for CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 */
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO createAddress(AddressDTO addressDto) {
        Address address = UserHelper.toAddress(addressDto);
        return UserHelper.toAddressDto(addressRepository.save(address));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO getAddressById(int id) {
        Address address = addressRepository.findById(id)
                .filter(addressObject -> addressObject.isDeleted() == false)
                .orElseThrow(()-> new NotFoundException(UserConstant.ID_ERROR));
        return UserHelper.toAddressDto(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressDTO updateAddress( AddressDTO addressDto) {
        return UserHelper.toAddressDto(addressRepository.save(UserHelper
                .toAddress(addressDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAddressById(int id) {
        boolean isDelete;

        if(addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            isDelete = true;
        } else {
            throw new NotFoundException("Invalid Exception");
        }
        return isDelete;
    }
}