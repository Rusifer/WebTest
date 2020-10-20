package com.webtest.web;

public class User {
	private String userId;
	private String userName;
	private String email;
	private String pwd;
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", pwd=" + pwd + "]";
	}


	
}
