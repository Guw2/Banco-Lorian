package com.lorian.lorianBank.cartao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CartaoRepository extends JpaRepository<Cartao, Integer>{
	Optional<Cartao> findByNumero(String numero);
	void deleteByNumero(String numero);
}
