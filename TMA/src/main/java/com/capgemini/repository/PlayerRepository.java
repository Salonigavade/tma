package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
