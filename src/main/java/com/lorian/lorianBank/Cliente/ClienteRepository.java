package com.lorian.lorianBank.cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

	Optional<Cliente> findByEmail(String email);
	void deleteByEmail(String email);
	
}
