package com.capgemini.controller;

import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.entity.Player;
import com.capgemini.entity.TeamName;
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
	
	//create player
		//localhost:8080/api/player/2
		@PostMapping("/playerNew/{id}")
		public ResponseEntity<Player> create(@PathVariable(value = "id") Integer id,@RequestParam("file") MultipartFile file,@RequestBody Player player){
			try {
				Player player2=playerService.create(id,file,player);
				
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
	@DeleteMapping("/player/delete/{playerId}")
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

	//serach by first name
	//localhost:8080/api/player/searchByFirst/hardik
	@GetMapping("/player/searchByFirst/{playerFirstName}")
	public ResponseEntity<List<Player>> serchPlayerByFirstName(@PathVariable String playerFirstName){
		try {
			List<Player> players=playerService.searchPlayerByFirstName(playerFirstName);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	//serach by last name
		//localhost:8080/api/player/searchByLast/Raina
		@GetMapping("/player/searchByLast/{playerLastName}")
		public ResponseEntity<List<Player>> searchPlayerByLastName(@PathVariable String playerLastName){
			try {
				List<Player> players=playerService.searchPlayerByLastName(playerLastName);
				return new ResponseEntity<>(players,HttpStatus.OK);

			} catch (PlayerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}

	//serach by team
	//localhost:8080/api/player/searchByTeam/mi
	@GetMapping("/player/searchByTeam/{teamName}")
	public ResponseEntity<List<Player>> searchPlayerByTeamName(@PathVariable TeamName teamName){
		try {
			List<Player> players=playerService.searchPlayerByTeamName(teamName);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}


	//upload photo
	//localhost:8080/api/upload/2
	@PutMapping("/upload/{id}")
	  public String uploadPhoto(@PathVariable(value = "id") Integer id,@RequestParam("file") MultipartFile file) {
	    
	    try {                       
	    	boolean isUpload=playerService.uploadPhoto(id,file);
	    	if(isUpload) {
	    		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/player/download/" + id).toUriString();
				return fileDownloadUri;
			} else {
				return "Could not upload certificate!";
			}
	      
	    } catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
	    }
	  }
	
	//download photo
	//http://localhost:8080/api/player/download/1
		@GetMapping("/player/download/{playerId}")
		public ResponseEntity downloadFromDB(@PathVariable Integer playerId) {
			
			try {
				String fileName = playerService.getPhotoNameById(playerId);
				String type = URLConnection.guessContentTypeFromName(fileName);
				byte[] photo = playerService.getPhotoById(playerId);
				
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(type))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" 
								+ fileName + "\"")
						.body(photo);
		
			} catch (PlayerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		    }
		}
}
