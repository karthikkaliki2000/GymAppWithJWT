package com.CN.Gym.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.repository.UserRepository;



@Service
public class CustomUserDetailService implements UserDetailsService {

	
	private final UserRepository userRepository;
	
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
		
		return this.userRepository.findByEmail(username).orElseThrow(()->new UserNotFoundException("User Not Found"));
	}

}
