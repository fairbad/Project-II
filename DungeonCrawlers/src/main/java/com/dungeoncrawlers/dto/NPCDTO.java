package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.User;

public class NPCDTO {
	private int id;

	private String name;

	private String image;

	private User user;

	public NPCDTO() {
	}

	public NPCDTO(int id, String name, String image, User user) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

}
