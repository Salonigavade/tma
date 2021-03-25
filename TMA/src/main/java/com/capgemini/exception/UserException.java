package com.capgemini.exception;

public class UserException extends Exception{

	public UserException() {
		super();
	}

	public UserException(String msg) {
		super(msg);
	}
	
	public UserException(String msg, Throwable e) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
