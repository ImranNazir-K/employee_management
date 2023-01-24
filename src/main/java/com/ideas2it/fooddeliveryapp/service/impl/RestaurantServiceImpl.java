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

import com.ideas2it.fooddeliveryapp.constant.RestaurantConstant;
import com.ideas2it.fooddeliveryapp.dto.OrderDetailDTO;
import com.ideas2it.fooddeliveryapp.dto.RestaurantDTO;
import com.ideas2it.fooddeliveryapp.dto.ReviewDTO;
import com.ideas2it.fooddeliveryapp.exception.DuplicateFoundException;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.RestaurantHelper;
import com.ideas2it.fooddeliveryapp.model.Restaurant;
import com.ideas2it.fooddeliveryapp.repository.RestaurantRepository;
import com.ideas2it.fooddeliveryapp.service.OrderDetailService;
import com.ideas2it.fooddeliveryapp.service.RestaurantService;
import com.ideas2it.fooddeliveryapp.service.ReviewService;

/**
 * Service class for restaurant that implements RestaurantService
 * to perform CRUD operations.
 *
 * @author Sakthi Annamalai
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Logger logger = LoggerFactory
            .getLogger(RestaurantServiceImpl.class);

    private final RestaurantRepository restaurantRepository;
    private final ReviewService reviewService;
    private final OrderDetailService orderDetailService;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
            ReviewService reviewService, OrderDetailService orderDetailService) {
        this.restaurantRepository = restaurantRepository;
        this.reviewService = reviewService;
        this.orderDetailService = orderDetailService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDto) {
        isPhoneNumberDuplicate(restaurantDto.getPhoneNumber());
        isEmailDuplicate(restaurantDto.getEmail());
        Restaurant restaurant = restaurantRepository.save(RestaurantHelper
                .toRestaurant(restaurantDto));
        logger.info("Restaurant Created");
        return RestaurantHelper.toRestaurantDto(restaurant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isPhoneNumberDuplicate(String phoneNumber) {
        if (null != restaurantRepository.findByPhoneNumber(phoneNumber)) {
            logger.warn(RestaurantConstant.PHONE_NUMBER_ALREADY_EXIST);
            throw new DuplicateFoundException(RestaurantConstant
                    .PHONE_NUMBER_ALREADY_EXIST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isEmailDuplicate(String email) {
        if (null != restaurantRepository.getByEmail(email)) {
            logger.warn(RestaurantConstant.EMAIL_ALREADY_EXIST);
            throw new DuplicateFoundException(RestaurantConstant
                    .EMAIL_ALREADY_EXIST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurants() {
        return RestaurantHelper.toRestaurantDtos(restaurantRepository
                .findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO getRestaurantById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .filter(restaurantObject -> !restaurantObject.getIsDeleted())
                .orElseThrow(() -> {
                    logger.warn(RestaurantConstant.RESTAURANT_NOT_FOUND);
                    throw new NotFoundException(RestaurantConstant
                            .RESTAURANT_NOT_FOUND);
                });

        logger.info("Gets the particular restaurant");
        return RestaurantHelper.toRestaurantDto(restaurant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDto) {
        int id = restaurantDto.getId();

        if (isPhoneNumberAlreadyExists(id, (restaurantDto.getPhoneNumber()))) {
            logger.warn("Restaurant phone number is already exist");
            throw new DuplicateFoundException(RestaurantConstant
                    .PHONE_NUMBER_ALREADY_EXIST);
        }

        if (isEmailAlreadyExists(id, restaurantDto.getEmail())) {
            logger.warn("Restaurant email is already exist");
            throw new DuplicateFoundException(RestaurantConstant
                    .EMAIL_ALREADY_EXIST);
        }
        Restaurant restaurant = restaurantRepository.save(RestaurantHelper
                .toRestaurant(restaurantDto));
        logger.info("Restaurant Updated");
        return RestaurantHelper.toRestaurantDto(restaurant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhoneNumberAlreadyExists(int id, String phoneNumber) {
        boolean isExists = false;
        Restaurant restaurant = restaurantRepository.findByPhoneNumber
                (phoneNumber);

        if (null == restaurant) {
            isExists = true;
        } else if (restaurant.getId() == id) {
            isExists = true;
        }
        return !isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmailAlreadyExists(int id, String email) {
        boolean isExists = false;
        Restaurant restaurant = restaurantRepository.getByEmail(email);

        if (null == restaurant) {
            isExists = true;
        } else if (restaurant.getId() == id) {
            isExists = true;
        }
        return !isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRestaurant(int id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            logger.info("Restaurant deleted");
            return true;
        } else {
            logger.warn("invalid restaurant id");
            throw new NotFoundException(RestaurantConstant.
                    RESTAURANT_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurantsByArea(String areaName) {
        return RestaurantHelper.toRestaurantDtos(restaurantRepository
                .findAllByAddressArea(areaName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReviewDTO> getReviewsByRestaurantId(int id) {
        return reviewService.getReviewsByRestaurantId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurantsByCuisine(String cuisineName) {
        return RestaurantHelper.toRestaurantDtos(restaurantRepository
                .findAllByFoodsCuisine(cuisineName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDTO> getRestaurantsByFoodName(String foodName) {
        return RestaurantHelper.toRestaurantDtos(restaurantRepository
                .findAllByFoodsName(foodName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getAllOrdersByRestaurantId(int id) {
        return orderDetailService.getAllOrdersByRestaurantId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderDetailDTO> getActiveOrdersByRestaurantId(int id) {
        return orderDetailService.getActiveOrdersByRestaurantId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailDTO acceptOrder(int orderId) {
        return orderDetailService.updateOrder(orderId);
    }
}