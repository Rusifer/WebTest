package com.webtest.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.sql.Update;

@Entity
public class User {
	@Id
	@GeneratedValue //자동으로 1씩 증가
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String userId;
	private String name;
	private String email;
	private String password;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	public String getPassword() {
		return password;
	}


	
}
