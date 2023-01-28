package com.suraj.blogs.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suraj.blogs.entities.User;
import com.suraj.blogs.payloads.JwtAuthRequest;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	@Modifying
	@Transactional
	@Query(value="update users set password =:password where email =:username" ,nativeQuery = true)
	int save(String username, String password);
	
}
