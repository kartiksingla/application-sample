package com.cloud.application.authserver.repo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cloud.application.authserver.exception.UserNotFoundException;
import com.cloud.application.authserver.model.AuthUser;

@Repository
public class UserDetailsRepository {

	@Autowired
	private BCryptPasswordEncoder encoder;
	final List<AuthUser> users = Arrays.asList(new AuthUser(1, "admin", encoder.encode("admin"), "ADMIN"),
			new AuthUser(1, "user", encoder.encode("user"), "USER"));

	public AuthUser getUserDetails(String username) throws UserNotFoundException {
		Optional<AuthUser> authUser = this.users.stream().filter(user-> user.getUsername().equals(username)).findFirst();
		if(authUser.isPresent())
			return authUser.get();
		throw new UserNotFoundException(username);
	}
}
