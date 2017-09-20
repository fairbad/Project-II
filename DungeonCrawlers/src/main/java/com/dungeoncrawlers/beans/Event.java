package com.dungeoncrawlers.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="EVENTS")
public class Event implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name="EVENT_ID")
    @SequenceGenerator(name="EVENT_ID_SEQ", sequenceName="EVENT_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENT_ID_SEQ")
    private int id;

    @Column
    private String name;
    
    @Column
    private String description;

    @Column
    private String image;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="EVENT_TYPE_ID")
    private EventType event;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="LOCATION_ID")
    private Location location;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="EVENT_NPC",
    joinColumns=@JoinColumn(name="EVENT_ID"),
    inverseJoinColumns=@JoinColumn(name="NPC_ID"))
    private List<NonPlayableCharacter> nonPlayableCharacters;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="EVENT_ENEMY",
    joinColumns=@JoinColumn(name="EVENT_ID"),
    inverseJoinColumns=@JoinColumn(name="ENEMY_ID"))
    private List<Enemy> enemies;

    public Event() {}

	public Event(int id, String name, String description, String image, EventType event, Location location,
			ArrayList<NonPlayableCharacter> nonPlayableCharacters, ArrayList<Enemy> enemies) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.event = event;
		this.location = location;
		this.nonPlayableCharacters = nonPlayableCharacters;
		this.enemies = enemies;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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