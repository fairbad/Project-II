package com.dungeoncrawlers.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dungeoncrawlers.beans.Character;
import com.dungeoncrawlers.beans.Enemy;
import com.dungeoncrawlers.beans.NonPlayableCharacter;
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
	
	//Used to decode encoded AWS Keys
	private String deCode(String str) {
		String tempstr = "";
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'A':
				tempstr += "B";
				break;
			case 'B':
				tempstr += "C";
				break;
			case 'C':
				tempstr += "D";
				break;
			case 'D':
				tempstr += "E";
				break;
			case 'E':
				tempstr += "F";
				break;
			case 'F':
				tempstr += "G";
				break;
			case 'G':
				tempstr += "H";
				break;
			case 'H':
				tempstr += "I";
				break;
			case 'I':
				tempstr += "J";
				break;
			case 'J':
				tempstr += "K";
				break;
			case 'K':
				tempstr += "L";
				break;
			case 'L':
				tempstr += "M";
				break;
			case 'M':
				tempstr += "N";
				break;
			case 'N':
				tempstr += "O";
				break;
			case 'O':
				tempstr += "P";
				break;
			case 'P':
				tempstr += "Q";
				break;
			case 'Q':
				tempstr += "R";
				break;
			case 'R':
				tempstr += "S";
				break;
			case 'S':
				tempstr += "T";
				break;
			case 'T':
				tempstr += "U";
				break;
			case 'U':
				tempstr += "V";
				break;
			case 'V':
				tempstr += "W";
				break;
			case 'W':
				tempstr += "X";
				break;
			case 'X':
				tempstr += "Y";
				break;
			case 'Y':
				tempstr += "Z";
				break;
			case 'Z':
				tempstr += "A";
				break;
			// Lower Case
			case 'a':
				tempstr += "b";
				break;
			case 'b':
				tempstr += "c";
				break;
			case 'c':
				tempstr += "d";
				break;
			case 'd':
				tempstr += "e";
				break;
			case 'e':
				tempstr += "f";
				break;
			case 'f':
				tempstr += "g";
				break;
			case 'g':
				tempstr += "h";
				break;
			case 'h':
				tempstr += "i";
				break;
			case 'i':
				tempstr += "j";
				break;
			case 'j':
				tempstr += "k";
				break;
			case 'k':
				tempstr += "l";
				break;
			case 'l':
				tempstr += "m";
				break;
			case 'm':
				tempstr += "n";
				break;
			case 'n':
				tempstr += "o";
				break;
			case 'o':
				tempstr += "p";
				break;
			case 'p':
				tempstr += "q";
				break;
			case 'q':
				tempstr += "r";
				break;
			case 'r':
				tempstr += "s";
				break;
			case 's':
				tempstr += "t";
				break;
			case 't':
				tempstr += "u";
				break;
			case 'u':
				tempstr += "v";
				break;
			case 'v':
				tempstr += "w";
				break;
			case 'w':
				tempstr += "x";
				break;
			case 'x':
				tempstr += "y";
				break;
			case 'y':
				tempstr += "z";
				break;
			case 'z':
				tempstr += "a";
				break;
			//numbers
			case '0':
				tempstr += "1";
				break;
			case '1':
				tempstr += "2";
				break;
			case '2':
				tempstr += "3";
				break;
			case '3':
				tempstr += "4";
				break;
			case '4':
				tempstr += "5";
				break;
			case '5':
				tempstr += "6";
				break;
			case '6':
				tempstr += "7";
				break;
			case '7':
				tempstr += "8";
				break;
			case '8':
				tempstr += "9";
				break;
			case '9':
				tempstr += "0";
				break;
			//symbols
			case '/':
				tempstr += "+";
				break;
			case '+':
				tempstr += "/";
				break;
			}
		}
		return tempstr;
	}
	
	//Used to get decoded string of encoded AWS keys
	private String[] getKeyString() {
		String[] keys = new String[2];
		keys[0] = deCode("ZJHZIS1GST53ZKRPD6VZ");
		keys[1] = deCode("LZG6/Cqzm+okF2lYoFyLV5WlsJRezI7Zte+ee2qx");
		return keys;
	}
	
	@RequestMapping(value="/createCharacter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CharacterDTO> createCharacter(HttpSession session, @RequestBody CharacterDTO characterDTO) throws IOException{
		System.out.println("creating new character");
		System.out.println(characterDTO.getImage());
		String sourceData = characterDTO.getImage();
		String[] parts = sourceData.split(",");
		String imageString = parts[1];
		
		BufferedImage img = null;
		byte[] imageByte = Base64.getDecoder().decode(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		img = ImageIO.read(bis);
		bis.close();
		
		File outputFile = new File("image.png");
		ImageIO.write(img, "png", outputFile);
		
		Random r = new Random();
		String key = r.nextInt(1000000)+1+"";
	    
		String[] keys = getKeyString();
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(keys[0], keys[1]);
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2).build();
		try {
		String bucketName = "project2bucketforrevatureportfoliostuff";
			s3.putObject(new PutObjectRequest(bucketName, key, outputFile));
		}
		catch(AmazonServiceException ase) {
			ase.printStackTrace();
		}
		catch(AmazonClientException ace) {
			ace.printStackTrace();
		}
		characterDTO.setImage("https://s3.us-east-2.amazonaws.com/project2bucketforrevatureportfoliostuff/"+key);
        User currentUser = (User) session.getAttribute("user");
        characterDTO.setUser(currentUser);
		characterDTO.setId(serviceimpl.addCharacter(characterDTO).getId());
		return new ResponseEntity<CharacterDTO>(characterDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> createNPC(HttpSession session, @RequestBody NPCDTO npcDTO) throws IOException{
		System.out.println("creating new NPC");
		System.out.println(npcDTO.getImage());
		String sourceData = npcDTO.getImage();
		String[] parts = sourceData.split(",");
		String imageString = parts[1];
		
		BufferedImage img = null;
		byte[] imageByte = Base64.getDecoder().decode(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		img = ImageIO.read(bis);
		bis.close();
		
		File outputFile = new File("image.png");
		ImageIO.write(img, "png", outputFile);
		
		Random r = new Random();
		String key = r.nextInt(1000000)+1+"";
		String[] keys = getKeyString();
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(keys[0], keys[1]);
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2).build();
		try {
		String bucketName = "project2bucketforrevatureportfoliostuff";
			s3.putObject(new PutObjectRequest(bucketName, key, outputFile));
		}
		catch(AmazonServiceException ase) {
			ase.printStackTrace();
		}
		catch(AmazonClientException ace) {
			ace.printStackTrace();
		}
		npcDTO.setImage("https://s3.us-east-2.amazonaws.com/project2bucketforrevatureportfoliostuff/"+key);
        User currentUser = (User) session.getAttribute("user");
        npcDTO.setUser(currentUser);
        npcDTO.setId(serviceimpl.addNonPlayableCharacter(npcDTO).getId());
		return new ResponseEntity<NPCDTO>(npcDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/createEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> createEnemy(HttpSession session, @RequestBody EnemyDTO enemyDTO) throws IOException{
		System.out.println("creating new Enemy");
		System.out.println(enemyDTO.getImage());
		String sourceData = enemyDTO.getImage();
		String[] parts = sourceData.split(",");
		String imageString = parts[1];
		
		BufferedImage img = null;
		byte[] imageByte = Base64.getDecoder().decode(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		img = ImageIO.read(bis);
		bis.close();
		
		File outputFile = new File("image.png");
		ImageIO.write(img, "png", outputFile);
		
		Random r = new Random();
		String key = r.nextInt(1000000)+1+"";
		String[] keys = getKeyString();
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(keys[0], keys[1]);
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2).build();
		try {
		String bucketName = "project2bucketforrevatureportfoliostuff";
			s3.putObject(new PutObjectRequest(bucketName, key, outputFile));
		}
		catch(AmazonServiceException ase) {
			ase.printStackTrace();
		}
		catch(AmazonClientException ace) {
			ace.printStackTrace();
		}
		enemyDTO.setImage("https://s3.us-east-2.amazonaws.com/project2bucketforrevatureportfoliostuff/"+key);
        User currentUser = (User) session.getAttribute("user");
        enemyDTO.setUser(currentUser);
        enemyDTO.setId(serviceimpl.addEnemy(enemyDTO).getId());
		return new ResponseEntity<EnemyDTO>(enemyDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/getCharacters", method= {RequestMethod.GET},
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

	@RequestMapping(value="/getNPCs", method= {RequestMethod.GET},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<NPCDTO>> getNPCs(HttpSession session){
		System.out.println("getting NPCs");
        User currentUser = (User) session.getAttribute("user");
        List<NonPlayableCharacter> NPCs = serviceimpl.getAllNonPlayableCharactersByUser(currentUser);
        List<NPCDTO> npcDTOs = new ArrayList<>();
        for(NonPlayableCharacter npc: NPCs) {
        	NPCDTO temp = new NPCDTO(npc.getId(),npc.getName(),npc.getImage(),currentUser);
        	npcDTOs.add(temp);
        }
		return new ResponseEntity<List<NPCDTO>>(npcDTOs, HttpStatus.OK);
	}

	@RequestMapping(value="/getEnemies", method= {RequestMethod.GET},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<EnemyDTO>> getEnemies(HttpSession session){
		System.out.println("getting Enemys");
        User currentUser = (User) session.getAttribute("user");
        List<Enemy> enemies = serviceimpl.getAllEnemiesByUser(currentUser);
        List<EnemyDTO> enemyDTOs = new ArrayList<>();
        for(Enemy e: enemies) {
        	EnemyDTO temp = new EnemyDTO(e.getId(),e.getName(),e.getImage(),currentUser);
        	enemyDTOs.add(temp);
        }
		return new ResponseEntity<List<EnemyDTO>>(enemyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value="/editCharacter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CharacterDTO> editCharacter(HttpSession session, @RequestBody CharacterDTO characterDTO){
		System.out.println("editing character");
		serviceimpl.updateCharacter(characterDTO);
		return new ResponseEntity<CharacterDTO>(characterDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/editNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> editNPC(HttpSession session, @RequestBody NPCDTO npcDTO){
		System.out.println("editing NPC");
		serviceimpl.updateNonPlayableCharacter(npcDTO);
		return new ResponseEntity<NPCDTO>(npcDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/editEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> editEnemy(HttpSession session, @RequestBody EnemyDTO enemyDTO){
		System.out.println("editing Enemy");
		serviceimpl.updateEnemy(enemyDTO);
		return new ResponseEntity<EnemyDTO>(enemyDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteCharacter", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CharacterDTO> deleteCharacter(HttpSession session, @RequestBody CharacterDTO characterDTO){
		System.out.println("delete character");
		serviceimpl.deleteCharacter(characterDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/deleteNPC", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NPCDTO> deleteNPC(HttpSession session, @RequestBody NPCDTO npcDTO){
		System.out.println("delete NPC");
		serviceimpl.deleteNonPlayableCharacter(npcDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/deleteEnemy", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnemyDTO> deleteEnemy(HttpSession session, @RequestBody EnemyDTO enemyDTO){
		System.out.println("delete Enemy");
		serviceimpl.deleteEnemy(enemyDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
