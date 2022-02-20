package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String paasword;
	
	@OneToOne(targetEntity=User.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user_id;

	
	
	public Client(Integer id, String name, String paasword, User user_id) {
		super();
		this.id = id;
		this.name = name;
		this.paasword = paasword;
		this.user_id = user_id;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public Client(Integer id, String name, String paasword) {
		super();
		this.id = id;
		this.name = name;
		this.paasword = paasword;
	}
	public String getPaasword() {
		return paasword;
	}
	public void setPaasword(String paasword) {
		this.paasword = paasword;
	}
	public Integer getId() {
		return id;
	}
	public Client() {
	}
	public Client(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
