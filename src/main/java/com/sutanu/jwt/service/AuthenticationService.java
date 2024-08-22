package com.sutanu.jwt.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sutanu.jwt.model.AuthenticationResponse;
import com.sutanu.jwt.model.User;
import com.sutanu.jwt.repository.UserRepo;

@Service
public class AuthenticationService {

	private final UserRepo usr;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepo usr, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.usr = usr;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	
	public AuthenticationResponse register(User request) {
		
		User user = new User();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(request.getRole());
		
		user = usr.save(user);
		
		String token = jwtService.generateToken((org.apache.catalina.User) user);
		
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(User request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				
				);
		User user = usr.findByUsername(request.getUsername()).orElseThrow();
		
		String token = jwtService.generateToken((org.apache.catalina.User) user);
		return new AuthenticationResponse(token);
	}
}
