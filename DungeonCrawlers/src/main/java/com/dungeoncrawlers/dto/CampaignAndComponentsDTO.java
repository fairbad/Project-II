package com.dungeoncrawlers.dto;

import java.util.List;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Map;

public class CampaignAndComponentsDTO {
	
	Campaign campaign;
	
	Map map;
	
	List<ChapterAndLocationsDTO> chapters;

	public CampaignAndComponentsDTO() {}
	
	public CampaignAndComponentsDTO(Campaign campaign, Map map, List<ChapterAndLocationsDTO> chapters) {
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

	public List<ChapterAndLocationsDTO> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterAndLocationsDTO> chapters) {
		this.chapters = chapters;
	}
}
