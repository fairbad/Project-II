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
	private DAO daoimpl;
	
	public void setDao(DAO daoimpl) {
		this.daoimpl = daoimpl;
	}

	@Override
	public User getUser(int id) {
		return daoimpl.getUser(id);
		
	}
	
	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		return daoimpl.addUser(user);
	}
	@Override
	public UserDTO authenticateUser(UserDTO userDto) {
		User user = daoimpl.findUserByEmail(userDto.getUsername());
		System.out.println("Inside the serviceImpl User AuthenticateUser Method");
		if(user != null && 
				(user.getPassword().equals(userDto.getPassword()))) {
			System.out.println("setting userdto to true");
			userDto.setAuthenticated(true);
		}else {
			System.out.println("Inside the Else everything will return null!");
			return null;
		}
		System.out.println("returning the User DTO" + userDto.toString());
		return userDto;
	}
	@Override
	public User updateUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		return daoimpl.updateUser(user);
	}
	
	@Override
	public Campaign getCampaign(int id) {
		return daoimpl.getCampaign(id);
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
		
		return daoimpl.addCampaign(campaign);
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
		
		return daoimpl.updateCampaign(campaign);
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
		
		daoimpl.deleteCampaign(campaign);
	}

	@Override
	public List<Campaign> getAllCampaignsByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllCampaignsByUser(author);
	}

	@Override
	public List<Campaign> getAllPublicCampaigns() {
		return daoimpl.getAllPublicCampaigns();
	}

	@Override
	public Chapter getChapter(int id) {
		return daoimpl.getChapter(id);
	}

	@Override
	public Chapter addChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return daoimpl.addChapter(chapter);
	}

	@Override
	public Chapter updateChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return daoimpl.updateChapter(chapter);
	}

	@Override
	public void deleteChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		daoimpl.deleteChapter(chapter);
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
		
		return daoimpl.getAllChaptersByCampaign(campaign);
	}

	@Override
	public Map getMap(int id) {
		return daoimpl.getMap(id);
	}

	@Override
	public Map addMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		return daoimpl.addMap(map);
	}

	@Override
	public Map updateMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		return daoimpl.updateMap(map);
	}

	@Override
	public void deleteMap(MapDTO mapDTO) {
		Map map = new Map();
		map.setDescription(mapDTO.getDescription());
		map.setImage(mapDTO.getImage());
		map.setName(mapDTO.getName());
		
		daoimpl.deleteMap(map);
	}
	
	@Override
	public List<Map> getAllMapsByUser(UserDTO userDTO){
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllMapsByUser(author);
	}

	@Override
	public Location getLocation(int id) {
		return daoimpl.getLocation(id);
	}

	@Override
	public Location addLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return daoimpl.addLocation(location);
	}

	@Override
	public Location updateLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return daoimpl.updateLocation(location);
	}

	@Override
	public void deleteLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		daoimpl.deleteLocation(location);
	}

	@Override
	public List<Location> getAllLocationsByChapter(ChapterDTO chapterDTO) {
		Chapter chapter = new Chapter();
		chapter.setCampaign(chapterDTO.getCampaign());
		chapter.setDescription(chapterDTO.getDescription());
		chapter.setImage(chapterDTO.getImage());
		chapter.setName(chapterDTO.getName());
		chapter.setOrder(chapterDTO.getOrder());
		
		return daoimpl.getAllLocationsByChapter(chapter);
	}

	@Override
	public Event getEvent(int id) {
		return daoimpl.getEvent(id);
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
		
		return daoimpl.addEvent(event);
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
		
		return daoimpl.updateEvent(event);
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
		
		daoimpl.deleteEvent(event);
	}

	@Override
	public List<Event> getAllEventsByLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setChapter(locationDTO.getChapter());
		location.setDescription(locationDTO.getDescription());
		location.setImage(locationDTO.getImage());
		location.setName(locationDTO.getName());
		
		return daoimpl.getAllEventsByLocation(location);
	}

	@Override
	public EventType getEventType(int id) {
		return daoimpl.getEventType(id);
	}

	@Override
	public EventType addEventType(EventTypeDTO eventTypeDTO) {
		EventType eventType = new EventType();
		eventType.setEvent(eventTypeDTO.getEvent());
		
		return daoimpl.addEventType(eventType);
	}

	@Override
	public List<EventType> getAllEventTypes() {
		return daoimpl.getAllEventTypes();
	}

	@Override
	public Character getCharacter(int id) {
		return daoimpl.getCharacter(id);
	}

	@Override
	public Character addCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		return daoimpl.addCharacter(character);
	}

	@Override
	public Character updateCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		return daoimpl.updateCharacter(character);
	}

	@Override
	public void deleteCharacter(CharacterDTO characterDTO) {
		Character character = new Character();
		character.setAuthor(characterDTO.getAuthor());
		character.setImage(characterDTO.getImage());
		character.setName(characterDTO.getName());
		
		daoimpl.deleteCharacter(character);
	}

	@Override
	public List<Character> getAllCharactersByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllCharactersByUser(author);
	}

	@Override
	public Enemy getEnemy(int id) {
		return daoimpl.getEnemy(id);
	}

	@Override
	public Enemy addEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		return daoimpl.addEnemy(enemy);
	}

	@Override
	public Enemy updateEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		return daoimpl.updateEnemy(enemy);
	}

	@Override
	public void deleteEnemy(EnemyDTO enemyDTO) {
		Enemy enemy = new Enemy();
		enemy.setAuthor(enemyDTO.getAuthor());
		enemy.setImage(enemyDTO.getImage());
		enemy.setName(enemyDTO.getName());
		
		daoimpl.deleteEnemy(enemy);
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
		
		return daoimpl.getAllEnemiesByEvent(event);
	}

	@Override
	public List<Enemy> getAllEnemiesByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllEnemiesByUser(author);
	}

	@Override
	public NonPlayableCharacter getNonPlayableCharacter(int id) {
		return daoimpl.getNonPlayableCharacter(id);
	}

	@Override
	public NonPlayableCharacter addNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		return daoimpl.addNonPlayableCharacter(npc);
	}

	@Override
	public NonPlayableCharacter updateNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		return daoimpl.updateNonPlayableCharacter(npc);
	}

	@Override
	public void deleteNonPlayableCharacter(NPCDTO npcDTO) {
		NonPlayableCharacter npc = new NonPlayableCharacter();
		npc.setAuthor(npcDTO.getAuthor());
		npc.setImage(npcDTO.getImage());
		npc.setName(npcDTO.getName());
		
		daoimpl.deleteNonPlayableCharacter(npc);
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
		
		return daoimpl.getAllNonPlayableCharactersByEvent(event);
	}

	@Override
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByUser(UserDTO userDTO) {
		User author = new User();
		author.setEmail(userDTO.getEmail());
		author.setPassword(userDTO.getPassword());
		author.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllNonPlayableCharactersByUser(author);
	}

	@Override
	public Rating getRating(int id) {
		return daoimpl.getRating(id);
	}

	@Override
	public Rating addRating(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		rating.setCampaign(ratingDTO.getCampaign());
		rating.setRating(ratingDTO.getRating());
		rating.setUser(ratingDTO.getUser());
		
		return daoimpl.addRating(rating);
	}

	@Override
	public Rating updateRating(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		rating.setCampaign(ratingDTO.getCampaign());
		rating.setRating(ratingDTO.getRating());
		rating.setUser(ratingDTO.getUser());
		
		return daoimpl.updateRating(rating);
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
		
		return daoimpl.getAllRatingsByCampaign(campaign);
	}
	
	@Override
	public List<Rating> getAllRatingsByUser(UserDTO userDTO){
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		
		return daoimpl.getAllRatingsByUser(user);
	}
	
	@Override
	public void updateAllCampaignRatings() {
		List<Campaign> campaigns = daoimpl.getAllCampaigns();
		for(Campaign c: campaigns) {
			List<Rating> ratings = daoimpl.getAllRatingsByCampaign(c);
			float total = 0;
			int count = 0;
			for(Rating r: ratings) {
				total += r.getRating();
				count ++;
			}
			c.setRating(total/count);
			daoimpl.updateCampaign(c);
		}
	}
}
