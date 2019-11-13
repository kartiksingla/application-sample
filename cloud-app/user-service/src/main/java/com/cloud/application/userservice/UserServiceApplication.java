package com.cloud.application.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.cloud.sleuth.zipkin2.ZipkinBackwardsCompatibilityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = { 
		//HibernateJpaAutoConfiguration.class,
		HealthEndpointAutoConfiguration.class,
		ZipkinAutoConfiguration.class,
		ZipkinBackwardsCompatibilityAutoConfiguration.class
		//DataSourceAutoConfiguration.class
		})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
