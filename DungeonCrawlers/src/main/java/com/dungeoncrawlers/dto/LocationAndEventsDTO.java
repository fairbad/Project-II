package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.Location;

public class LocationAndEventsDTO {

	Location location;
	
	List<Event> events;
	
	public LocationAndEventsDTO() {}

	public LocationAndEventsDTO(Location location, List<Event> events) {
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
