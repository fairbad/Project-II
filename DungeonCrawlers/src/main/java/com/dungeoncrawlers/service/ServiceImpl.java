package com.dungeoncrawlers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungeoncrawlers.dao.DAO;

@Service
public class ServiceImpl implements ServiceInterface{
	
	@Autowired
	private DAO dao;
	
	public void setDao(DAO dao) {
		this.dao = dao;
	}
}
