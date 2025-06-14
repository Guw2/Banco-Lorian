package com.lorian.lorianBank.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.lorian.lorianBank.cliente.Cliente;



public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findByCliente(Cliente cliente);
	Optional<Conta> findByNumero(Long numero);
	void deleteByNumero(Long numero);
}
