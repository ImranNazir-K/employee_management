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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.dto.JwtRequestDTO;
import com.ideas2it.fooddeliveryapp.dto.UserDTO;
import com.ideas2it.fooddeliveryapp.dto.UserResponseDTO;
import com.ideas2it.fooddeliveryapp.exception.DuplicateFoundException;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.UserHelper;
import com.ideas2it.fooddeliveryapp.model.User;
import com.ideas2it.fooddeliveryapp.repository.UserRepository;
import com.ideas2it.fooddeliveryapp.service.UserService;
import com.ideas2it.fooddeliveryapp.util.JwtUtil;

/**
 * Service class for user that implements UserService
 * to perform CRUD operations.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userSecurityService;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder
            passwordEncoder, AuthenticationManager authenticationManager,
            UserSecurityService userSecurityService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userSecurityService = userSecurityService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponseDTO createUser(UserDTO userDto) {
        isPhoneNumberExists(userDto.getPhoneNumber());
        isEmailExists(userDto.getEmail());
        User user = UserHelper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserHelper.toUserResponseDTO(UserHelper.toUserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponseDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .filter(userObject -> !userObject.getIsDeleted())
                .orElseThrow(() -> {
                    logger.warn(UserConstant.INVALID_ID);
                    throw new NotFoundException(UserConstant.INVALID_ID);
                });
        return UserHelper.toUserResponseDTO(UserHelper.toUserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserResponseDTO> getAllUser() {
        List<UserDTO> userList = UserHelper.toUserDtos(userRepository
                .findAll());
        return UserHelper.toUserResponseDTOS(userList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponseDTO updateUser(UserDTO userDto) {
        if (isPhoneNumberAlreadyExists(userDto.getId(),
                (userDto.getPhoneNumber()))) {
            logger.warn(UserConstant.CONTACT_EXIST);
            throw new DuplicateFoundException(UserConstant.
                    CONTACT_EXIST);
        }

        if (isEmailAlreadyExists(userDto.getId(), userDto.getEmail())) {
            logger.warn(UserConstant.EMAIL_EXIST_ALREADY);
            throw new DuplicateFoundException(UserConstant.
                    EMAIL_EXIST_ALREADY);
        }
        User user = UserHelper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserHelper.toUserResponseDTO(UserHelper.toUserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserById(int id) {
        boolean isDelete = false;

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return !isDelete;
        } else {
            logger.warn(UserConstant.INVALID_ID);
            throw new NotFoundException(UserConstant.INVALID_ID);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isPhoneNumberExists(String contact) {
        if (null != userRepository.findByPhoneNumber(contact)) {
            throw new DuplicateFoundException(UserConstant.CONTACT_EXIST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isEmailExists(String email) {
        if (null != userRepository.findByMailId(email)) {
            throw new DuplicateFoundException(UserConstant
                    .EMAIL_EXIST_ALREADY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhoneNumberAlreadyExists(int id, String phoneNumber) {
        boolean isExists = false;
        User user = userRepository.findByPhoneNumber(phoneNumber);

        if (null == user) {
            isExists = true;
        } else if (user.getId() == id) {
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
        User user = userRepository.findByMailId(email);

        if (null == user) {
            isExists = true;
        } else if (user.getId() == id) {
            isExists = true;
        }
        return !isExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToken(JwtRequestDTO jwtRequest) {
        final String token;
        User user = userRepository.findByUserName(jwtRequest
                .getUserName());

        if (null != user) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                jwtRequest.getUserName(),
                                jwtRequest.getPassword()
                        ));
            } catch (NotFoundException exception) {
                throw new NotFoundException(UserConstant.INVALID_CREDENTIALS);
            }
            final UserDetails userDetails = userSecurityService
                    .loadUserByUsername(jwtRequest.getUserName());
            token = jwtUtil.generateToken(userDetails);
            return token;
        } else {
            logger.warn(UserConstant.INVALID_CREDENTIALS);
            throw new NotFoundException(UserConstant.INVALID_CREDENTIALS);
        }
    }
}