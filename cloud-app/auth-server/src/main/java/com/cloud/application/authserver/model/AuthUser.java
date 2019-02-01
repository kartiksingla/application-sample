package com.cloud.application.authserver.model;

public class AuthUser {
	private String username, password, roles;
	private Integer id;

	public AuthUser(Integer id, String username, String password, String roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
