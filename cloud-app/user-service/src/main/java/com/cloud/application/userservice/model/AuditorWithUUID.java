package com.cloud.application.userservice.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AuditorWithUUID implements Serializable {

	private static final long serialVersionUID = 8224910198837139085L;

	@Id
	private String id;

	@Column(name = "created_time", updatable = false, nullable = false)
	@CreatedDate
	private Instant createdAt;

	@Column(name = "last_updated_time", nullable = false , columnDefinition = "datetime default SYSTIMESTAMP" )
	@LastModifiedDate
	private Instant lastUpdatedAt;

	public AuditorWithUUID() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setId(String id) {
		this.id = id;
	}
}
