package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.User;

public class EnemyDTO {
	private int id;

	private String name;

	private String image;

	private User author;

	public EnemyDTO() {
	}

	public EnemyDTO(int id, String name, String image, User author) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
