package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.entity.User;
import com.capgemini.exception.UserException;
import com.capgemini.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create user
	//localhost:8080/api/user
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User user2=userService.createUser(user);
			return new ResponseEntity<User>(user2,HttpStatus.CREATED);
		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/user/by-email/{emailId}")
	public ResponseEntity<User> findByEmailId(@PathVariable String emailId){
		try {
			User user2=userService.findByEmailId(emailId);
			return new ResponseEntity<User>(user2,HttpStatus.CREATED);
		} catch (UserException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
