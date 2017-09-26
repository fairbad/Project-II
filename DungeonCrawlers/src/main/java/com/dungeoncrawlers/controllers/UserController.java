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

	@RequestMapping(value="/register", method= {RequestMethod.POST},
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO>	registerUser(@RequestBody UserDTO userDTO){
		serviceimpl.addUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	 @RequestMapping(value="/auth", method= {RequestMethod.POST},
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO>
        authenticateUser(HttpServletRequest req, @RequestBody UserDTO userDTO){ 
        userDTO = serviceimpl.authenticateUser(userDTO);
        HttpSession session = req.getSession(true);
        session.setAttribute("currentUser", serviceimpl.getUser(userDTO.getId()));
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/editUser", method= {RequestMethod.POST},
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO>    edtiUser(@RequestBody UserDTO userDTO){
        System.out.println("Inside the Edit User Controller");
        serviceimpl.updateUser(userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value="/homeView", method= {RequestMethod.POST},
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO>    getUser(@RequestBody UserDTO userDTO){
        System.out.println("Inside the Home Controller");
        serviceimpl.getUser(userDTO.getId());
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
}
