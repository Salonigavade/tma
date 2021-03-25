package com.capgemini.exception;

public class PlayerException extends Exception{

	public PlayerException() {
		super();
	}
	
	public PlayerException(String msg) {
		super(msg);              
	}
	
	public PlayerException(String msg, Throwable e) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	
}

