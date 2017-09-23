package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ENEMIES")
public class Enemy implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name="ENEMY_ID")
    @SequenceGenerator(name="ENEMY_ID_SEQ", sequenceName="ENEMY_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENEMY_ID_SEQ")
    private int id;

    @Column
    private String name;

    @Column
    private String image;

    public Enemy() {}
    
	public Enemy(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
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
}
