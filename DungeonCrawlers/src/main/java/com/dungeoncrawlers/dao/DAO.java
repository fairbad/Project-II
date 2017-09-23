package com.dungeoncrawlers.dao;

import java.util.List;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.EventType;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.NonPlayableCharacter;
import com.dungeoncrawlers.beans.Rating;
import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.beans.Character;

public interface DAO {

	/*
	 * User Operations
	 */
	public User getUser(int id);
	
	public User getUser(String email, String password);
	
	public User addUser(User user);
	
	public User updateUser(User user);
	//Add this
	public User findUserByEmail(String username);
	
	/*
	 * Campaign Operations
	 */
	public Campaign getCampaign(int id);
	
	public List<Campaign> getAllCampaigns();
	
	public Campaign addCampaign(Campaign campaign);
	
	public Campaign updateCampaign(Campaign campaign);
	
	public void deleteCampaign(Campaign campaign);
	
	public List<Campaign> getAllCampaignsByUser(User author);
	
	public List<Campaign> getAllPublicCampaigns();
	
	/*
	 * Chapter Operations
	 */
	public Chapter getChapter(int id);
	
	public Chapter addChapter(Chapter chapter);
	
	public Chapter updateChapter(Chapter chapter);
	
	public void deleteChapter(Chapter chapter);
	
	public List<Chapter> getAllChaptersByCampaign(Campaign campaign);
	
	/*
	 * Map Operations
	 */
	public Map getMap(int id);
	
	public Map addMap(Map map);
	
	public Map updateMap(Map map);
	
	public void deleteMap(Map map);
	
	public List<Map> getAllMapsByUser(User user);
	
	/*
	 * Location Operations
	 */
	public Location getLocation(int id);
	
	public Location addLocation(Location location);
	
	public Location updateLocation(Location location);
	
	public void deleteLocation(Location location);
	
	public List<Location> getAllLocationsByChapter(Chapter chapter);
	
	/*
	 * Event Operations
	 */
	public Event getEvent(int id);
	
	public Event addEvent(Event event);
	
	public Event updateEvent(Event event);
	
	public void deleteEvent(Event event);
	
	public List<Event> getAllEventsByLocation(Location location);
	
	/*
	 * Event Type Operations
	 */
	
	public EventType getEventType(int id);
	
	public EventType addEventType(EventType eventType);
	
	public List<EventType> getAllEventTypes();
	
	/*
	 * Character Operations
	 */
	public Character getCharacter(int id);
	
	public Character addCharacter(Character character);
	
	public Character updateCharacter(Character character);
	
	public void deleteCharacter(Character character);
	
	public List<Character> getAllCharactersByUser(User author);
	
	/*
	 * Enemy Operations
	 */
	public Enemy getEnemy(int id);
	
	public Enemy addEnemy(Enemy enemy);
	
	public Enemy updateEnemy(Enemy enemy);
	
	public void deleteEnemy(Enemy enemy);
	
	public List<Enemy> getAllEnemiesByEvent(Event event);
	
	public List<Enemy> getAllEnemiesByUser(User author);
	
	/*
	 * NonPlayableCharacter Operations
	 */
	public NonPlayableCharacter getNonPlayableCharacter(int id);
	
	public NonPlayableCharacter addNonPlayableCharacter(NonPlayableCharacter npc);
	
	public NonPlayableCharacter updateNonPlayableCharacter(NonPlayableCharacter npc);
	
	public void deleteNonPlayableCharacter(NonPlayableCharacter npc);
	
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByEvent(Event event);
	
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByUser(User author);
	
	/*
	 * Rating Operations
	 */
	public Rating getRating(int id);
	
	public Rating addRating(Rating rating);
	
	public Rating updateRating(Rating rating);
	
	public List<Rating> getAllRatingsByCampaign(Campaign campaign);
	
	public List<Rating> getAllRatingsByUser(User user);
	
}
