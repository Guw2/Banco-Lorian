package com.lorian.lorianBank.conta;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ContaRepository extends JpaRepository<Conta, UUID>{

	Optional<Conta> findByNumero(Long numero);
	void deleteByNumero(Long numero);
}
