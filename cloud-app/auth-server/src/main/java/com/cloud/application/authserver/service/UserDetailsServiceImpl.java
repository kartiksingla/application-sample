package com.cloud.application.authserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.application.authserver.exception.UserNotFoundException;
import com.cloud.application.authserver.model.AuthUser;
import com.cloud.application.authserver.repo.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			AuthUser user = userRepository.getUserDetails(username);
			List<GrantedAuthority> authorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_" + user.getRoles());
			return new User(user.getUsername(), user.getPassword(), authorities);
		} catch (UserNotFoundException e) {
			throw new UsernameNotFoundException(e.getLocalizedMessage());
		}
	}

}
