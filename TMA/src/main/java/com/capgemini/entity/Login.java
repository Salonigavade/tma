package com.capgemini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The Class Login.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Login {
	// @NotNull(message="email must not be empty")
    private String emailId;
   // @NotEmpty(message="Password must not be empty")
    private String password;
  
	
}