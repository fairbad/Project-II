package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Map;

public class CampaignChapterDTO {

	private Campaign campaign;
	
	private Map map;
	
	private List<ChapterLocationDTO> chapters;

	public CampaignChapterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CampaignChapterDTO(Campaign campaign, Map map, List<ChapterLocationDTO> chapters) {
		super();
		this.campaign = campaign;
		this.map = map;
		this.chapters = chapters;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<ChapterLocationDTO> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterLocationDTO> chapters) {
		this.chapters = chapters;
	}
	
}


