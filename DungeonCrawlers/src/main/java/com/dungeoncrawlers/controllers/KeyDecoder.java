package com.dungeoncrawlers.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dungeoncrawlers.dto.CampaignAndComponentsDTO;
import com.dungeoncrawlers.dto.CharacterDTO;
import com.dungeoncrawlers.dto.EnemyDTO;
import com.dungeoncrawlers.dto.NPCDTO;

public class KeyDecoder {

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
		
		public CharacterDTO HandleCharImages(CharacterDTO characterDTO) throws IOException{
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
			return characterDTO;
		}
		
		public NPCDTO HandleNPCImages(NPCDTO npcDTO) throws IOException{
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
			return npcDTO;
		}
		
		public EnemyDTO HandleEnemyImages(EnemyDTO enemyDTO) throws IOException{
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
			return enemyDTO;
		}
		
		public CampaignAndComponentsDTO HandleCampaignImages(CampaignAndComponentsDTO cacDTO) throws IOException{
			//Campaign stuff
			if(cacDTO.getCampaign().getImage() != null && cacDTO.getCampaign().getImage().length() > 100) {
				System.out.println(cacDTO.getCampaign().getImage());
				String sourceData = cacDTO.getCampaign().getImage();
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
				cacDTO.getCampaign().setImage("https://s3.us-east-2.amazonaws.com/project2bucketforrevatureportfoliostuff/"+key);
			}
			
			//Map stuff
			if(cacDTO.getCampaign().getMap().getImage() != null && cacDTO.getCampaign().getMap().getImage().length() > 100) {
				System.out.println(cacDTO.getCampaign().getMap().getImage());
				String sourceData = cacDTO.getCampaign().getMap().getImage();
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
				cacDTO.getCampaign().getMap().setImage("https://s3.us-east-2.amazonaws.com/project2bucketforrevatureportfoliostuff/"+key);
			}
			
			return cacDTO;
		}
		
}
