package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="MAPS")
public class Map implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MAP_ID")
	@SequenceGenerator(name="MAP_ID_SEQ", sequenceName="MAP_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAP_ID_SEQ")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String image;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User author;
	
	public Map() {}
	
	public Map(int id, String name, String description, String image, User author) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
}
