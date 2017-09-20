package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="NPCS")
public class NonPlayableCharacter implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name="NPC_ID")
    @SequenceGenerator(name="NPC_ID_SEQ", sequenceName="NPC_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NPC_ID_SEQ")
    private int id;

    @Column
    private String name;
    
    @Column
    private String image;

    public NonPlayableCharacter() {}
    
	public NonPlayableCharacter(int id, String name, String image) {
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
