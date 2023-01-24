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

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;

/**
 * Interface for RestaurantServiceImpl class to perform CRUD
 * operations for restaurant.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
public interface RestaurantService {

    /**
     * Creates a restaurant before it Checks If a restaurant is found
     * with the same phone number or email, it throws a
     * DuplicateFoundException.
     *
     * @param restaurantDto The restaurant object.
     * @return A RestaurantDTO object which was created.
     */
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDto);

    /**
     * Checks If a restaurant is found with the same phone number,
     * it throws a DuplicateFoundException.
     *
     * @param phoneNumber The phone number for the restaurant.
     */
    void isPhoneNumberDuplicate(String phoneNumber);

    /**
     * Checks If a restaurant is found with the same email,
     * it throws a DuplicateFoundException.
     *
     * @param email The email for the restaurant.
     */
    void isEmailDuplicate(String email);


    /**
     * Gets a list of restaurants if no restaurants are found it
     * return empty list.
     *
     * @return A list of RestaurantDTO objects.
     */
    List<RestaurantDTO> getRestaurants();

    /**
     * Gets a restaurant by its id and if not found it
     * throws NotFoundException.
     *
     * @param id The id of the restaurant.
     * @return A RestaurantDTO object.
     */
    RestaurantDTO getRestaurantById(int id);

    /**
     * Updates a restaurant before it Checks if phone number or email
     * is available for a restaurant. If no restaurant is found it
     * updates or else it throws DuplicateFoundException.
     *
     * @param RestaurantDto The RestaurantDTO object.
     * @return A RestaurantDTO which was updated.
     */
    RestaurantDTO updateRestaurant(RestaurantDTO RestaurantDto);

    /**
     * Checks if phone number is available for a restaurant. If no
     * restaurant is found it updates or else it throws
     * DuplicateFoundException.
     *
     * @param id The id of the restaurant.
     * @param phoneNumber The phone number of the restaurant.
     * @return true if duplicate is found.
     */
    boolean isPhoneNumberAlreadyExists(int id, String phoneNumber);

    /**
     * Checks if email is available for a restaurant. If no
     * restaurant is found it updates or else it throws
     * DuplicateFoundException.
     *
     *
     * @param id The id of the restaurant.
     * @param email The email of the restaurant.
     * @return true if duplicate is found.
     */
    boolean isEmailAlreadyExists(int id, String email);

    /**
     * Deletes a restaurant by id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the restaurant to delete.
     * @return true if restaurant delete.
     */
    boolean deleteRestaurant(int id);

    /**
     * Gets a list of restaurants in a given area.
     *
     * @param areaName The name of the area.
     * @return A list of restaurants in the area.
     */
    List<RestaurantDTO> getRestaurantsByArea(String areaName);

    /**
     * Gets all reviews for a restaurant.
     *
     * @param id The id of the restaurant.
     * @return A list of Review for a restaurant.
     */
    List<ReviewDTO> getReviewsByRestaurantId(int id);

    /**
     * Gets all restaurants that serve a given cuisine.
     *
     * @param cuisineName The name of the cuisine.
     * @return A list of Restaurant that have the cuisineName.
     */
    List<RestaurantDTO> getRestaurantsByCuisine(String cuisineName);

    /**
     * Gets all restaurants that serve a specific food.
     *
     * @param foodName The name of the food.
     * @return A list of Restaurant.
     */
    List<RestaurantDTO> getRestaurantsByFoodName(String foodName);

    /**
     * Get all orders by restaurant.
     *
     * @param id The id of the restaurant.
     * @return List of OrderDetailDTO objects.
     */
    List<OrderDetailDTO> getAllOrdersByRestaurantId(int id);

    /**
     * Get all active orders for a restaurant.
     *
     * @param id The id of the restaurant.
     * @return List of OrderDetailDTO objects.
     */
    List<OrderDetailDTO> getActiveOrdersByRestaurantId(int id);

    /**
     * Accepts an order.
     *
     * @param orderId The id of the order.
     * @return List of OrderDetailDTO details.
     */
    OrderDetailDTO acceptOrder(int orderId);
}