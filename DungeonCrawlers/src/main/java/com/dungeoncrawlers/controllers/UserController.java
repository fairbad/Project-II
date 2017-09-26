package com.dungeoncrawlers.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dungeoncrawlers.beans.User;
import com.dungeoncrawlers.dto.UserDTO;
import com.dungeoncrawlers.service.ServiceInterface;

@RestController
@RequestMapping(value="/user")
@SessionAttributes("userDTO")
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
        authenticateUser(@RequestBody UserDTO userDTO, HttpSession session){ 
        userDTO = serviceimpl.authenticateUser(userDTO);
        if(userDTO != null){
        	 //addUserInSession(userDTO, session);
        	 session.setAttribute("user", userDTO);
        	 userDTO = (UserDTO)session.getAttribute("user");
        	 System.out.println("This is my session: " + session.getId());
             System.out.println("This is my session user: " + session.getAttribute("user"));
             //System.out.println("This is my session user email: " + session.getAttribute(userDTO.getEmail()));
        }
       
        //userDTO.addtAttribute(userDTO);
        
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
    private void addUserInSession(UserDTO u, HttpSession session){
    	System.out.println("Inside the addUserInSession");
    	System.out.println("User email: " + u.getEmail());
    	
    	session.setAttribute("user", u);
    	//session.setAttribute("user", u.getId());
    	System.out.println("Session att: " + session.getAttribute("user"));
    	System.out.println("Getting email: " + session.getAttribute("user"));
    }
}
