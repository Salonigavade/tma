package com.capgemini.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
	@Query("select p from Player p where p.playerFirstName=?1")
	List<Player> findByPlayerFirstName(String playerFirstName);

	@Query("select p from Player p where p.playerLastName=?1")
	List<Player> findByPlayerLastName(String playerLasttName);
	
	@Query("select p from Player p where p.teamName=?1")
	List<Player> findPlayerByTeamName(String teamName);

	//List<Player> findByUserId(Integer id);
//	Optional<Player> findByIdAndUserId(Integer playerId,Integer id);
}
