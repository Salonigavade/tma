package com.capgemini.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIgnoreProperties({"hibernetLazyInitilizer","handler"})
@Entity
@Table(name="user_details")
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=3, message="First name should have atleast 3 characters!")
	@Column(name = "userName")
	private String userName;
	
	@NotNull
	@Email(message="Email format invalid!")
	@Column(name = "emailId")
	private String emailId;
	
	@Size(max=10)
	@Column(name="phoneNo", nullable = false)
	private String phone;
	
	@NotNull
	@Pattern(regexp="^[A-Za-z_0-9@#$%]{6,12}",message="Password must be 6 characters")
	@Column(name="password")
	private String password;
	
//	@NotNull
//	@Pattern(regexp="^[A-Za-z_0-9@#$%]{6,12}",message="password and Re entered password must be same")
//	@Column(name="reEnterpassword")
//	private String ReEnterPassword;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Player> players;
}
