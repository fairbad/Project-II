package com.dungeoncrawlers.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.CampaignDTO;
import com.dungeoncrawlers.service.ServiceInterface;

@RestController
@RequestMapping(value="/campaign")
public class CampaignController {

	@Autowired
	private ServiceInterface serviceimpl;

	public void setServiceImpl(ServiceInterface serviceImpl) {
		this.serviceimpl = serviceImpl;
	}
	
	@RequestMapping(value="/details", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CampaignDTO> createCampaign(HttpSession session, @RequestBody CampaignDTO campaignDTO){
		System.out.println("creating new campaign");
        User currentUser = (User) session.getAttribute("user");
        campaignDTO.setUser(currentUser);
        campaignDTO.setId(serviceimpl.addCampaign(campaignDTO).getId());
		return new ResponseEntity<CampaignDTO>(campaignDTO, HttpStatus.OK);
	}

}
