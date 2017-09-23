package com.dungeoncrawlers.dto;
public class UserDTO {
	private int id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private boolean authenticated;

	public UserDTO() {
		super();
	}

	public UserDTO(int id, String username, String email, String password, boolean authenticated) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authenticated = authenticated;
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

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", authenticated=" + authenticated + "]";
	}
	
	
}