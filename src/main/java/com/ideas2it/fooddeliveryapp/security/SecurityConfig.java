/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ideas2it.fooddeliveryapp.filter.JwtFilter;
import com.ideas2it.fooddeliveryapp.service.impl.UserSecurityService;

/**
 * Class for configuration of SecurityChainFilter to provide security
 * to our application.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserSecurityService userSecurityService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(UserSecurityService userSecurityService, JwtFilter
            jwtFilter) {
        this.userSecurityService = userSecurityService;
        this.jwtFilter = jwtFilter;
    }

    /**
     * Encodes the password with BcryptPasswordEncoder and return the
     * encrypted characters.
     *
     * @return encrypted password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuration for security, based on roles the entries will be
     * restricted for users and security.
     *
     * @param httpSecurity security configurations.
     * @return SecurityFilterChain
     * @throws Exception when authentication is invalid.
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterAfter(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/users/authenticate")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}")
                .hasAnyAuthority("ADMIN", "CUSTOMER", "DELIVERY_PERSON",
                        "VENDOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/users")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users")
                .hasAnyAuthority("CUSTOMER", "DELIVERY_PERSON", "VENDOR",
                        "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/addresses")
                .hasAnyAuthority("ADMIN", "CUSTOMER", "VENDOR",
                        "DELIVERY_PERSON")
                .requestMatchers(HttpMethod.GET, "/api/v1/addresses/{id}")
                .hasAnyAuthority("CUSTOMER", "DELIVERY_PERSON", "VENDOR",
                        "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/addresses")
                .hasAnyAuthority("CUSTOMER", "DELIVERY_PERSON", "VENDOR",
                        "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/addresses/{id}")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/restaurants")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/restaurants/{id}")
                .hasAnyAuthority("ADMIN", "VENDOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/restaurants")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/restaurants/{id}/reviews")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/restaurants/addresses/{areaName}")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/restaurants/foods/{cuisineName}")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/restaurants/{id}/orders")
                .hasAnyAuthority("VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/restaurants/{id}/activeorders")
                .hasAnyAuthority("VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/restaurants/orders/{orderId}")
                .hasAnyAuthority("ADMIN", "VENDOR")
                .requestMatchers(HttpMethod.PUT, "/api/v1/restaurants")
                .hasAnyAuthority("ADMIN", "VENDOR")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/restaurants/{id}")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/reviews")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/reviews")
                .hasAnyAuthority("CUSTOMER", "VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/reviews/{id}")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/reviews")
                .hasAnyAuthority("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/reviews/{id}")
                .hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/foods")
                .hasAnyAuthority("ADMIN", "VENDOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/foods/{id}")
                .hasAnyAuthority("ADMIN", "VENDOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/foods")
                .hasAnyAuthority("CUSTOMER", "VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/foods/{foodName}/restaurants")
                .hasAnyAuthority("ADMIN", "CUSTOMER", "VENDOR")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/foods/category/{category}/restaurants/"
                                + "{restaurantId}")
                .hasAnyAuthority("ADMIN", "VENDOR", "CUSTOMER")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/foods/cuisine/{cuisine}/restaurants/"
                                + "{restaurantId}")
                .hasAnyAuthority("ADMIN", "CUSTOMER", "VENDOR")
                .requestMatchers(HttpMethod.PUT, "/api/v1/foods")
                .hasAnyAuthority("VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/foods/{id}")
                .hasAnyAuthority("VENDOR", "ADMIN")
                .requestMatchers(HttpMethod.POST, "api/v1/orderitems")
                .hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET, "api/v1/payments")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/deliveries")
                .hasAnyAuthority("DELIVERY_PERSON", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/deliveries")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/deliveries/{id}")
                .hasAnyAuthority("ADMIN", "DELIVERY_PERSON")
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/deliveries/orderdetails")
                .hasAnyAuthority("ADMIN", "VENDOR", "DELIVERY_PERSON")
                .requestMatchers(HttpMethod.GET, "/api/v1/deliveries/"
                        + "users/{id}")
                .hasAnyAuthority("ADMIN", "DELIVERY_PERSON")
                .requestMatchers(HttpMethod.PUT, "/api/v1/deliveries")
                .hasAnyAuthority("ADMIN", "DELIVERY_PERSON")
                .requestMatchers(HttpMethod.POST, "api/v1/orderdetails")
                .hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "api/v1/orderdetails")
                .hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/orderdetails/users/{userId}")
                .hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "api/v1/orderdetails/{orderId}/users/{userId}")
                .hasAnyAuthority("CUSTOMER", "ADMIN")
                .anyRequest().authenticated();
        return httpSecurity.build();
    }

    /**
     * Configure the properties for authentication.
     *
     * @param authenticationConfiguration to provide authentication
     *                                    manager.
     * @return configured authentication properties.
     * @throws Exception when configuration fails.
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Provide the authentication provider to verify the credentials
     * of user.
     *
     * @return configured authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new
                DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userSecurityService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}