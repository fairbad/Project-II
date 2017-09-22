package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.Chapter;

public class LocationDTO {
	private int id;

	private String name;

	private String description;

	private String image;

	private Chapter chapter;

	public LocationDTO() {
	}

	public LocationDTO(int id, String name, String description, String image, Chapter chapter) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.chapter = chapter;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
}
