package com.dungeoncrawlers.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	}
}
