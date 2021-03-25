package com.capgemini.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.ForgotPassword;
import com.capgemini.entity.Login;
import com.capgemini.entity.Logout;
import com.capgemini.service.LoginService;

/**
 * The Class LoginController.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")

public class LoginController {

	@Autowired 
	private LoginService loginService;

	//http://localhost:8081/springfox/api/Login/login
	
	@PostMapping("/login")
	public ResponseEntity<?> signIn( @RequestBody Login registerUser) {
		String str = loginService.signIn(registerUser);
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}

	//http://localhost:8081/springfox/api/Login/logout
	@PostMapping("/logout") 

	public ResponseEntity<?> signOut( @RequestBody Logout registerUser) {
		String str = loginService.signOut(registerUser);
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
	
	
	//http://localhost:8081/springfox/api/Login/reset/newPass
	@PostMapping("/reset/{new_password}")
	public ResponseEntity<?> changePassword( @RequestBody Login registerUser, @PathVariable String new_password) {
		String str =loginService.changePassword(registerUser, new_password);
	
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
	
	@PostMapping("/forgot/{newPassword}")
	public ResponseEntity<?> forgotPassword( @RequestBody ForgotPassword registerUser,@PathVariable String newPassword) {
		String str =loginService.forgotPassword(registerUser, newPassword);
		return new ResponseEntity<>(str, HttpStatus.OK);
	}

}