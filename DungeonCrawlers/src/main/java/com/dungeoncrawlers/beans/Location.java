package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="LOCATIONS")
public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LOCATION_ID")
	@SequenceGenerator(name="LOCATION_ID_SEQ", sequenceName="LOCATION_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATION_ID_SEQ")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String image;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="CHAPTER_ID", nullable=false)
	private Chapter chapter;

	public Location() {}
	
	public Location(int id, String name, String description, String image, Chapter chapter) {
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
