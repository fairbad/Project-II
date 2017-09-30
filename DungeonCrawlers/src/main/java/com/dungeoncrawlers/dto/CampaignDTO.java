package com.dungeoncrawlers.dto;

import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.User;

public class CampaignDTO {
	private int id;

	private String name;

	private String description;

	private String image;

	private int numViews;

	private float rating;

	private boolean isPublic;

	private User user;

	private Map map;

	public CampaignDTO() {}

	public CampaignDTO(int id, String name, String description, String image, int numViews, float rating, boolean isPublic,
			User user, Map map) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.numViews = numViews;
		this.rating = rating;
		this.isPublic = isPublic;
		this.user = user;
		this.map = map;
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

	public int getNumViews() {
		return numViews;
	}

	public void setNumViews(int numViews) {
		this.numViews = numViews;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CampaignDTO [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", numViews=" + numViews + ", rating=" + rating + ", isPublic=" + isPublic + ", user=" + user
				+ ", map=" + map + "]";
	}
}
