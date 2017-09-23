package com.dungeoncrawlers.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="EVENT_TYPES")
public class EventType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EVENT_TYPE_ID")
	@SequenceGenerator(name="EVENT_TYPE_ID_SEQ", sequenceName="EVENT_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENT_TYPE_ID_SEQ")
	private int id;
	
	@Column
	private String event;

	public EventType() {}
	
	public EventType(int id, String event) {
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
