package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Location;

public class ChapterLocationDTO {

	private Chapter chapter;
	
	private List<Location> locations;

	public ChapterLocationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChapterLocationDTO(Chapter chapter, List<Location> locations) {
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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	
	
}
