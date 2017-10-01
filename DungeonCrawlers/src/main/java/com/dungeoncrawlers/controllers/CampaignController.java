package com.dungeoncrawlers.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.CampaignAndComponentsDTO;
import com.dungeoncrawlers.dto.CampaignDTO;
import com.dungeoncrawlers.dto.ChapterAndLocationsDTO;
import com.dungeoncrawlers.dto.ChapterDTO;
import com.dungeoncrawlers.dto.EventDTO;
import com.dungeoncrawlers.dto.LocationAndEventsDTO;
import com.dungeoncrawlers.dto.LocationDTO;
import com.dungeoncrawlers.dto.MapDTO;
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
		if(campaignDTO.getMap() == null) {
			campaignDTO.setMap(new Map());
			campaignDTO.getMap().setImage("MissingMapImage.png");
		}
		campaignDTO.setImage("MissingCampaignImage.png");
        User currentUser = (User) session.getAttribute("user");
        campaignDTO.setUser(currentUser);
        campaignDTO.setPublic(true);
        Campaign campaign = serviceimpl.addCampaign(campaignDTO);
        campaignDTO.setId(campaign.getId());
		return new ResponseEntity<CampaignDTO>(campaignDTO, HttpStatus.OK);
	}
	/*
	@RequestMapping(value="/map", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MapDTO> createMap(HttpSession session, @RequestBody MapDTO mapDTO){
		System.out.println("creating new map");
        mapDTO.setUser(currentCampaign.getUser());
        Map map = serviceimpl.addMap(mapDTO);
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setId(currentCampaign.getId());
        campaignDTO.setName(currentCampaign.getName());
        campaignDTO.setDescription(currentCampaign.getDescription());
        campaignDTO.setMap(map);
        campaignDTO.setUser(currentCampaign.getUser());
        //currentCampaign = serviceimpl.updateCampaign(campaignDTO);
        mapDTO.setId(map.getId());
		return new ResponseEntity<MapDTO>(mapDTO, HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/chapter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ChapterDTO> createChapter(HttpSession session, @RequestBody ChapterDTO chapterDTO){
		System.out.println("creating new chapter");
        Chapter chapter = serviceimpl.addChapter(chapterDTO);
        chapterDTO.setId(chapter.getId());
		return new ResponseEntity<ChapterDTO>(chapterDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/location", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<LocationDTO> createLocation(HttpSession session, @RequestBody LocationDTO locationDTO){
		System.out.println("creating new location");
		Location location = serviceimpl.addLocation(locationDTO);
        locationDTO.setId(location.getId());
		return new ResponseEntity<LocationDTO>(locationDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/event", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EventDTO> createEvent(HttpSession session, @RequestBody EventDTO eventDTO){
		System.out.println("creating new event");
		Event event = serviceimpl.addEvent(eventDTO);
        eventDTO.setId(event.getId());
		return new ResponseEntity<EventDTO>(eventDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCampaigns", method= {RequestMethod.GET},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CampaignDTO>> getCampaigns(HttpSession session){
		System.out.println("getting campaigns");
        User currentUser = (User) session.getAttribute("user");
        List<Campaign> campaigns = serviceimpl.getAllCampaignsByUser(currentUser);
        List<CampaignDTO> campaignsDTO = new ArrayList<>();
        for(Campaign c: campaigns) {
        	CampaignDTO temp = new CampaignDTO(c.getId(), c.getName(), c.getDescription(), c.getImage(), c.getNumViews(), c.getRating(), c.isPublic(), currentUser, c.getMap());
        	campaignsDTO.add(temp);
        }
		return new ResponseEntity<List<CampaignDTO>>(campaignsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getCampaign", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CampaignAndComponentsDTO> getCampaign(HttpSession session, @RequestBody CampaignDTO campaignDTO){
		System.out.println("getting campaign and its components");
		System.out.println(campaignDTO.toString());
		CampaignAndComponentsDTO cacDTO;
		
		Campaign campaign = serviceimpl.getCampaign(campaignDTO.getId());
		
        if (campaign != null) {
	        List<ChapterAndLocationsDTO> chapters_locations = new ArrayList<ChapterAndLocationsDTO>();
	        List<Chapter> chapters = serviceimpl.getAllChaptersByCampaign(campaign);
	        for (Chapter c : chapters) {
	        	List<Location> locations = serviceimpl.getAllLocationsByChapter(c);
	        	List<LocationAndEventsDTO> location_events = new ArrayList<LocationAndEventsDTO>();
	        	for (Location l : locations) {
	        		List<Event> events = serviceimpl.getAllEventsByLocation(l);
	        		LocationAndEventsDTO laeDTO = new LocationAndEventsDTO(l, events);
	        		location_events.add(laeDTO);
	        	}
	        	ChapterAndLocationsDTO calDTO = new ChapterAndLocationsDTO(c, location_events);
	        	chapters_locations.add(calDTO);
	        }
	        cacDTO = new CampaignAndComponentsDTO(campaign, campaign.getMap(), chapters_locations);
        }
        else {
        	campaignDTO.setUser((User) session.getAttribute("user")); 
        	if(campaignDTO.getMap() == null) {
        		MapDTO tempMap = new MapDTO();
        		tempMap.setImage("MissingMapImage.png");
        		tempMap.setUser(campaignDTO.getUser());
        		campaignDTO.setMap(serviceimpl.addMap(tempMap));
    		}
    		campaignDTO.setImage("MissingCampaignImage.png");
        	campaign = serviceimpl.addCampaign(campaignDTO);
        	cacDTO = new CampaignAndComponentsDTO(campaign, null, null);
        }
		return new ResponseEntity<CampaignAndComponentsDTO>(cacDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/editCampaignDetails", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CampaignAndComponentsDTO> editCampaignDetails(HttpSession session, @RequestBody CampaignAndComponentsDTO cacDTO) throws IOException{
		System.out.println("creating editing campaign");
		
		KeyDecoder dec = new KeyDecoder();
		dec.HandleCampaignImages(cacDTO);
		
		MapDTO tempMap = new MapDTO();
		tempMap.setId(cacDTO.getCampaign().getMap().getId());
		tempMap.setImage(cacDTO.getCampaign().getMap().getImage());
		tempMap.setName(cacDTO.getCampaign().getMap().getName());
		tempMap.setDescription(cacDTO.getCampaign().getMap().getDescription());
		tempMap.setUser(cacDTO.getCampaign().getUser());
		
		cacDTO.getCampaign().setMap(serviceimpl.updateMap(tempMap));
		cacDTO.setCampaign(serviceimpl.updateCampaign(cacDTO.getCampaign()));
		return new ResponseEntity<CampaignAndComponentsDTO>(cacDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/editChapters", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CampaignAndComponentsDTO> editChapters(HttpSession session, @RequestBody CampaignAndComponentsDTO cacDTO){
		System.out.println("editing chapter");
		List<ChapterAndLocationsDTO> calDTO = cacDTO.getChapters();
		cacDTO.setCampaign(serviceimpl.updateCampaign(cacDTO.getCampaign()));
		return new ResponseEntity<CampaignAndComponentsDTO>(cacDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/dndLocation", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> dndLocation(@RequestBody LocationDTO locationDTO){
		
		System.out.println(serviceimpl.updateLocation(locationDTO).toString());
		
		//serviceimpl.updateChapter(chapterDTO);
		//serviceimpl.updateLocation(locationDTO);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
