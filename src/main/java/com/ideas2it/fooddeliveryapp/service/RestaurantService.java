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

import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;

/**
 * This interface is for RestaurantServiceImpl
 *
 * @author Sakthi Annamalai
 * @version 1.0
 */
public interface RestaurantService {

    /**
     * Creates restaurant.
     *
     * @param restaurantDto The restaurant object that you
     *                      want to create.
     * @return RestaurantDTO
     */
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDto);

    /**
     * Gets a list of restaurants.
     *
     * @return A list of RestaurantDTO objects.
     */
    List<RestaurantDTO> getRestaurants();

    /**
     * Gets a restaurant by its id.
     *
     * @param id The id of the restaurant you want to get.
     * @return A RestaurantDTO object.
     */
    RestaurantDTO getRestaurantById(int id);

    /**
     * Updates a restaurant.
     *
     * @param RestaurantDto The RestaurantDTO object that you
     *                      want to update.
     * @return RestaurantDTO
     */
    RestaurantDTO updateRestaurant(RestaurantDTO RestaurantDto);

    /**
     * Deletes a restaurant by id.
     *
     * @param id The id of the restaurant to delete
     * @return A string.
     */
    boolean deleteRestaurant(int id);
}
