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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;
import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.FoodHelper;
import com.ideas2it.fooddeliveryapp.model.Food;
import com.ideas2it.fooddeliveryapp.repository.FoodRepository;
import com.ideas2it.fooddeliveryapp.service.FoodService;
import com.ideas2it.fooddeliveryapp.service.RestaurantService;

/**
 * Implementation class of FoodService interface for Food entity.
 *
 * @author M Mohamed Riyas
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class FoodServiceImpl implements FoodService {

    private static final Logger logger = LoggerFactory
            .getLogger(FoodServiceImpl.class);

    private final FoodRepository foodRepository;
    private final RestaurantService restaurantService;

    public FoodServiceImpl(FoodRepository foodRepository,
            RestaurantService restaurantService) {
        this.foodRepository = foodRepository;
        this.restaurantService = restaurantService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO createFood(FoodDTO foodDto) {
        Food food = foodRepository.save(FoodHelper.toFood(foodDto));
        logger.info("Food Created");
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodDTO> getFoods() {
        return FoodHelper.toFoodDtos(foodRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO getFoodById(int id) {
        Food food = foodRepository.findById(id)
                .filter(foodObject -> !foodObject.getIsDeleted())
                .orElseThrow(() -> {
                    logger.warn(FoodConstant.FOOD_NOT_FOUND);
                    throw new NotFoundException(FoodConstant.FOOD_NOT_FOUND);
                });
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO updateFood(FoodDTO foodDto) {
        Food food = foodRepository.save(FoodHelper.toFood(foodDto));
        logger.info("Food Updated");
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteFood(int id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            logger.info("Food Deleted");
            return true;
        } else {
            logger.warn(FoodConstant.FOOD_NOT_FOUND);
            throw new NotFoundException(FoodConstant.FOOD_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurantByFood(String foodName) {
        return restaurantService.getRestaurantsByFoodName(foodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodDTO> getFoodByCategory(int restaurantId, String category) {
        RestaurantDTO restaurant = restaurantService
                .getRestaurantById(restaurantId);

        List<FoodDTO> foods = restaurant.getFoods().stream()
                .filter(food -> category.equals(food.getCategory())).toList();
        return foods;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodDTO> getFoodByCuisine(int restaurantId, String cuisine) {
        RestaurantDTO restaurant = restaurantService
                .getRestaurantById(restaurantId);

        List<FoodDTO> foods = restaurant.getFoods().stream()
                .filter(food -> cuisine.equals(food.getCuisine())).toList();
        return foods;
    }
}