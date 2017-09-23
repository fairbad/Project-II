package com.dungeoncrawlers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dungeoncrawlers.dto.UserDTO;
import com.dungeoncrawlers.service.ServiceInterface;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private ServiceInterface serviceimpl;

	public void setServiceImpl(ServiceInterface serviceImpl) {
		this.serviceimpl = serviceImpl;
	}
	
	//TODO: Needs a dto add this
	@RequestMapping(value="/auth", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO> 
		authenticateUser(@RequestBody UserDTO userDto){	
		System.out.println("Inside the user Login Conntroller");
		return new ResponseEntity<UserDTO>(serviceimpl.authenticateUser(userDto),HttpStatus.OK);
	}

	@RequestMapping(value="/register", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO>	registerUser(@RequestBody UserDTO userDTO){
		System.out.println("creating new user");
		serviceimpl.addUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	@RequestMapping(value="/editUser", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO>	edtiUser(@RequestBody UserDTO userDTO){
		System.out.println("Inside the Edit User Conntroller");
		serviceimpl.addUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
}
