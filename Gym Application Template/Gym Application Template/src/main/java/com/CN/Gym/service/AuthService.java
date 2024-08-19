package com.CN.Gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import com.CN.Gym.jwt.JwtAuthenticationHelper;

import com.CN.Gym.dto.JwtRequest;
import com.CN.Gym.dto.JwtResponse;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager manger;
	
	
	@Autowired
	JwtAuthenticationHelper helper;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	public JwtResponse login(JwtRequest jwtRequest) {
		//authenciate the request first with authenticationmanger
		this.doauthentication(jwtRequest.getUsername(),jwtRequest.getPassword());
		UserDetails userDetails=userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=helper.generateToken(userDetails);
		JwtResponse jwtResponse=JwtResponse.builder().jwtToken(token).build();
		return jwtResponse;
	}
	private void doauthentication(String username, String password) {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
			manger.authenticate(usernamePasswordAuthenticationToken);
		}
		catch (BadCredentialsException e) {
			
			throw new BadCredentialsException("Invalid Credentials");
		}
		
	}

}
