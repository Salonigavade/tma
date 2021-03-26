package com.capgemini.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.User;

/**
 * The Interface LoginRepository.
 */
@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {
	
	@Query("select rs from User rs where rs.emailId=?1")
	Optional<User> findByEmailId(String emailId);
	
	
	
}