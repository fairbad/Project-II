package com.dungeoncrawlers.dto;

public class ChapterLocationDTO {

	private ChapterDTO chapterDTO;
	
	private LocationDTO locationDTO;

	public ChapterLocationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChapterLocationDTO(ChapterDTO chapterDTO, LocationDTO locationDTO) {
		super();
		this.chapterDTO = chapterDTO;
		this.locationDTO = locationDTO;
	}

	public ChapterDTO getChapterDTO() {
		return chapterDTO;
	}

	public void setChapterDTO(ChapterDTO chapterDTO) {
		this.chapterDTO = chapterDTO;
	}

	public LocationDTO getLocationDTO() {
		return locationDTO;
	}

	public void setLocationDTO(LocationDTO locationDTO) {
		this.locationDTO = locationDTO;
	}
	
}
