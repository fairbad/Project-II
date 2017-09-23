package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTERS")
public class Character implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CHARACTER_ID")
	@SequenceGenerator(name = "CHARACTER_ID_SEQ", sequenceName = "CHARACTER_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHARACTER_ID_SEQ")
	private int id;

	@Column
	private String name;

	@Column
	private String image;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User author;

	public Character() {
	}

	public Character(int id, String name, String image, User author) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.author = author;
	}

	public Character(String name, String image, User author) {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", image=" + image + ", author=" + author + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
