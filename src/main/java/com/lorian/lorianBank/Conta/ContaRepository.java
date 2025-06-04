package com.lorian.lorianBank.Conta;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ContaRepository extends JpaRepository<Conta, UUID>{

	Optional<Conta> findByNumero(Long numero);
	
}
