package com.capgemini.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.entity.User;
import com.capgemini.exception.UserException;
import com.capgemini.repository.UserRepository;

@Service(value = "userService")
//@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) throws UserException {
		try {
			User user2=userRepository.save(user);
			return user2;		
		} catch (DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}
	}
}
