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
import com.ideas2it.fooddeliveryapp.dto.UserResponseDTO;
import com.ideas2it.fooddeliveryapp.model.Address;
import com.ideas2it.fooddeliveryapp.model.User;

/**
 * Converts entity to DTO object and DTO object to entity.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
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
        return mapper.map(userDto, User.class);
    }

    /**
     * Converts List of User into List of UserDTO.
     *
     * @param userDTOS List of user.
     * @return List of UserDto.
     */
    public static List<UserDTO> toUserDtos(List<User> userDTOS) {
        return userDTOS.stream().map(user -> mapper.map(user,
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
     * Converts UserDTO object into UserResponse object.
     *
     * @param userDto as user response object.
     * @return converted userResponseDto object.
     */
    public static UserResponseDTO toUserResponseDTO(UserDTO userDto) {
        return mapper.map(userDto, UserResponseDTO.class);
    }

    /**
     * Converts List<UserDTO> object into List<UserResponseDTO>.
     *
     * @param userDTOS List of UserDTO.
     * @return Converted List of UserResponse DTO.
     */
    public static List<UserResponseDTO> toUserResponseDTOS(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(userDTO -> mapper.map(userDTO,
                UserResponseDTO.class)).toList();
    }

    /**
     * Converts AddressDto object into Address object.
     *
     * @param addressDto as AddressDto object.
     * @return converted address object.
     */
    public static Address toAddress(AddressDTO addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    /**
     * Converts Address object to AddressDto object.
     *
     * @param addressDto as Address object.
     * @return converted addressDto object.
     */
    public static AddressDTO toAddressDto(Address addressDto) {
        return mapper.map(addressDto, AddressDTO.class);
    }
}