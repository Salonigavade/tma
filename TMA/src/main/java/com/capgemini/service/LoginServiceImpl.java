package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.ForgotPassword;
import com.capgemini.entity.Login;
import com.capgemini.entity.Logout;
import com.capgemini.entity.User;
import com.capgemini.exception.ResourceNotFound;
import com.capgemini.repository.LoginRepository;

import java.util.Optional;

/**
 * The Class LoginServiceImpl.
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public String signIn(Login registerUser) {
		String str = null;
		Optional<User> userObj = loginRepository.findByEmailId(registerUser.getEmailId());
		if (!userObj.isPresent()) {
			System.out.println(userObj);
			throw new ResourceNotFound("user not found for this id");
		} else {
			String pwd = userObj.get().getPassword();
 			if (!pwd.equals(registerUser.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				str = "Sign in sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new ResourceNotFound("OPERATION_FAILED");
			}
		}
		return str;
	}

	@Override
	public String signOut(Logout registerUser) {
		String str = null;
		Optional<User> userObj = loginRepository.findByEmailId(registerUser.getEmailId());
		if (!userObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			try {
				str = "Sign Out sucessfull";
				loginRepository.saveAndFlush(userObj.get());
			} catch (Exception e) {
				throw new ResourceNotFound("OPERATION_FAILED");
			}
		}
		return str;
	}

	@Override
	public String changePassword(Login registerUser, String new_password) {
		String str = null;
		Optional<User> userObj = loginRepository.findByEmailId(registerUser.getEmailId());
		if (!userObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String pwd = userObj.get().getPassword();
			if (!pwd.equals(registerUser.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				userObj.get().setPassword(new_password);
				loginRepository.saveAndFlush(userObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new ResourceNotFound("OPERATION_FAILED");
			}
		}
		return str;
	}

	@Override
	public String forgotPassword(ForgotPassword registerUser, String new_password) {
		String str = null;
		Optional<User> userObj = loginRepository.findByEmailId(registerUser.getEmailId());
		
		if (!userObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String userName = userObj.get().getUserName();
			if (!userName.equals(registerUser.getUserName())) {
				throw new ResourceNotFound("USER_NOT_FOUND");
			}
			try {
				userObj.get().setPassword(new_password);
				loginRepository.saveAndFlush(userObj.get());
				str = "Password updated sucessfully";
			} catch (Exception e) {
				throw new ResourceNotFound("OPERATION_FAILED");
			}
		}
		return str;
	}
	
	
}