package com.cloud.application.authserver.security.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cloud.application.authserver.model.UserCredentials;
import com.cloud.application.authserver.security.config.JwtTokenConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUserPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtTokenConfig jwtTokenConfig;

	private AuthenticationManager authManager;

	public JwtUserPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
			JwtTokenConfig jwtTokenConfiguration) {
		this.authManager = authenticationManager;
		this.jwtTokenConfig = jwtTokenConfiguration;

		this.setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher(jwtTokenConfig.getUri(), HttpMethod.POST.name()));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserCredentials userCred = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
																	userCred.getUsername(),
																	userCred.getPassword(),
																	Collections.emptyList());
			return authManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		Long currentTime = System.currentTimeMillis();
		String token = Jwts.builder()
						   .setSubject(auth.getName())
						   .claim("authorities", 
								   auth.getAuthorities().stream()
								       .map(GrantedAuthority::getAuthority)
								       .collect(Collectors.toList()))
						   .setIssuedAt(new Date(currentTime))
						   .setExpiration(new Date(currentTime + jwtTokenConfig.getExpiration()*1000))
						   .signWith(SignatureAlgorithm.HS512, jwtTokenConfig.getSecret().getBytes())
						   .compact();
		
		response.addHeader(jwtTokenConfig.getHeader(), jwtTokenConfig.getPrefix()+ token);
	}

}
