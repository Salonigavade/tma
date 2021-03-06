package com.capgemini.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Pojo class
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="player")
public class Player {

	@Id
	@Column(name = "playerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playerId;
	
	@NotNull
	@Size(min=3, message="First name should have atleast 3 characters!")
	@Column(name = "firstName")
	private String playerFirstName;

	@NotNull
	@Size(min=3, message="Last name should have atleast 3 characters!")
	@Column(name = "lastName")
	private String playerLastName;
	
	@NotNull
	@Column(name = "price")
	private Double price;
	
	@NotNull
	@Column(name = "teamName")
//	@Enumerated(EnumType.STRING)
	private String teamName;
	
	@NotNull
	@Column(name = "playerStatus")
//	@Enumerated(EnumType.STRING)
	private String status;
	
	@NotNull
	@Column(name = "description")
//	@Enumerated(EnumType.STRING)
	private String description;
	
	@Column
	private String fileName;
	
	@JsonIgnore
	@Lob
	@Column
	private byte[] playerFile;
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
}
