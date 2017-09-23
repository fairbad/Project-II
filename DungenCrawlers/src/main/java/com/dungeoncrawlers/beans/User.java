package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="USER_ID_SEQ", sequenceName="USER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_SEQ")
	private int id;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;

	public User() {}
	
	public User(int id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
