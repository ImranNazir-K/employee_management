/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ideas2it.fooddeliveryapp.service.impl.UserSecurityService;
import com.ideas2it.fooddeliveryapp.util.JwtUtil;

/**
 * JwtFilter that extends OncePerRequestFilter to validate the token.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserSecurityService userSecurityService;

    public JwtFilter(JwtUtil jwtUtil, UserSecurityService
            userSecurityService) {
        this.jwtUtil = jwtUtil;
        this.userSecurityService = userSecurityService;
    }

    /**
     * Checks if the request has a valid JWT token then it sets the
     * Authentication in the context, to specify the user as
     * authenticated.
     *
     * @param httpServletRequest HttpServletRequest object that
     *                           contains the request the client
     *                           made.
     * @param httpServletResponse HttpServletResponse object that
     *                            contains the response returns to
     *                            the client.
     * @param filterChain the filter chain reflects the order of
     *                    the filters.
     * @throws ServletException if the request cannot be handled.
     * @throws IOException if an input or output error occurs while
     *                     handling the request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,FilterChain filterChain)
            throws ServletException, IOException {
        String token = null;
        String userName = null;
        String authorizationHeader = httpServletRequest.getHeader(
                "Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith(
                "Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.getUsernameFromToken(token);
        }

        if (userName != null && SecurityContextHolder.getContext()
                .getAuthentication() == null) {
            UserDetails userDetails = userSecurityService.loadUserByUsername
                    (userName);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken
                        usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(
                        usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}