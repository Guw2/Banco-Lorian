package com.lorian.lorianBank.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{

	Optional<UserDetails> findByUser(String user);
	
}
