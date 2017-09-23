package com.dungeoncrawlers.service;

import java.util.List;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Character;
import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.EventType;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.NonPlayableCharacter;
import com.dungeoncrawlers.beans.Rating;
import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.CampaignDTO;
import com.dungeoncrawlers.dto.ChapterDTO;
import com.dungeoncrawlers.dto.CharacterDTO;
import com.dungeoncrawlers.dto.EnemyDTO;
import com.dungeoncrawlers.dto.EventDTO;
import com.dungeoncrawlers.dto.EventTypeDTO;
import com.dungeoncrawlers.dto.LocationDTO;
import com.dungeoncrawlers.dto.MapDTO;
import com.dungeoncrawlers.dto.NPCDTO;
import com.dungeoncrawlers.dto.RatingDTO;
import com.dungeoncrawlers.dto.UserDTO;

public interface ServiceInterface {

	/*
	 * User Operations
	 */
	
	public User getUser(int id);
	
	public User addUser(UserDTO userDTO);
	
	public User updateUser(UserDTO userDTO);
	
	/*
	 * Campaign Operations
	 */
	public Campaign getCampaign(int id);
	
	public Campaign addCampaign(CampaignDTO campaignDTO);
	
	public Campaign updateCampaign(CampaignDTO campaignDTO);
	
	public void deleteCampaign(CampaignDTO campaignDTO);
	
	public List<Campaign> getAllCampaignsByUser(UserDTO userDTO);
	
	public List<Campaign> getAllPublicCampaigns();

	// update order method
	//public
	
	/*
	 * Chapter Operations
	 */
	public Chapter getChapter(int id);
	
	public Chapter addChapter(ChapterDTO chapterDTO);
	
	public Chapter updateChapter(ChapterDTO chapterDTO);
	
	public void deleteChapter(ChapterDTO chapterDTO);
	
	public List<Chapter> getAllChaptersByCampaign(CampaignDTO campaignDTO);
	
	/*
	 * Map Operations
	 */
	public Map getMap(int id);
	
	public Map addMap(MapDTO mapDTO);
	
	public Map updateMap(MapDTO mapDTO);
	
	public void deleteMap(MapDTO mapDTO);
	
	public List<Map> getAllMapsByUser(UserDTO userDTO);
	
	/*
	 * Location Operations
	 */
	public Location getLocation(int id);
	
	public Location addLocation(LocationDTO locationDTO);
	
	public Location updateLocation(LocationDTO locationDTO);
	
	public void deleteLocation(LocationDTO locationDTO);
	
	public List<Location> getAllLocationsByChapter(ChapterDTO chapterDTO);
	
	/*
	 * Event Operations
	 */
	public Event getEvent(int id);
	
	public Event addEvent(EventDTO eventDTO);
	
	public Event updateEvent(EventDTO eventDTO);
	
	public void deleteEvent(EventDTO eventDTO);
	
	public List<Event> getAllEventsByLocation(LocationDTO locationDTO);
	
	/*
	 * Event Type Operations
	 */
	
	public EventType getEventType(int id);
	
	public EventType addEventType(EventTypeDTO eventTypeDTO);
	
	public List<EventType> getAllEventTypes();
	
	/*
	 * Character Operations
	 */
	public Character getCharacter(int id);
	
	public Character addCharacter(CharacterDTO characterDTO);
	
	public Character updateCharacter(CharacterDTO characterDTO);
	
	public void deleteCharacter(CharacterDTO characterDTO);
	
	public List<Character> getAllCharactersByUser(UserDTO userDTO);
	
	/*
	 * Enemy Operations
	 */
	public Enemy getEnemy(int id);
	
	public Enemy addEnemy(EnemyDTO enemyDTO);
	
	public Enemy updateEnemy(EnemyDTO enemyDTO);
	
	public void deleteEnemy(EnemyDTO enemyDTO);
	
	public List<Enemy> getAllEnemiesByEvent(EventDTO eventDTO);
	
	public List<Enemy> getAllEnemiesByUser(UserDTO userDTO);
	
	/*
	 * NonPlayableCharacter Operations
	 */
	public NonPlayableCharacter getNonPlayableCharacter(int id);
	
	public NonPlayableCharacter addNonPlayableCharacter(NPCDTO npcDTO);
	
	public NonPlayableCharacter updateNonPlayableCharacter(NPCDTO npcDTO);
	
	public void deleteNonPlayableCharacter(NPCDTO npcDTO);
	
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByEvent(EventDTO eventDTO);
	
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByUser(UserDTO userDTO);
	
	/*
	 * Rating Operations
	 */
	public Rating getRating(int id);
	
	public Rating addRating(RatingDTO ratingDTO);
	
	public Rating updateRating(RatingDTO ratingDTO);
	
	public List<Rating> getAllRatingsByCampaign(CampaignDTO campaignDTO);
	
	public List<Rating> getAllRatingsByUser(UserDTO userDTO);
	
	public void updateAllCampaignRatings();
	
}
