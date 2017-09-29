package com.dungeoncrawlers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

@Repository
@Transactional
public class DAOimpl implements DAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(int id) {
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}
	@Override
	public List<String> getAllUsersEmail(){
		List<String> emails = sessionFactory.getCurrentSession().createQuery("select email from User").list();
		return emails;
	}
	@Override
	public List<String> getAllUserNames(){
		List<String> username = sessionFactory.getCurrentSession().createQuery("select username from User").list();
		return username;
	}

	@Override
	public User getUser(String email, String password) {
		return (User)sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password)).uniqueResult();
	}

	@Override
	public User addUser(User user) {
		System.out.println("User in add User: " + user.toString());
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		System.out.println("This is the user inside the User updateUser in the DAOimpl: " + user.toString());
		sessionFactory.getCurrentSession().update(user);
		return user;
	}

	@Override
	public Campaign getCampaign(int id) {
		return (Campaign) sessionFactory.getCurrentSession().get(Campaign.class, id);
	}
	
	@Override
	public List<Campaign> getAllCampaigns(){
		return (List<Campaign>) sessionFactory.getCurrentSession().createCriteria(Campaign.class).list();
	}

	@Override
	public Campaign addCampaign(Campaign campaign) {
		sessionFactory.getCurrentSession().save(campaign);
		return campaign;
	}

	@Override
	public Campaign updateCampaign(Campaign campaign) {
		sessionFactory.getCurrentSession().update(campaign);
		return campaign;
	}

	@Override
	public void deleteCampaign(Campaign campaign) {
		sessionFactory.getCurrentSession().delete(campaign);
	}

	@Override
	public List<Campaign> getAllCampaignsByUser(User user) {
		return (List<Campaign>) sessionFactory.getCurrentSession().createCriteria(Campaign.class).add(Restrictions.eq("user", user)).list();
	}

	@Override
	public List<Campaign> getAllPublicCampaigns() {
		return (List<Campaign>) sessionFactory.getCurrentSession().createCriteria(Campaign.class).add(Restrictions.eq("isPublic", true)).list();
	}

	@Override
	public Chapter getChapter(int id) {
		return (Chapter) sessionFactory.getCurrentSession().get(Chapter.class, id);
	}

	@Override
	public Chapter addChapter(Chapter chapter) {
		sessionFactory.getCurrentSession().save(chapter);
		return chapter;
	}

	@Override
	public Chapter updateChapter(Chapter chapter) {
		sessionFactory.getCurrentSession().update(chapter);
		return chapter;
	}

	@Override
	public void deleteChapter(Chapter chapter) {
		sessionFactory.getCurrentSession().delete(chapter);
	}

	@Override
	public List<Chapter> getAllChaptersByCampaign(Campaign campaign) {
		return (List<Chapter>) sessionFactory.getCurrentSession().createCriteria(Chapter.class).add(Restrictions.eq("campaign", campaign)).list();
	}

	@Override
	public Map getMap(int id) {
		return (Map) sessionFactory.getCurrentSession().get(Map.class, id);
	}

	@Override
	public Map addMap(Map map) {
		sessionFactory.getCurrentSession().save(map);
		return map;
	}

	@Override
	public Map updateMap(Map map) {
		sessionFactory.getCurrentSession().update(map);
		return map;
	}

	@Override
	public void deleteMap(Map map) {
		sessionFactory.getCurrentSession().delete(map);
	}
	
	@Override
	public List<Map> getAllMapsByUser(User user){
		return (List<Map>) sessionFactory.getCurrentSession().createCriteria(Map.class).add(Restrictions.eq("user", user)).list();
	}

	@Override
	public Location getLocation(int id) {
		return (Location) sessionFactory.getCurrentSession().get(Location.class, id);
	}

	@Override
	public Location addLocation(Location location) {
		sessionFactory.getCurrentSession().save(location);
		return location;
	}

	@Override
	public Location updateLocation(Location location) {
		sessionFactory.getCurrentSession().update(location);
		return location;
	}

	@Override
	public void deleteLocation(Location location) {
		sessionFactory.getCurrentSession().delete(location);
	}

	@Override
	public List<Location> getAllLocationsByChapter(Chapter chapter) {
		return (List<Location>) sessionFactory.getCurrentSession().createCriteria(Location.class).add(Restrictions.eq("chapter", chapter)).list();
	}

	@Override
	public Event getEvent(int id) {
		return (Event) sessionFactory.getCurrentSession().get(Event.class, id);
	}

	@Override
	public Event addEvent(Event event) {
		sessionFactory.getCurrentSession().save(event);
		return event;
	}

	@Override
	public Event updateEvent(Event event) {
		sessionFactory.getCurrentSession().update(event);
		return event;
	}

	@Override
	public void deleteEvent(Event event) {
		sessionFactory.getCurrentSession().delete(event);
	}

	@Override
	public List<Event> getAllEventsByLocation(Location location) {
		return (List<Event>) sessionFactory.getCurrentSession().createCriteria(Event.class).add(Restrictions.eq("location", location)).list();
	}

	@Override
	public EventType getEventType(int id) {
		return (EventType) sessionFactory.getCurrentSession().get(Event.class, id);
	}

	@Override
	public EventType addEventType(EventType eventType) {
		sessionFactory.getCurrentSession().save(eventType);
		return eventType;
	}

	@Override
	public List<EventType> getAllEventTypes(){
		return (List<EventType>) sessionFactory.getCurrentSession().createCriteria(EventType.class).list();
	}
	
	@Override
	public Character getCharacter(int id) {
		return (Character) sessionFactory.getCurrentSession().get(Character.class, id);
	}

	@Override
	public Character addCharacter(Character character) {
		sessionFactory.getCurrentSession().save(character);
		return character;
	}

	@Override
	public Character updateCharacter(Character character) {
		sessionFactory.getCurrentSession().update(character);
		return character;
	}

	@Override
	public void deleteCharacter(Character character) {
		sessionFactory.getCurrentSession().delete(character);
	}

	@Override
	public List<Character> getAllCharactersByUser(User user) {
		return (List<Character>) sessionFactory.getCurrentSession().createCriteria(Character.class).add(Restrictions.eq("user", user)).list();
	}

	@Override
	public Enemy getEnemy(int id) {
		return (Enemy) sessionFactory.getCurrentSession().get(Enemy.class, id);
	}

	@Override
	public Enemy addEnemy(Enemy enemy) {
		sessionFactory.getCurrentSession().save(enemy);
		return enemy;
	}

	@Override
	public Enemy updateEnemy(Enemy enemy) {
		sessionFactory.getCurrentSession().update(enemy);
		return enemy;
	}

	@Override
	public void deleteEnemy(Enemy enemy) {
		sessionFactory.getCurrentSession().delete(enemy);
	}

	@Override
	public List<Enemy> getAllEnemiesByEvent(Event event) {
		return (List<Enemy>) sessionFactory.getCurrentSession().createCriteria(Enemy.class).add(Restrictions.eq("event", event)).list();
	}

	@Override
	public List<Enemy> getAllEnemiesByUser(User user) {
		return (List<Enemy>) sessionFactory.getCurrentSession().createCriteria(Enemy.class).add(Restrictions.eq("user", user)).list();
	}

	@Override
	public NonPlayableCharacter getNonPlayableCharacter(int id) {
		return (NonPlayableCharacter) sessionFactory.getCurrentSession().get(NonPlayableCharacter.class, id);
	}

	@Override
	public NonPlayableCharacter addNonPlayableCharacter(NonPlayableCharacter npc) {
		sessionFactory.getCurrentSession().save(npc);
		return npc;
	}

	@Override
	public NonPlayableCharacter updateNonPlayableCharacter(NonPlayableCharacter npc) {
		sessionFactory.getCurrentSession().update(npc);
		return npc;
	}

	@Override
	public void deleteNonPlayableCharacter(NonPlayableCharacter npc) {
		sessionFactory.getCurrentSession().delete(npc);
	}

	@Override
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByEvent(Event event) {
		return (List<NonPlayableCharacter>) sessionFactory.getCurrentSession().createCriteria(NonPlayableCharacter.class).add(Restrictions.eq("event", event)).list();
	}

	@Override
	public List<NonPlayableCharacter> getAllNonPlayableCharactersByUser(User user) {
		return (List<NonPlayableCharacter>) sessionFactory.getCurrentSession().createCriteria(NonPlayableCharacter.class).add(Restrictions.eq("user", user)).list();
	}
	
	@Override
	public Rating getRating(int id) {
		return (Rating) sessionFactory.getCurrentSession().get(Rating.class, id);
	}

	@Override
	public Rating addRating(Rating rating) {
		sessionFactory.getCurrentSession().save(rating);
		return rating;
	}

	@Override
	public Rating updateRating(Rating rating) {
		sessionFactory.getCurrentSession().update(rating);
		return rating;
	}

	@Override
	public List<Rating> getAllRatingsByCampaign(Campaign campaign) {
		return (List<Rating>) sessionFactory.getCurrentSession().createCriteria(Rating.class).add(Restrictions.eq("campaign", campaign)).list();
	}
	
	@Override
	public List<Rating> getAllRatingsByUser(User user){
		return (List<Rating>) sessionFactory.getCurrentSession().createCriteria(Rating.class).add(Restrictions.eq("user", user)).list();
	}
}
