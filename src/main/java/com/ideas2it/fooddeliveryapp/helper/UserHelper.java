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

import com.ideas2it.fooddeliveryapp.dto.AddressDTO;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.model.Address;
import com.ideas2it.fooddeliveryapp.model.User;

/**
 * This is UserHelper class for user module
 * provides methods for converting entity object DTO and DTO to entity.
 *
 * @author Govindaraj
 * @version 1.0
 */
public class UserHelper {
    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Converts UserDTO object into User object.
     *
     * @param userDto as UserDTO object.
     * @return user as User object
     */
    public static User toUser(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        return user;
    }

    /**
     * Converts List of User into List of UserDTO.
     *
     * @param users List of user.
     * @return List of UserDto.
     */
    public static List<UserDTO> toUserDtoList(List<User> users) {
        return users.stream().map(user -> mapper.map(user,
                UserDTO.class)).toList();
    }

    /**
     * Converts User object into UserDto object.
     *
     * @param user as User object.
     * @return converted userDto object.
     */
    public static UserDTO toUserDto(User user) {
        return mapper.map(user, UserDTO.class);
    }
    
    /**
     * Converts AddressDto object into Address object.
     *
     * @param addressDto as AddressDto object
     * @return converted address object.
     */
    public static Address toAddress(AddressDTO addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    /**
     * Converts Address object to AddressDto object.
     *
     * @param address as Address object.
     * @return converted addressDto object.
     */
    public static AddressDTO toAddressDto(Address address) {
        return mapper.map(address, AddressDTO.class);
    }
}