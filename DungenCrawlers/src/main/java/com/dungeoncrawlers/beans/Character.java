package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="CHARACTERS")
public class Character implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CHARACTER_ID")
	@SequenceGenerator(name="CHARACTER_ID_SEQ", sequenceName="CHARACTER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHARACTER_ID_SEQ")
	private int id;

	@Column
	private String name;

	@Column
	private String image;

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinColumn(name="USER_ID", nullable = false)
	private User author;

	public Character() {}
	
	public Character(int id, String name, String image, User author) {
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
