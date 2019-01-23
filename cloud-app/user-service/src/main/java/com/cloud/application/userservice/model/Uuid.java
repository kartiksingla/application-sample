package com.cloud.application.userservice.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Uuid implements Serializable {

	private static final long serialVersionUID = -8062754253161676678L;
	@Id
	private String id;

	public Uuid() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

}
