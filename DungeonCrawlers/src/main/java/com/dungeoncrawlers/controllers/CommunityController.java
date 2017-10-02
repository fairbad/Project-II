package com.dungeoncrawlers.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.dto.CampaignDTO;
import com.dungeoncrawlers.service.ServiceInterface;

@RestController
@RequestMapping(value="/community")
public class CommunityController {

	@Autowired
	private ServiceInterface serviceimpl;

	public void setServiceImpl(ServiceInterface serviceImpl) {
		this.serviceimpl = serviceImpl;
	}
	
	@RequestMapping(value="/getPublicCampaigns", method= {RequestMethod.GET},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CampaignDTO>> getPublicCampaigns(HttpSession session){
		System.out.println("getting all public campaign");
        List<Campaign> campaigns = serviceimpl.getAllPublicCampaigns();
        List<CampaignDTO> campaignsDTO = new ArrayList<>();
        for(Campaign c: campaigns) {
        	CampaignDTO temp = new CampaignDTO();
        	temp.setId(c.getId());
        	temp.setName(c.getName());
        	temp.setDescription(c.getDescription());
        	temp.setImage(c.getImage());
        	temp.setNumViews(c.getNumViews());
        	temp.setRating(c.getRating());
        	temp.setPublic(c.isPublic());
        	temp.setUser(c.getUser());
        	temp.setMap(c.getMap());
        	campaignsDTO.add(temp);
        }
        
		return new ResponseEntity<List<CampaignDTO>>(campaignsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/incrementView", method = {RequestMethod.POST}, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> incrementView(@RequestBody CampaignDTO campaignDTO){
		Campaign campaign = new Campaign();
		campaign.setId(campaignDTO.getId());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews()+1);
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		campaign.setUser(campaignDTO.getUser());
		serviceimpl.updateCampaign(campaign);
		System.out.println(campaign.toString());
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/addRating", method = {RequestMethod.POST}, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> addRating(@RequestBody int id){
		System.out.println("adding a rating");
		//serviceimpl.addRating();
		return null;
	}
}