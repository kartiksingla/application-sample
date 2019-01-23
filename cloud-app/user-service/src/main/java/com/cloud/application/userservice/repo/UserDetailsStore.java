package com.cloud.application.userservice.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.application.userservice.model.UserDetails;

@Repository
public class UserDetailsStore {

	@Autowired
	private EntityManager em;

	@Transactional
	public UserDetails getUserDetails(String id) {
		return em.find(UserDetails.class, id);
	}

	@Transactional
	public UserDetails save(UserDetails user) {
		em.merge(user);
		return user;
	}

}
