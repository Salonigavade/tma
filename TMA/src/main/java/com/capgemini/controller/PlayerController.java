package com.capgemini.controller;

import java.net.URLConnection;
import java.util.List;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.entity.Description;
import com.capgemini.entity.Player;
import com.capgemini.entity.TeamName;
import com.capgemini.exception.PlayerException;
import com.capgemini.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/player")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	//create player
	//localhost:8080/api/player/2
	@PostMapping("/{userId}")
	public ResponseEntity<Player> createPlayer(@PathVariable(value = "userId") Integer userId,@RequestBody Player player){
		try {
			Player player2=playerService.createPlayer(userId,player);
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

	//delete by id
	//localhost:8080/api/player/delete/1
	@DeleteMapping("/delete/{playerId}")
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
	@PutMapping("/update/{playerId}")
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
			p.setFileName(player.getFileName());

			return new ResponseEntity<Player>(playerService.updatePlayer(p),HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	//serach by id
		//localhost:8080/api/player/searchById/1
		@GetMapping("/searchById/{playerId}")
		public ResponseEntity<Player> getById(@PathVariable Integer playerId){
			try {
				Player players=playerService.getPlayerById(playerId);
				return new ResponseEntity<>(players,HttpStatus.OK);

			} catch (PlayerException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}

	//serach by first name
	//localhost:8080/api/player/searchByFirst/hardik
	@GetMapping("/searchByFirst/{playerFirstName}")
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
	@GetMapping("/searchByLast/{playerLastName}")
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
	@GetMapping("/searchByTeam/{teamName}")
	public ResponseEntity<List<Player>> searchPlayerByTeamName(@PathVariable String teamName){
		try {
			List<Player> players=playerService.searchPlayerByTeamName(teamName);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//serach by Descriptin
	//localhost:8080/api/player/searchByDescription/mi
	@GetMapping("/searchByDescription/{description}")
	public ResponseEntity<List<Player>> searchPlayerByDescription(@PathVariable String description){
		try {
			List<Player> players=playerService.searchPlayerByDescription(description);
			return new ResponseEntity<>(players,HttpStatus.OK);

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}


	//upload photo
	//localhost:8080/api/player/upload/2
	@PutMapping("/upload/{playerId}" )
	public String uploadPhoto(@RequestPart("file") MultipartFile file, @PathVariable(value = "playerId") Integer playerId) {

		try {                       
			boolean isUpload=playerService.uploadPhoto(file,playerId);
			if(isUpload) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/player/download/" + playerId).toUriString();
				return fileDownloadUri;
			} else {
				return "Could not upload photo!";
			}

		} catch (PlayerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	//download photo
	//http://localhost:8080/api/player/download/1
	@GetMapping("/download/{playerId}")
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


//create player
////localhost:8080/api/playerNew/2
//@PostMapping("/playerNew/{id}")
//public ResponseEntity<Player> create(@PathVariable(value = "id") Integer id,@RequestParam("file") MultipartFile file,@RequestBody Player player){
//	try {
//		Player player2=playerService.create(id,file,player);
//		
//		return new ResponseEntity<>(player2,HttpStatus.CREATED);
//	} catch (PlayerException e) {
//		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//	}
//}

