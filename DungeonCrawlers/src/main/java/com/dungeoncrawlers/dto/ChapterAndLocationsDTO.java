package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Chapter;

public class ChapterAndLocationsDTO {

	Chapter chapter;
	
	List<LocationAndEventsDTO> locations;

	public ChapterAndLocationsDTO() {}
	
	public ChapterAndLocationsDTO(Chapter chapter, List<LocationAndEventsDTO> locations) {
		super();
		this.chapter = chapter;
		this.locations = locations;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public List<LocationAndEventsDTO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationAndEventsDTO> locations) {
		this.locations = locations;
	}
}
