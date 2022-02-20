package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.security.core.authority.SimpleGrantedAuthority;




	@Entity
	@Table(name = "USER")
	public class User implements Serializable , UserDetails {
	
	public User( String username, String password) {
			super();
			
			this.username = username;
			this.password = password;
		}
	

	

	public User() {

	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String username;
	private String password;
	private boolean enabled;
	
	public User(Integer userId, String username, String password, boolean enabled, Set<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id")
			
			)
	
	private Set<Role> roles=new HashSet<>();
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getUserId() {
	return userId;
	}
	public void setUserId(Integer userId) {
	this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
	return false;
	}
	@Override
	public boolean isAccountNonLocked() {
	return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	return false;
	}
	@Override
	public boolean isEnabled() {
	return false;
	}
	
  
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles=this.getRoles();
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
	return authorities;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	
	}

	
	
	}

