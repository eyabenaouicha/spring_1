package com.example.demo.security;
/*
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.core.userdetails.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	 private AuthenticationManager authenticationManager;
	 public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	 this.authenticationManager = authenticationManager;
	 }
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse
	response) throws AuthenticationException {
	 try {
	 com.example.demo.entity.User appUser= new ObjectMapper().readValue(request.getInputStream(), com.example.demo.entity.User.class);
	 return authenticationManager.authenticate(new
	UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
	 } catch (IOException e) {
	 e.printStackTrace();
	 throw new RuntimeException(e);
	 }
	 }
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse
	response, FilterChain chain, Authentication authResult) throws IOException, ServletException
	{
		User user=(User)authResult.getPrincipal();
	 List<String> roles=new ArrayList<>();
	 authResult.getAuthorities().forEach(a->{
	 roles.add(a.getAuthority());
	 });
	 String jwt= JWT.create()
	 .withIssuer(request.getRequestURI())
	 .withSubject(user.getUsername())
	 .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
	 .withExpiresAt(new
	Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
	 .sign(Algorithm.HMAC256(SecurityParams.SECRET));
	 response.addHeader(SecurityParams.JWT_HEADER_NAME,jwt); }
	} 

