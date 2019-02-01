package com.cloud.application.authserver.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtTokenConfig {

	@Value("${security.jwt.auth-uri:/auth/**}")
	private String uri;

	@Value("${security.jwt.expiration:#{24*60*60}}")
	private int expirationTimeSecs;

	@Value("${security.jwt.secret:SecretSalt}")
	private String secret;

	@Value("${security.jwt.prefix:Bearer }")
	private String prefix;

	@Value("${security.jwt.header:Authorization}")
	private String header;

	public String getUri() {
		return null;
	}

	public int getExpiration() {
		return expirationTimeSecs;
	}

	public String getSecret() {
		return secret;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getHeader() {
		return header;
	}

}
