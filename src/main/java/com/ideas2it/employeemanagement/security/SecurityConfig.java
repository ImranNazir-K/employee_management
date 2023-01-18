 
/* Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is confidential and proprietary information 
 * of Ideas2it Technologies. 
 */
package com.ideas2it.employeemanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ideas2it.employeemanagement.filter.JwtFilter;

/**
 * Class for configuration of SecurityChainFilter to provide security
 * 		to our application.
 *
 * @author IMRAN NAZIR K
 *
 * @version 6.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;
	private final UserDetailsService user;
	
    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService user) {
    	this.jwtFilter = jwtFilter;
    	this.user =  user;
    }

    /**
     * Configuration for SecurityFilterChain that filters the
     * HttpMethods request from the client.
     * 
     * @param httpSecurity for security configuration.
     * 
     * @return httpSecurity for our application.
     * 
     * @throws Exception if authentication is invalid.
     */
    @Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity)
			throws Exception {
    	
		httpSecurity
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST).permitAll()
			
			.anyRequest().authenticated()
			.and().exceptionHandling().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy
					.STATELESS);
		
		httpSecurity.addFilterBefore(jwtFilter,
				UsernamePasswordAuthenticationFilter.class);;
		return httpSecurity.build();
	}
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @Bean
    protected AuthenticationManager authenticationManagerBean
    		(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    /**
     * Provides Authentication provider to verify the user
     * credentials.
     * 
     * @return configured authencation provider.
     */
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider
        		= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(user);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    
    /**
     * Encodes the password with BcryptPasswordEncoder and return the
     * encrypted characters.
     * 
     * @return encrypted password.
     */
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
