package com.dungeoncrawlers.dao;

import java.util.ArrayList;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.Event;
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
	
	/*
	 * Campaign Operations
	 */
	public Campaign getCampaign(int id);
	
	public Campaign addCampaign(Campaign campaign);
	
	public Campaign updateCampaign(Campaign campaign);
	
	public void deleteCampaign(int id);
	
	public ArrayList<Campaign> getAllCampaignsByUserId(int id);
	
	public ArrayList<Campaign> getAllPublicCampaigns(boolean isPublic);
	
	/*
	 * Chapter Operations
	 */
	public Chapter getChapter(int id);
	
	public Chapter addChapter(Chapter chapter);
	
	public Chapter updateChapter(Chapter chapter);
	
	public void deleteChapter(int id);
	
	public ArrayList<Chapter> getAllChaptersByCampaignId(int id);
	
	/*
	 * Map Operations
	 */
	public Map getMap(int id);
	
	public Map addMap(Map map);
	
	public Map updateMap(Map map);
	
	public void deleteMap(int id);
	
	/*
	 * Location Operations
	 */
	public Location getLocation(int id);
	
	public Location addLocation(Location location);
	
	public Location updateLocation(Location location);
	
	public void deleteLocation(int id);
	
	public ArrayList<Location> getAllLocationsByChapterId(int id);
	
	/*
	 * Event Operations
	 */
	public Event getEvent(int id);
	
	public Event addEvent(Event event);
	
	public Event updateEvent(Event event);
	
	public void deleteEvent(int id);
	
	public ArrayList<Event> getAllEventsByLocationId(int id);
	
	public Event getEventType(int id);
	
	public Event addEventType(Event event);
	
	/*
	 * Character Operations
	 */
	public Character getCharacter(int id);
	
	public Character addCharacter(Character character);
	
	public Character updateCharacter(Character character);
	
	public void deleteCharacter(int id);
	
	public ArrayList<Character> getAllCharactersByUserId(int id);
	
	/*
	 * Enemy Operations
	 */
	public Enemy getEnemy(int id);
	
	public Enemy addEnemy(Enemy enemy);
	
	public Enemy updateEnemy(Enemy enemy);
	
	public void deleteEnemy(int id);
	
	public ArrayList<Enemy> getAllEnemiesByEventId(int id);
	
	/*
	 * NonPlayableCharacter Operations
	 */
	public NonPlayableCharacter getNonPlayableCharacter(int id);
	
	public NonPlayableCharacter addNonPlayableCharacter(NonPlayableCharacter npc);
	
	public NonPlayableCharacter updateNonPlayableCharacter(NonPlayableCharacter npc);
	
	public void deleteNonPlayableCharacter(int id);
	
	public ArrayList<NonPlayableCharacter> getAllNonPlayableCharactersByEventId(int id);
	
	/*
	 * Rating Operations
	 */
	public Rating getRating(int id);
	
	public Rating addRating(Rating rating);
	
	public Rating updateRating(Rating rating);
	
	public ArrayList<Rating> getAllRatingsByCampaignId(int id);
	
}
