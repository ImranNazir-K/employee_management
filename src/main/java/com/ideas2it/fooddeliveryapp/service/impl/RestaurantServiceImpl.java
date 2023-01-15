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

import com.ideas2it.fooddeliveryapp.constant.RestaurantReviewConstant;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.RestaurantReviewHelper;
import com.ideas2it.fooddeliveryapp.model.Restaurant;
import com.ideas2it.fooddeliveryapp.repository.RestaurantRepository;
import com.ideas2it.fooddeliveryapp.service.RestaurantService;

/**
 * This class is a service class that implements the
 * RestaurantService interface
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDto) {
        Restaurant restaurant = RestaurantReviewHelper.toRestaurant(
                restaurantDto);
        return RestaurantReviewHelper.toRestaurantDto(restaurantRepository.
                save(restaurant));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurants() {
        return RestaurantReviewHelper.toRestaurantDtos
                (restaurantRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO getRestaurantById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).
                filter(restaurantObject -> restaurantObject.
                        getIsDeleted() == false)
                .orElseThrow(() -> new NotFoundException(
                        RestaurantReviewConstant.RESTAURANT_NOT_FOUND));
        return RestaurantReviewHelper.toRestaurantDto(restaurant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDto) {
        Restaurant restaurant = RestaurantReviewHelper.toRestaurant(
                restaurantDto);
        return RestaurantReviewHelper.toRestaurantDto(restaurantRepository.
                save(restaurant));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRestaurant(int id) {
        boolean isDeleted = false;

        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            isDeleted = true;
        } else {
            throw new NotFoundException(RestaurantReviewConstant.
                    RESTAURANT_NOT_FOUND);
        }
        return isDeleted;
    }
}
