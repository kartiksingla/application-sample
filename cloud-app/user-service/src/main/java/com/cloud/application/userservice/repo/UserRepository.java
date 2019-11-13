package com.cloud.application.userservice.repo;

import com.cloud.application.userservice.model.UserDetails;

public interface UserRepository {

	UserDetails getUserDetails(String id);

	UserDetails save(UserDetails user);

}