package com.dungeoncrawlers.main;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dungeoncrawlers.beans.Character;
import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dao.DAO;

public class Main {

	public static void main(String[] args) {
		//AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		//DAO dao = (DAO)context.getBean("myDao");
		/*
		User u = new User();
		u.setUsername("testman");
		u.setEmail("testing123@email.com");
		u.setPassword("12345");

		System.out.println(dao.addUser(u).getUsername());
		*/
		/*
		User u = new User();
		u.setUsername("test");
		u.setEmail("testing@email.com");
		u.setPassword("12345");
		u.setId(50);
		dao.updateUser(u);
		System.out.println(dao.getUser(50).getUsername());*/
		
/*
		User u = dao.getUser(50);
		
		Character c = new Character();
		c.setAuthor(u);
		c.setName("Character1");
		Character c1 = new Character();
		c1.setAuthor(u);
		c1.setName("Character2");
		Character c2 = new Character();
		c2.setAuthor(u);
		c2.setName("Character3");
*/
		//dao.addCharacter(c);
		//dao.addCharacter(c1);
		//dao.addCharacter(c2);

		//System.out.println(dao.getCharacter(50).getName());
		//System.out.println(dao.getCharacter(100).getName());
		//System.out.println(dao.getCharacter(150).getName());
		/*
		List<Character> charList= dao.getAllCharactersByUserId(50);
		for(Character cha: charList) {
			System.out.println(cha.getName());
		}
		*/
	}
}
