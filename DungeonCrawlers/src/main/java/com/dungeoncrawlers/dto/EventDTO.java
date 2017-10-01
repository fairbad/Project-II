package com.dungeoncrawlers.dto;

import java.util.ArrayList;
import java.util.List;

import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.EventType;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.NonPlayableCharacter;

public class EventDTO {
	private int id;

	private String name;

	private String description;

	private String image;

	private EventType event;

	private int location_id;
	//private Location location;

	private List<NonPlayableCharacter> nonPlayableCharacters;

	private List<Enemy> enemies;

	public EventDTO() {
	}

	public EventDTO(int id, String name, String description, String image, EventType event, int location_id,
			ArrayList<NonPlayableCharacter> nonPlayableCharacters, ArrayList<Enemy> enemies) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		//this.event = event;
		this.location_id = location_id;
		//this.nonPlayableCharacters = nonPlayableCharacters;
		//this.enemies = enemies;
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

	public EventType getEvent() {
		return event;
	}

	public void setEvent(EventType event) {
		this.event = event;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public List<NonPlayableCharacter> getNonPlayableCharacters() {
		return nonPlayableCharacters;
	}

	public void setNonPlayableCharacters(ArrayList<NonPlayableCharacter> nonPlayableCharacters) {
		this.nonPlayableCharacters = nonPlayableCharacters;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

}
