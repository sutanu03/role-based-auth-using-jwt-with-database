package com.sutanu.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sutanu.jwt.model.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer>{
	
	// to search a user with username
	Optional<User> findByUsername(String username);

}
