
/*
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.*
 *
 * This software is the confidential and proprietary information 
 * of Ideas2it Technologies. 
 */

package com.ideas2it.employeemanagement.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Class that generates Json web token fro the clienta and validates
 * 		the token for further request made by the client which is
 * 		authenticated.
 *
 * @author IMRAN NAZIR K
 *
 * @version 1.0
 */
@Service
public class JwtUtil {
	
	private String secret = "ideas2Tt@123";
	
	/**
	 * Builds a Json web token for the user. The json web token is
	 * 		build by setting the claims and digitally signed with a
	 * 		algorithm and a secret by compacting it into a URL-safe
	 * 		JWT String.
	 * 
	 * @param username the username given by the clien as String.
	 * 
	 * @return A compact URL-safe JWT string.
	 */
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()
			    + 1000 * 60 * 60)).signWith(SignatureAlgorithm.HS256,
			    secret).compact();
	}
	
	/**
	 * validate the token for further request made by the client
	 * 		which is authenticated. 
     * 
	 * @param token that is extracted from the HttpHeader.
	 * @param user given for authentication.
	 * 
	 * @return true when the given username and the username in the
	 * 		token is equal and the token is not expired.
	 */
	public Boolean validateToken(String token, UserDetails user) {
		String username = extractUsername(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
	
	/**
	 * Extracts the username fro the token.
	 * 
	 * @param token that is extracted from the HttpHeader.
	 * 
	 * @return username as claim.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims :: getSubject);
	}

	/**
	 * Checks whether the token is expired or not by comparing the
	 * 		date if this date is before the specified date.
	 * 
	 * @param token that is extracted from the HttpHeader.
	 * 
	 * @return true if the token is not expired.
	 */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Extracts the expiration date from the token to validate
	 * 		the token Expiration date.
	 * 
	 * @param token that is extracted from the HttpHeader.
	 * 
	 * @return the expiration date.
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims :: getExpiration);
	}

	/**
	 * Extracts claims from the token by applying Function to extract
	 * 		that claim from that token.
	 * 
	 * @param <T> type of the input function.
	 * @param token that is extracted from the HttpHeader.
	 * @param claimsResolver reference variable for Function
	 *        interface.
	 * 
	 * @return the function result.
	 */
	private <T> T extractClaim(String token, Function<Claims, T>
			claimsResolver) {
        final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extracts all the claims from the token.
	 * 
	 * @param token that is extracted from the HttpHeader.
	 * 
	 * @return the claims extracted from the token.
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
				.getBody();
	}
}
