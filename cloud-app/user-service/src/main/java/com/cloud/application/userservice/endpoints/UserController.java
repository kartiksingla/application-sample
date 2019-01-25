package com.cloud.application.userservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.application.userservice.model.UserDetails;
import com.cloud.application.userservice.services.UserDetailsService;

@RestController
public class UserController {

	@Autowired
	private UserDetailsService userManagerService;

	@GetMapping("user/{id}")
	public ResponseEntity<UserDetails> getUser(@PathVariable String id) {
		UserDetails userDetails = userManagerService.getUserById(id);
		return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
	}
	
	@PostMapping("user")
	public ResponseEntity<String> addUser(@RequestBody UserDetails user) {
		UserDetails userDetails = userManagerService.addUser(user);
		return new ResponseEntity<String>(userDetails.getId(),HttpStatus.CREATED);
	}
}
