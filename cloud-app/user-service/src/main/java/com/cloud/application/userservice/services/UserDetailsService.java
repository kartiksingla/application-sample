package com.cloud.application.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.application.userservice.model.UserDetails;
import com.cloud.application.userservice.repo.UserDetailsStore;

@Service
public class UserDetailsService {

	@Autowired
	private UserDetailsStore userDetailsStore;
	
	public UserDetails getUserById(String id) {
		return userDetailsStore.getUserDetails(id);
	}

	public UserDetails addUser(UserDetails user) {
		return userDetailsStore.save(user);
	}

	
}
