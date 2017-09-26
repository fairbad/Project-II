package com.dungeoncrawlers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.CharacterDTO;
import com.dungeoncrawlers.dto.EnemyDTO;
import com.dungeoncrawlers.dto.NPCDTO;
import com.dungeoncrawlers.service.ServiceInterface;

@RestController
@RequestMapping(value="/creator")
public class CreatorController {

	@Autowired
	private ServiceInterface serviceimpl;

	public void setServiceImpl(ServiceInterface serviceImpl) {
		this.serviceimpl = serviceImpl;
	}
	
	@RequestMapping(value="/createCharacter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CharacterDTO> createCharacter(HttpSession session, @RequestBody CharacterDTO characterDTO){
		System.out.println("creating new character");
        User currentUser = (User) session.getAttribute("currentUser");
        characterDTO.setUser(currentUser);
		characterDTO.setId(serviceimpl.addCharacter(characterDTO).getId());
		return new ResponseEntity<CharacterDTO>(characterDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> createNPC(@RequestBody NPCDTO npcDTO){
		System.out.println("creating new NPC");
		serviceimpl.addNonPlayableCharacter(npcDTO);
		return new ResponseEntity<NPCDTO>(npcDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/createEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> createEnemy(@RequestBody EnemyDTO enemyDTO){
		System.out.println("creating new Enemy");
		serviceimpl.addEnemy(enemyDTO);
		return new ResponseEntity<EnemyDTO>(enemyDTO, HttpStatus.OK);
	}
}
