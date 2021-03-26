package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Login;
import com.capgemini.entity.Player;
import com.capgemini.entity.User;
import com.capgemini.exception.PlayerException;
import com.capgemini.repository.LoginRepository;
import com.capgemini.repository.PlayerRepository;
import com.capgemini.repository.UserRepository;


@Service(value = "playerService")
//@Transactional

public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Player createPlayer(Integer id, Player player) throws PlayerException {
		try {
			Optional<User> uOptional=userRepository.findById(id);
			User u=null;
			if(uOptional.isPresent()) {
				u=uOptional.get();
			}	
			player.setUser(u);
			return playerRepository.save(player);
				
			
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	
	}


	@Override
	public List<Player> getAllPlayers() throws PlayerException {
		try {
			return playerRepository.findAll();
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public Integer deletePlayerById(Integer playerId) throws PlayerException {
		try {
			playerRepository.deleteById(playerId);
			return 1;
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public Player updatePlayer(Player player) throws PlayerException {
		try {
			Player p=playerRepository.getOne(player.getPlayerId());
			if(p!=null) {
				Player p3=playerRepository.save(player);
				return p3;
			}else {
				throw new PlayerException("No record found for given employee id");
			}
			
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public List<Player> serachPlayerByName(String playerFirstName) throws PlayerException {
		try {
			return playerRepository.findByPlayerName(playerFirstName);
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public List<Player> serachPlayerByTeamName(String teamName) throws PlayerException {
		try {
			return playerRepository.findPlayerByTeamName(teamName);
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public Player getPlayerById(Integer playerId) throws PlayerException {
		try {
			Optional<Player> opt=playerRepository.findById(playerId);
			if(opt.isPresent()) {
				return opt.get();
			}
			else 
				throw new PlayerException("unable to find id");
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	



}
