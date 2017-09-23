package com.dungeoncrawlers.dto;

import javax.persistence.Column;

public class EventTypeDTO {
	private int id;

	@Column
	private String event;

	public EventTypeDTO() {
	}

	public EventTypeDTO(int id, String event) {
		super();
		this.id = id;
		this.event = event;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
