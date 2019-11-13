package com.cloud.application.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloud.application.userservice.exceptions.UserNotExistsException;
import com.cloud.application.userservice.model.UserDetails;
import com.cloud.application.userservice.repo.UserRepository;

@Service
public class UserDetailsService {

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Autowired
	public UserDetailsService(UserRepository userDetailsStore) {
		this.userRepo = userDetailsStore;
	}
	
	private UserRepository userRepo;
	
	public UserDetails getUserById(String id) {
		UserDetails user = userRepo.getUserDetails(id);
		if (user == null)
			throw new UserNotExistsException("User with ID : "+id+" not found. ");
		return user;
	}

	public UserDetails addUser(UserDetails user) {
		user.setName(user.getName() + " " + activeProfile);
		return userRepo.save(user);
	}

}
