/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This software is the confidential and proprietary information
 * of Ideas2it Technologies.
 */
package com.ideas2it.fooddeliveryapp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JWT util class for token generation.
 *
 * @author Govindaraj
 * @version 1.0
 * @since 04/01/2023
 */
@Component
public class JwtUtil {

    private final String secretKey = "secretKey";

    /**
     * Gets username from the token.
     *
     * @param token The JWT token.
     * @return The username from Token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Gets expire date from the token.
     *
     * @param token The JWT token.
     * @return expire date of the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Uses token to get all the claims from it, and then apply
     * the claimsResolver function to the claims.
     *
     * @param token          The JWT token.
     * @param claimsResolver A function that takes in a Claims object and
     *                       returns a value of type T.
     * @return A generic type T is being returned.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Validates a token and returns the claims.
     *
     * @param token The token that needs to be validated.
     * @return The claims are being returned.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks Expire date of the token, if date is before
     * the current date, then the token is expired.
     *
     * @param token The check JWT token is expired or not.
     * @return A boolean value is token is expired or not.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Validates userDetails object and returns a JWT token.
     *
     * @param userDetails is the user object that contains the user
     *                    information.
     * @return A JWT token  pass a token as String.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Generates and return a JWT token using map of claims,
     * a subject, and a secret key.
     *
     * @param claims  A map of claims that will be added to the JWT.
     * @param subject The subject of the token.
     * @return A JWT token  generate token with signature
     */
    private String doGenerateToken(Map<String, Object> claims, String
            subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + 10 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * Checks the username in the token matches the username in
     * the userDetails object and the token is not expired, then
     * return true.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The user details object that contains
     *                    the username and password of the user.
     * @return true if user exist.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired
                (token));
    }
}