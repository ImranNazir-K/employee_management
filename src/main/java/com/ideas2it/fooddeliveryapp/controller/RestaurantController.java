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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.service.RestaurantService;

/**
 * Controller class for Handling the incoming requests to validate
 * the user input for performing crud operations for Restaurant.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Creates a restaurant before it Checks If a restaurant is found
     * with the same phone number or email, it throws a
     * DuplicateFoundException.
     *
     * @param restaurantDto The restaurantDto object that will be
     *                      created.
     * @return A RestaurantDTO object which was created.
     */
    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody @Valid RestaurantDTO
            restaurantDto) {
        return restaurantService.createRestaurant(restaurantDto);
    }

    /**
     * Gets all the restaurants if no restaurants are found it return
     * empty list.
     *
     * @return A list of RestaurantDTO objects.
     */
    @GetMapping
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    /**
     * Gets a restaurant by its id and if not found it throws
     * NotFoundException.
     *
     * @param id The id of the restaurant.
     * @return A RestaurantDTO object.
     */
    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * Updates a restaurant before it Checks if phone number or email
     * is available for a restaurant. If no restaurant is found it
     * updates or else it throws DuplicateFoundException.
     *
     * @param restaurantDto The object that will be updated.
     * @return A RestaurantDTO object which was updated.
     */
    @PutMapping
    public RestaurantDTO updateRestaurant(@RequestBody @Valid RestaurantDTO
            restaurantDto) {
        return restaurantService.updateRestaurant(restaurantDto);
    }

    /**
     * Deletes a restaurant by id if not found it throws
     * NotFoundException.
     *
     * @param id The id of the restaurant to be deleted.
     * @return true if restaurant was deleted.
     */
    @DeleteMapping("/{id}")
    public boolean deleteRestaurant(@PathVariable int id) {
        return restaurantService.deleteRestaurant(id);
    }

    /**
     * Gets a list of restaurants in a given area.
     *
     * @param areaName The name of the area.
     * @return A list of RestaurantDTO objects.
     */
    @GetMapping("/addresses/{areaName}")
    public List<RestaurantDTO> getRestaurantsByArea(@PathVariable String
            areaName) {
        return restaurantService.getRestaurantsByArea(areaName);
    }

    /**
     * Gets all restaurants that serve a given cuisine.
     *
     * @param cuisineName The name of the cuisine.
     * @return A list of restaurants that have the cuisineName.
     */
    @GetMapping("/foods/{cuisineName}")
    public List<RestaurantDTO> getRestaurantsByCuisine(@PathVariable String
            cuisineName) {
        return restaurantService.getRestaurantsByCuisine(cuisineName);
    }

    /**
     * Gets all reviews for a restaurant.
     *
     * @param id The id of the restaurant.
     * @return A list of reviews for a restaurant.
     */
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getReviewsByRestaurantId(@PathVariable int id) {
        return restaurantService.getReviewsByRestaurantId(id);
    }

    /**
     * Gets all orders by restaurant.
     *
     * @param id The id of the restaurant.
     * @return List of OrderDetailDTO objects.
     */
    @GetMapping("/{id}/orders")
    public List<OrderDetailDTO> getAllOrdersByRestaurantId(@PathVariable int
            id) {
        return restaurantService.getAllOrdersByRestaurantId(id);
    }

    /**
     * Gets all active orders for a restaurant.
     *
     * @param id The id of the restaurant.
     * @return List of OrderDetailDTO objects.
     */
    @GetMapping("/{id}/activeorders")
    public List<OrderDetailDTO> getActiveOrdersByRestaurantId(@PathVariable int
            id) {
        return restaurantService.getActiveOrdersByRestaurantId(id);
    }

    /**
     * Accepts an order.
     *
     * @param orderId The id of the order.
     * @return List of OrderDetailDTO objects.
     */
    @GetMapping("/orders/{orderId}")
    public OrderDetailDTO acceptOrder(@PathVariable int orderId) {
        return restaurantService.acceptOrder(orderId);
    }
}