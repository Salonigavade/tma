package com.capgemini.service;

import com.capgemini.entity.ForgotPassword;
import com.capgemini.entity.Login;
import com.capgemini.entity.Logout;

/**
 * The Interface LoginService.
 */
public interface LoginService {

	/**
	 * Sign in.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	public String signIn(Login registerUser);
	
	/**
	 * Sign out.
	 *
	 * @param registerUser the register user
	 * @return the string
	 */
	public String signOut(Logout registerUser);
	
	/**
	 * Change password.
	 *
	 * @param registerUser the register user
	 * @param new_password the new password
	 * @return the string
	 */
	public String changePassword(Login registerUser, String new_password);
	
	public String forgotPassword(ForgotPassword registerUser, String new_password);

}