package com.capgemini.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.entity.Description;
import com.capgemini.entity.Player;
import com.capgemini.entity.TeamName;
import com.capgemini.exception.PlayerException;

public interface PlayerService {
	public Player createPlayer(Integer id,Player player) throws PlayerException;
	public List<Player> getAllPlayers() throws PlayerException;
	public Player getPlayerById(Integer playerId) throws PlayerException;
	public Integer deletePlayerById(Integer playerId) throws PlayerException;
	public Player updatePlayer(Player player) throws PlayerException;
	
	public List<Player> searchPlayerByFirstName(String playerFirstName) throws PlayerException;
	public List<Player> searchPlayerByTeamName(TeamName teamName) throws PlayerException;
	public List<Player> searchPlayerByLastName(String playerLastName) throws PlayerException;
	public List<Player> searchPlayerByDescription(Description description) throws PlayerException;
	
	public boolean uploadPhoto(Integer id,MultipartFile file) throws PlayerException;
	
	public byte[] getPhotoById(Integer playerId) throws PlayerException;
	public String getPhotoNameById(Integer playerId) throws PlayerException;

}
