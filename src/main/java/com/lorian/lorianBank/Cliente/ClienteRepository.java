package com.lorian.lorianBank.Cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

	Cliente findByEmail(String email);
	void deleteByEmail(String email);
	
}
