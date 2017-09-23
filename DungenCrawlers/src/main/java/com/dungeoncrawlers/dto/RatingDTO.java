package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.User;

public class RatingDTO {
	private int id;

	private int rating;

	private Campaign campaign;

	private User user;

	public RatingDTO() {
	}

	public RatingDTO(int id, int rating, Campaign campaign, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.campaign = campaign;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}