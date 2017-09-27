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
	
	@RequestMapping(value="/viewCampaigns", method= {RequestMethod.GET},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CampaignDTO>> createCampaign(HttpSession session, @RequestBody CampaignDTO campaignDTO){
		System.out.println("getting all campaign");
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
}