package com.capgemini.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.entity.Login;
import com.capgemini.entity.Player;
import com.capgemini.entity.TeamName;
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
	public List<Player> searchPlayerByFirstName(String playerFirstName) throws PlayerException {
		try {
			return playerRepository.findByPlayerFirstName(playerFirstName);
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}

	@Override
	public List<Player> searchPlayerByTeamName(TeamName teamName) throws PlayerException {
		try {
			return playerRepository.findByType(teamName);
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


	@Override
	public List<Player> searchPlayerByLastName(String playerLastName) throws PlayerException {
		try {
			return playerRepository.findByPlayerLastName(playerLastName);
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}


	@Override
	public boolean uploadPhoto(Integer id,MultipartFile file) throws PlayerException {
		boolean isUpload=false;
		try {
			Player player=playerRepository.findById(id).get();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
			player.setPhotos(fileName);
			player.setFile(file.getBytes());;
			playerRepository.save(player);
			isUpload=true;
		   // return playerRepository.save(p);                    
		} catch (DataAccessException | IOException e) {
			throw new PlayerException(e.getMessage(),e);
		}
		return isUpload;
	}


	@Override
	public byte[] getPhotoById(Integer playerId) throws PlayerException {
		try {
			Player player=playerRepository.findById(playerId).get();
			return player.getFile();
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}


	@Override
	public String getPhotoNameById(Integer playerId) throws PlayerException {
		try {
			Player player=playerRepository.findById(playerId).get();
			String fileName = player.getPhotos();
			return fileName;
		} catch (DataAccessException e) {
			throw new PlayerException(e.getMessage(),e);
		}
	}


	@Override
	public Player create(Integer id, MultipartFile file, Player player) throws PlayerException {
		try {
			User user=userRepository.findById(id).get();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
			player.setPhotos(fileName);
			player.setFile(file.getBytes());
			player.setUser(user);
			return playerRepository.save(player);                    
		} catch (DataAccessException | IOException e) {
			throw new PlayerException(e.getMessage(),e);
		}

	}

	



}
