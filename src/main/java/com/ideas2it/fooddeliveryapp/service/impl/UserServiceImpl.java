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

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.UserHelper;
import com.ideas2it.fooddeliveryapp.model.User;
import com.ideas2it.fooddeliveryapp.repository.UserRepository;
import com.ideas2it.fooddeliveryapp.service.UserService;

/**
 * This class is a service class that implements the
 * UserService interface and provides methods
 * for CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO createUser(UserDTO userDto) {
       User user = UserHelper.toUser(userDto);
       user.setPassword(userDto.getPhoneNumber());
       return UserHelper.toUserDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUserById( int id) {
        User user = userRepository.findById(id).filter(userObject ->
                userObject.isDeleted() == false).orElseThrow(()-> new
                NotFoundException(UserConstant.ID_ERROR));
        return UserHelper.toUserDto(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> getAllUser() {
        return UserHelper.toUserDtoList(userRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        User user = UserHelper.toUser(userDto);
        return  UserHelper.toUserDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserById(int id) {
        boolean isDelete;

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            isDelete = true;
        } else {
            throw new NotFoundException(UserConstant.ID_ERROR);
        }
        return isDelete;
    }
}
