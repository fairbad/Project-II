package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.Campaign;

public class ChapterDTO {
	private int id;

	private String name;

	private String description;

	private String image;

	private Campaign campaign;

	public ChapterDTO(int id, String name, String description, String image, int order, Campaign campaign) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.campaign = campaign;
	}

	public ChapterDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
}
