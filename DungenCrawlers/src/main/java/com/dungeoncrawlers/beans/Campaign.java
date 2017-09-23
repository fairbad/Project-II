package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="CAMPAIGNS")
public class Campaign implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CAMPAIGN_ID")
	@SequenceGenerator(name="CAMPAIGN_ID_SEQ", sequenceName="CAMPAIGN_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAMPAIGN_ID_SEQ")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String image;
	
	@Column
	private int numViews;
	
	@Column
	private float rating; //The average of all ratings
	
	@Column(nullable=false)
	private boolean isPublic;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="USER_ID", nullable=false)
	private User author;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="MAP_ID")
	private Map map;

	public Campaign() {}

	public Campaign(int id, String name, String description, String image, int numViews, float rating, boolean isPublic,
			User author, Map map) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.numViews = numViews;
		this.rating = rating;
		this.isPublic = isPublic;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
