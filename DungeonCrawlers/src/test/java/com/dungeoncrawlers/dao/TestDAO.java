package com.dungeoncrawlers.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dungeoncrawlers.beans.Character;
import com.dungeoncrawlers.beans.User;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDAO {

	@Autowired
	private DAO dao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUser() {
		User user = new User("andy","andy@email.com","password");
		dao.addUser(user);
		
		User userGetLogin1 = dao.getUser("andy@email.com", "password");
		
		user.setUsername("zheng");
		user.setEmail("zheng@email.com");
		user.setPassword("p4ssw0rd");
		dao.updateUser(user);
		
		User userGetLogin2 = dao.getUser("zheng@email.com", "p4ssw0rd");
		assertEquals(true,user.equals(userGetLogin2));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCharacter() {
		User user = new User("andy","andy@email.com","password");
		dao.addUser(user);
		Character character = new Character("Creus","https://someimage.com/1",user);
		dao.addCharacter(character);

		Character character2 = new Character("Andy","https://someimage.com/2",user);
		dao.addCharacter(character2);
		
		List<Character> characters = dao.getAllCharactersByUser(user);
		assertEquals(2,characters.size());
		assertEquals(true,character.equals(characters.get(0)));
		assertEquals(true,character2.equals(characters.get(1)));
	}
	
}
