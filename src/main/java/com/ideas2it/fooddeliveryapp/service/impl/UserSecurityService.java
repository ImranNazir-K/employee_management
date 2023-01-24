/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.UserConstant;
import com.ideas2it.fooddeliveryapp.model.User;
import com.ideas2it.fooddeliveryapp.repository.UserRepository;
import com.ideas2it.fooddeliveryapp.security.UserSecurityDetails;

/**
 * Class implements UserDetailsService to provide username,
 * and password for authentication provider.
 */
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (null == user) {
            throw new UsernameNotFoundException(UserConstant.INVALID_CREDENTIALS);
        }
        return new UserSecurityDetails(user);
    }
}