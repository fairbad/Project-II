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
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.CampaignChapterDTO;
import com.dungeoncrawlers.dto.CampaignDTO;
import com.dungeoncrawlers.dto.ChapterDTO;
import com.dungeoncrawlers.dto.ChapterLocationDTO;
import com.dungeoncrawlers.dto.EventDTO;
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
        User currentUser = (User) session.getAttribute("user");
        campaignDTO.setUser(currentUser);
        campaignDTO.setPublic(true);
        Campaign campaign = serviceimpl.addCampaign(campaignDTO);
        session.setAttribute("campaign", campaign);
        campaignDTO.setId(campaign.getId());
		return new ResponseEntity<CampaignDTO>(campaignDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/map", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MapDTO> createMap(HttpSession session, @RequestBody MapDTO mapDTO){
		System.out.println("creating new map");
        Campaign currentCampaign = (Campaign) session.getAttribute("campaign");
        mapDTO.setUser(currentCampaign.getUser());
        Map map = serviceimpl.addMap(mapDTO);
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setId(currentCampaign.getId());
        campaignDTO.setName(currentCampaign.getName());
        campaignDTO.setDescription(currentCampaign.getDescription());
        campaignDTO.setMap(map);
        campaignDTO.setUser(currentCampaign.getUser());
        currentCampaign = serviceimpl.updateCampaign(campaignDTO);
        session.setAttribute("campaign", currentCampaign);
        mapDTO.setId(map.getId());
		return new ResponseEntity<MapDTO>(mapDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/chapter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ChapterDTO> createChapter(HttpSession session, @RequestBody ChapterDTO chapterDTO){
		System.out.println("creating new chapter");
        Campaign currentCampaign = (Campaign) session.getAttribute("campaign");
        chapterDTO.setCampaign(currentCampaign);
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
	public ResponseEntity<CampaignChapterDTO> getCampaign(HttpSession session, @RequestBody CampaignDTO campaignDTO){
		System.out.println("getting campaign and its components");
		System.out.println(campaignDTO.toString());
        Campaign campaign = serviceimpl.getCampaign(campaignDTO.getId());
        
        CampaignChapterDTO campaignChapterDTO = new CampaignChapterDTO();
        campaignChapterDTO.setCampaign(campaign);
        
        List<Chapter> chapters = serviceimpl.getAllChaptersByCampaign(campaign);
        List<ChapterLocationDTO> chapterLocationsDTO = new ArrayList<ChapterLocationDTO>();
        for(Chapter c: chapters) {
        	ChapterLocationDTO chapterLocationDTO = new ChapterLocationDTO();
        	chapterLocationDTO.setChapter(c);
        	chapterLocationDTO.setLocations(serviceimpl.getAllLocationsByChapter(c));
        	chapterLocationsDTO.add(chapterLocationDTO);
        }
        
        campaignChapterDTO.setChapters(chapterLocationsDTO);
        
        /*
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
        CampaignAndComponentsDTO cacDTO = new CampaignAndComponentsDTO(campaign, campaign.getMap(), chapters_locations);
		*/
        return new ResponseEntity<CampaignChapterDTO>(campaignChapterDTO, HttpStatus.OK);
	}

}
