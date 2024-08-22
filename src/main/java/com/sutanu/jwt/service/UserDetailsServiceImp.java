package com.sutanu.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sutanu.jwt.repository.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private final UserRepo usr;
	
	public UserDetailsServiceImp(UserRepo usr) {
		this.usr = usr;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usr.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found!"));
	}

}
