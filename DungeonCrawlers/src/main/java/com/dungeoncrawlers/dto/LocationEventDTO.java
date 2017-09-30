package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.Location;

public class LocationEventDTO {

	private Location location;
	
	private List<Event> events;

	public LocationEventDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationEventDTO(Location location, List<Event> events) {
		super();
		this.location = location;
		this.events = events;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
