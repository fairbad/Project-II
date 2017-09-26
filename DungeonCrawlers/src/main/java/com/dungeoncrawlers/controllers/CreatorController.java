package com.dungeoncrawlers.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dungeoncrawlers.beans.Character;
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
        User currentUser = (User) session.getAttribute("user");
        characterDTO.setUser(currentUser);
		characterDTO.setId(serviceimpl.addCharacter(characterDTO).getId());
		return new ResponseEntity<CharacterDTO>(characterDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> createNPC(HttpSession session, @RequestBody NPCDTO npcDTO){
		System.out.println("creating new NPC");
        User currentUser = (User) session.getAttribute("user");
        npcDTO.setUser(currentUser);
        npcDTO.setId(serviceimpl.addNonPlayableCharacter(npcDTO).getId());
		return new ResponseEntity<NPCDTO>(npcDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/createEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> createEnemy(HttpSession session, @RequestBody EnemyDTO enemyDTO){
		System.out.println("creating new Enemy");
        User currentUser = (User) session.getAttribute("user");
        enemyDTO.setUser(currentUser);
        enemyDTO.setId(serviceimpl.addEnemy(enemyDTO).getId());
		return new ResponseEntity<EnemyDTO>(enemyDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/getCharacters", method= {RequestMethod.GET},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CharacterDTO>> getCharacters(HttpSession session){
		System.out.println("getting characters");
        User currentUser = (User) session.getAttribute("user");
        List<Character> characters = serviceimpl.getAllCharactersByUser(currentUser);
        List<CharacterDTO> charactersDTO = new ArrayList<>();
        for(Character c: characters) {
        	CharacterDTO temp = new CharacterDTO(c.getId(),c.getName(),c.getImage(),currentUser);
        	charactersDTO.add(temp);
        }
		return new ResponseEntity<List<CharacterDTO>>(charactersDTO, HttpStatus.OK);
	}
/*
	@RequestMapping(value="/getNPCs", method= {RequestMethod.GET},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<> getNPCs(HttpSession session){
		System.out.println("getting NPCs");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/getEnemies", method= {RequestMethod.GET},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<> getEnemies(HttpSession session){
		System.out.println("getting Enemys");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
*/	
	@RequestMapping(value="/editCharacter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CharacterDTO> editCharacter(HttpSession session, @RequestBody CharacterDTO characterDTO){
		System.out.println("editing character");
		
		Character character = serviceimpl.updateCharacter(characterDTO);
		
		/*
        User currentUser = (User) session.getAttribute("user");
        characterDTO.setUser(currentUser);
        characterDTO.setId(serviceimpl.updateCharacter(characterDTO).getId());*/
		return new ResponseEntity<CharacterDTO>(characterDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/editNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> editNPC(HttpSession session, @RequestBody NPCDTO npcDTO){
		System.out.println("editing NPC");
        User currentUser = (User) session.getAttribute("user");
        npcDTO.setUser(currentUser);
        npcDTO.setId(serviceimpl.updateNonPlayableCharacter(npcDTO).getId());
		return new ResponseEntity<NPCDTO>(npcDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/editEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> editEnemy(HttpSession session, @RequestBody EnemyDTO enemyDTO){
		System.out.println("editing Enemy");
        User currentUser = (User) session.getAttribute("user");
        enemyDTO.setUser(currentUser);
        enemyDTO.setId(serviceimpl.updateEnemy(enemyDTO).getId());
		return new ResponseEntity<EnemyDTO>(enemyDTO, HttpStatus.OK);
	}
}
