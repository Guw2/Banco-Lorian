package com.lorian.lorianBank.cartao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CartaoRepository extends JpaRepository<Cartao, UUID>{
	Optional<Cartao> findByNumero(String numero);
	void deleteByNumero(String numero);
}
