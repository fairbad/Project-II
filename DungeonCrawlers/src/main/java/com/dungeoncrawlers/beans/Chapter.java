package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CHAPTER_ID")
	@SequenceGenerator(name="CHAPTER_ID_SEQ", sequenceName="CHAPTER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHAPTER_ID_SEQ")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String image;
	
	//@Column
	//private int order;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="CAMPAIGN_ID", nullable=false)
	private Campaign campaign;

	public Chapter() {}
	
	

	public Chapter(int id, String name, String description, String image, Campaign campaign) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.campaign = campaign;
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

	/*
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}*/

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
}
