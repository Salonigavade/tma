package com.capgemini.entity;

import lombok.Data;

@Data
public class ForgotPassword {
	private String emailId;
	private String userName;
}
