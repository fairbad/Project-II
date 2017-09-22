package com.dungeoncrawlers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.dungeoncrawlers.dao.DAO;
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

@Service
public class ServiceImpl implements ServiceInterface{
	
	@Autowired
	private DAO dao;
	
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public Campaign getCampaign(int id) {
		return dao.getCampaign(id);
	}

	@Override
	public Campaign addCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setAuthor(campaignDTO.getAuthor());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews());
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		
		return dao.addCampaign(campaign);
	}

	@Override
	public Campaign updateCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setAuthor(campaignDTO.getAuthor());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews());
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		
		return dao.updateCampaign(campaign);
	}

	@Override
	public void deleteCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setAuthor(campaignDTO.getAuthor());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews());
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		
		dao.deleteCampaign(campaign);
	}

	@Override
	public List<Campaign> getAllCampaignsByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return dao.getAllCampaignsByUser(author);
	}

	@Override
	public List<Campaign> getAllPublicCampaigns() {
		return dao.getAllPublicCampaigns();
	}

	// to finish
	@Override
	public List<Campaign> sortByMostViewed() {
		List<Campaign> campaigns = dao.getAllCampaigns();
		for(Campaign c: campaigns) {
			c.getNumViews();
		}
		
		return null;
	}

	@Override
	public Chapter getChapter(int id) {
		return dao.getChapter(id);
	}

	@Override
	public Chapter addChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return dao.addChapter(chapter);
	}

	@Override
	public Chapter updateChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return dao.updateChapter(chapter);
	}

	@Override
	public void deleteChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		dao.deleteChapter(chapter);
	}

	@Override
	public List<Chapter> getAllChaptersByCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setAuthor(campaignDTO.getAuthor());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews());
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		
		return dao.getAllChaptersByCampaign(campaign);
	}

	@Override
	public Map getMap(int id) {
		return dao.getMap(id);
	}

	@Override
	public Map addMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		return dao.addMap(map);
	}

	@Override
	public Map updateMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		return dao.updateMap(map);
	}

	@Override
	public void deleteMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		dao.deleteMap(map);
	}

	@Override
	public Location getLocation(int id) {
		return dao.getLocation(id);
	}

	@Override
	public Location addLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return dao.addLocation(location);
	}

	@Override
	public Location updateLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return dao.updateLocation(location);
	}

	@Override
	public void deleteLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		dao.deleteLocation(location);
	}

	@Override
	public List<Location> getAllLocationsByChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return dao.getAllLocationsByChapter(chapter);
	}

	@Override
	public Event getEvent(int id) {
		return dao.getEvent(id);
	}

	@Override
	public Event addEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setDescription(eventDTO.getDescription());
		event.setEnemies(eventDTO.getEnemies());
		event.setEvent(eventDTO.getEvent());
		event.setImage(eventDTO.getImage());
		event.setLocation(eventDTO.getLocation());
		event.setName(eventDTO.getName());
		event.setNonPlayableCharacters(eventDTO.getNonPlayableCharacters());
		
		return dao.addEvent(event);
	}

	@Override
	public Event updateEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setDescription(eventDTO.getDescription());
		event.setEnemies(eventDTO.getEnemies());
		event.setEvent(eventDTO.getEvent());
		event.setImage(eventDTO.getImage());
		event.setLocation(eventDTO.getLocation());
		event.setName(eventDTO.getName());
		event.setNonPlayableCharacters(eventDTO.getNonPlayableCharacters());
		
		return dao.updateEvent(event);
	}

	@Override
	public void deleteEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setDescription(eventDTO.getDescription());
		event.setEnemies(eventDTO.getEnemies());
		event.setEvent(eventDTO.getEvent());
		event.setImage(eventDTO.getImage());
		event.setLocation(eventDTO.getLocation());
		event.setName(eventDTO.getName());
		event.setNonPlayableCharacters(eventDTO.getNonPlayableCharacters());
		
		dao.deleteEvent(event);
	}

	@Override
	public List<Event> getAllEventsByLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return dao.getAllEventsByLocation(location);
	}

	@Override
	public EventType getEventType(int id) {
		return dao.getEventType(id);
	}

	@Override
	public EventType addEventType(EventTypeDTO eventTypeDTO) {
		EventType eventType = new EventType();
		eventType.setEvent(eventTypeDTO.getEvent());
		
		return dao.addEventType(eventType);
	}

	@Override
	public List<EventType> getAllEventTypes() {
		return dao.getAllEventTypes();
	}

	@Override
	public Character getCharacter(int id) {
		return dao.getCharacter(id);
	}

	@Override
	public Character addCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		return dao.addCharacter(character);
	}

	@Override
	public Character updateCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		return dao.updateCharacter(character);
	}

	@Override
	public void deleteCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		dao.deleteCharacter(character);
	}

	@Override
	public List<Character> getAllCharactersByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return dao.getAllCharactersByUser(author);
	}

	@Override
	public Enemy getEnemy(int id) {
		return dao.getEnemy(id);
	}

	@Override
	public Enemy addEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		return dao.addEnemy(enemy);
	}

	@Override
	public Enemy updateEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		return dao.updateEnemy(enemy);
	}

	@Override
	public void deleteEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		dao.deleteEnemy(enemy);
	}

	@Override
	public List<Enemy> getAllEnemiesByEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setDescription(eventDTO.getDescription());
		event.setEnemies(eventDTO.getEnemies());
		event.setEvent(eventDTO.getEvent());
		event.setImage(eventDTO.getImage());
		event.setLocation(eventDTO.getLocation());
		event.setName(eventDTO.getName());
		event.setNonPlayableCharacters(eventDTO.getNonPlayableCharacters());
		
		return dao.getAllEnemiesByEvent(event);
	}

	@Override
	public List<Enemy> getAllEnemiesByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return dao.getAllEnemiesByUser(author);
	}

	@Override
	public NonPlayableCharacter getNonPlayableCharacter(int id) {
		return dao.getNonPlayableCharacter(id);
	}

	@Override
	public NonPlayableCharacter addNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		return dao.addNonPlayableCharacter(npc);
	}

	@Override
	public NonPlayableCharacter updateNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		return dao.updateNonPlayableCharacter(npc);
	}

	@Override
	public void deleteNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		dao.deleteNonPlayableCharacter(npc);
	}

	@Override
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setDescription(eventDTO.getDescription());
		event.setEnemies(eventDTO.getEnemies());
		event.setEvent(eventDTO.getEvent());
		event.setImage(eventDTO.getImage());
		event.setLocation(eventDTO.getLocation());
		event.setName(eventDTO.getName());
		event.setNonPlayableCharacters(eventDTO.getNonPlayableCharacters());
		
		return dao.getAllNonPlayableCharactersByEvent(event);
	}

	@Override
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return dao.getAllNonPlayableCharactersByUser(author);
	}

	@Override
	public Rating getRating(int id) {
		return dao.getRating(id);
	}

	@Override
	public Rating addRating(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		rating.setCampaign(ratingDTO.getCampaign());
		rating.setRating(ratingDTO.getRating());
		rating.setUser(ratingDTO.getUser());
		
		return dao.addRating(rating);
	}

	@Override
	public Rating updateRating(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		rating.setCampaign(ratingDTO.getCampaign());
		rating.setRating(ratingDTO.getRating());
		rating.setUser(ratingDTO.getUser());
		
		return dao.updateRating(rating);
	}

	@Override
	public List<Rating> getAllRatingsByCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setAuthor(campaignDTO.getAuthor());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setImage(campaignDTO.getImage());
		campaign.setMap(campaignDTO.getMap());
		campaign.setName(campaignDTO.getName());
		campaign.setNumViews(campaignDTO.getNumViews());
		campaign.setPublic(campaignDTO.isPublic());
		campaign.setRating(campaignDTO.getRating());
		
		return dao.getAllRatingsByCampaign(campaign);
	}
	
	@Override
	public void updateAllCampaignRatings() {
		List<Campaign> campaigns = dao.getAllCampaigns();
		for(Campaign c: campaigns) {
			List<Rating> ratings = dao.getAllRatingsByCampaign(c);
			float total = 0;
			int count = 0;
			for(Rating r: ratings) {
				total += r.getRating();
				count ++;
			}
			c.setRating(total/count);
			dao.updateCampaign(c);
		}
	}
}
