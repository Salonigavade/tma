package com.capgemini.service;

import com.capgemini.entity.User;
import com.capgemini.exception.UserException;

public interface UserService {
	public User createUser(User user) throws UserException;
	
	public User findByEmailId(String emailId) throws UserException;
}
