package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.entity.Player;
import com.capgemini.exception.PlayerException;
import com.capgemini.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	//create player
	//localhost:8080/api/player/2
	@PostMapping("/player/{id}")
	public ResponseEntity<Player> createPlayer(@PathVariable(value = "id") Integer id,@RequestBody Player player){
		try {
			Player player2=playerService.createPlayer(id,player);
			return new ResponseEntity<>(player2,HttpStatus.CREATED);
		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//getAllPlayers
	//localhost:8080/api/playerInfo
	@GetMapping("/playerInfo")
	public ResponseEntity<List<Player>> getAllPlayers(){
		try {
			List<Player> playerList=playerService.getAllPlayers();
			return new ResponseEntity<>(playerList,HttpStatus.OK);
		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//delet by id
	//localhost:8080/api/player/1
	@DeleteMapping("/player/{playerId}")
	public String deletePlayerById(@PathVariable( value = "playerId") Integer playerId){
		try {
			Integer status=playerService.deletePlayerById(playerId);
			if(status==1)
				return "player "+ playerId +" deleted from database";
			else
				return "Unable to delete";
		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//update player
	//localhost:8080/api/player/update/1
	@PutMapping("/player/update/{playerId}")
	public ResponseEntity<Player> updatePlayerInfo(@PathVariable( value = "playerId") Integer playerId,@RequestBody Player player){
		Player p;
		try {
			p=playerService.getPlayerById(playerId);
			p.setPlayerFirstName(player.getPlayerFirstName());
			p.setPlayerLastName(player.getPlayerLastName());
			p.setPrice(player.getPrice());
			p.setTeamName(player.getTeamName());
			p.setStatus(player.getStatus());
			p.setDescription(player.getDescription());
			p.setPhotos(player.getPhotos());

			return new ResponseEntity<Player>(playerService.updatePlayer(p),HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//serach by name
	//localhost:8080/api/player/searchByFirst/hardik
	@GetMapping("/player/searchByFirst/{playerFirstName}")
	public ResponseEntity<List<Player>> serchPlayerByFirstName(@PathVariable String playerFirstName){
		try {
			List<Player> players=playerService.serachPlayerByName(playerFirstName);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//serach by team
	//localhost:8080/api/player/searchByTeam/mi
	@GetMapping("/player/searchByTeam/{teamName}")
	public ResponseEntity<List<Player>> serchPlayerByTeamName(@PathVariable String teamName){
		try {
			List<Player> players=playerService.serachPlayerByTeamName(teamName);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}


}
