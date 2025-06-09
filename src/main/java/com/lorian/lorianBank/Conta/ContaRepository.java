package com.lorian.lorianBank.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByNumero(Long numero);
	void deleteByNumero(Long numero);
}
