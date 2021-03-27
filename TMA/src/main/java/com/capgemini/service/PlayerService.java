package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Player;
import com.capgemini.exception.PlayerException;

public interface PlayerService {
	public Player createPlayer(Integer id,Player player) throws PlayerException;
	public List<Player> getAllPlayers() throws PlayerException;
	public Player getPlayerById(Integer playerId) throws PlayerException;
	public Integer deletePlayerById(Integer playerId) throws PlayerException;
	public Player updatePlayer(Player player) throws PlayerException;
	public List<Player> serachPlayerByFirstName(String playerFirstName) throws PlayerException;
	public List<Player> serachPlayerByTeamName(String teamName) throws PlayerException;
	public List<Player> serachPlayerByLastName(String playerLastName) throws PlayerException;
	
}
