
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
                                                                                                                                        
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.util.JwtUtil;

/**
 * Class acts as Interceptor for JWT security that validates the
 * 		username and password of an client to proceed further
 * 		requests.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Component
public class JwtFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	private final EmployeeService employeeService;
	
	public JwtFilter(EmployeeService employeeService, JwtUtil jwtUtil) {
		this.employeeService = employeeService;
		this.jwtUtil = jwtUtil;
	}
	
	/**
     * Checks if the request has a valid JWT token. If it has a valid JWT
     * 		Token then it sets the Authentication in the
     *      SecurityContextHolder to specify that the current user is
     *      authenticated.
     * 
     * @param httpServletRequest the HttpServletRequest object that
     *        contains the request the client made.
     * @param httpServletResponse the HttpServletResponse object that
              contains the response that to be returned to the client.
     * @param FilterChain the filter chain reflects the order of
              the filters.
     * 
     * @throws servletException if the request cannot be handled
     * @throws IOException if an error occurs while the servlet
     *         handling the request
     */
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		String token = new String();
		String username = null;
		
		String authorizationHeader = httpServletRequest.getHeader
				("Authorization");
		
		if (null != authorizationHeader && authorizationHeader
				.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(token);
		}

		if (null != username && null == SecurityContextHolder.getContext()
				.getAuthentication()) {
			UserDetails user = employeeService.loadUserByUsername(username);
			
			if (jwtUtil.validateToken(token, user)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuth 
						= new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				usernamePasswordAuth.setDetails
						(new WebAuthenticationDetailsSource()
						.buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication
						(usernamePasswordAuth);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
