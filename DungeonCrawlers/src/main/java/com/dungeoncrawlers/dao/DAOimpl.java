package com.dungeoncrawlers.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.dungeoncrawlers.beans.Campaign;
import com.dungeoncrawlers.beans.Chapter;
import com.dungeoncrawlers.beans.Character;
import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.Event;
import com.dungeoncrawlers.beans.Location;
import com.dungeoncrawlers.beans.Map;
import com.dungeoncrawlers.beans.NonPlayableCharacter;
import com.dungeoncrawlers.beans.Rating;
import com.dungeoncrawlers.beans.User;

@Transactional
public class DAOimpl implements DAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(int id) {
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User getUser(String email, String password) {
		return (User)sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password)).uniqueResult();
	}

	@Override
	public User addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}

	@Override
	public Campaign getCampaign(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Campaign addCampaign(Campaign campaign) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Campaign updateCampaign(Campaign campaign) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteCampaign(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Campaign> getAllCampaignsByUserId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public ArrayList<Campaign> getAllPublicCampaigns(boolean isPublic) {
		return (ArrayList<Campaign>) sessionFactory.getCurrentSession().createCriteria(Campaign.class).add(Restrictions.eq("isPublic", isPublic)).list();
	}

	@Override
	public Chapter getChapter(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Chapter addChapter(Chapter chapter) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Chapter updateChapter(Chapter chapter) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteChapter(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Chapter> getAllChaptersByCampaignId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Map getMap(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Map addMap(Map map) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Map updateMap(Map map) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteMap(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public Location getLocation(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Location addLocation(Location location) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Location updateLocation(Location location) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteLocation(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Location> getAllLocationsByChapterId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Event getEvent(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Event addEvent(Event event) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Event updateEvent(Event event) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteEvent(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Event> getAllEventsByLocationId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Event getEventType(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Event addEventType(Event event) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Character getCharacter(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Character addCharacter(Character character) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Character updateCharacter(Character character) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteCharacter(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Character> getAllCharactersByUserId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Enemy getEnemy(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Enemy addEnemy(Enemy enemy) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Enemy updateEnemy(Enemy enemy) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteEnemy(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<Enemy> getAllEnemiesByEventId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public NonPlayableCharacter getNonPlayableCharacter(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public NonPlayableCharacter addNonPlayableCharacter(NonPlayableCharacter npc) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public NonPlayableCharacter updateNonPlayableCharacter(NonPlayableCharacter npc) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteNonPlayableCharacter(int id) {
		sessionFactory.getCurrentSession();
		
	}

	@Override
	public ArrayList<NonPlayableCharacter> getAllNonPlayableCharactersByEventId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Rating getRating(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Rating addRating(Rating rating) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Rating updateRating(Rating rating) {
		sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public ArrayList<Rating> getAllRatingsByCampaignId(int id) {
		sessionFactory.getCurrentSession();
		return null;
	}

}
