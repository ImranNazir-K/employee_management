/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.helper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.ideas2it.fooddeliveryapp.dto.DeliveryDTO;
import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.model.Delivery;
import com.ideas2it.fooddeliveryapp.model.Food;

/**
 * Contains methods to convert entities to DTOs and vice-versa
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
public class FoodHelper {

    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Converts DeliveryDTO object to Delivery object
     *
     * @param deliveryDto object of DeliveryDTO
     *
     * @return Delivery object
     */
    public static Delivery toDelivery(DeliveryDTO deliveryDto) {
        return mapper.map(deliveryDto, Delivery.class);
    }

    /**
     * Converts list of DeliveryDTO objects to
     * list of Delivery objects
     *
     * @param deliveryDtos list of objects of DeliveryDTO class
     *
     * @return list of Delivery objects
     */
    public static List<Delivery> toDeliveries(List<DeliveryDTO> deliveryDtos) {
        return deliveryDtos.stream().map(deliveryDto -> mapper.map(deliveryDto,
                Delivery.class)).toList();
    }

    /**
     * Converts Delivery object to DeliveryDTO object
     *
     * @param delivery object of Delivery
     *
     * @return DeliveryDTO object
     */
    public static DeliveryDTO toDeliveryDto(Delivery delivery) {
        return mapper.map(delivery, DeliveryDTO.class);
    }

    /**
     * Converts list of Delivery objects to
     * list of DeliveryDTO objects
     *
     * @param deliveries list of objects of Delivery
     *
     * @return list of DeliveryDTO objects
     */
    public static List<DeliveryDTO> toDeliveryDtos(List<Delivery> deliveries) {
        return deliveries.stream().map(delivery -> mapper.map(delivery,
                DeliveryDTO.class)).toList();
    }

    /**
     * Converts FoodDTO object to Food object
     *
     * @param foodDto object of FoodDto
     *
     * @return object of Food
     */
    public static Food toFood(FoodDTO foodDto) {
        return mapper.map(foodDto, Food.class);
    }

    /**
     * Converts list of FoodDTO objects to list of Food objects
     *
     * @param foodDtos list of objects of FoodDto
     *
     * @return list of objects of Food
     */
    public static List<Food> toFoods(List<FoodDTO> foodDtos) {
        return foodDtos.stream().map(foodDto -> mapper.map(foodDto,
                Food.class)).toList();
    }

    /**
     * Converts Food object to FoodDTO object
     *
     * @param food object of Food
     *
     * @return object of FoodDTO
     */
    public static FoodDTO toFoodDto(Food food) {
        return mapper.map(food, FoodDTO.class);
    }

    /**
     * Converts list of Food objects to list of FoodDTO objects
     *
     * @param foods list of objects of Food
     *
     * @return list of objects of FoodDTO
     */
    public static List<FoodDTO> toFoodDtos(List<Food> foods) {
        return foods.stream().map(food -> mapper.map(food,
                FoodDTO.class)).toList();
    }
}
