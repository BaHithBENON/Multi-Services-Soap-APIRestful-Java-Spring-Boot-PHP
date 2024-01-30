package com.javaws.entities;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String login;
	private String password;
	
	public User() { 
		this.id = -1;
	}
	
	public User(int id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password) {
		super();
		this.id = -1;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
