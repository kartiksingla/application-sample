package com.cloud.application.userservice.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cloud.application.userservice.model.UserDetails;

@Repository
public class UserDetailsStore implements UserRepository {

	// @Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public UserDetails getUserDetails(String id) {
		return entityManager.find(UserDetails.class, id);
	}

	@Transactional
	public UserDetails save(UserDetails user) {
		entityManager.merge(user);
		return user;
	}

}
