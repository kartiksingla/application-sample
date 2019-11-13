package com.cloud.application.userservice.repo;

import java.util.HashMap;
import java.util.Map;

import com.cloud.application.userservice.model.UserDetails;

//@Repository
public class UserDetailsStoreInMemory implements UserRepository {

	private Map<String, UserDetails> userMap = new HashMap<>();

	@Override
	public UserDetails getUserDetails(String id) {
		return userMap.get(id);
	}

	@Override
	public UserDetails save(UserDetails user) {
		userMap.put(user.getId(), user);
		return user;
	}

}
