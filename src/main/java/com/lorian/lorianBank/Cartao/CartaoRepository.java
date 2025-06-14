package com.lorian.lorianBank.cartao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.cliente.Cliente;


public interface CartaoRepository extends JpaRepository<Cartao, Long>{
	List<Cartao> findByConta(Conta conta);
	List<Cartao> findByCliente(Cliente cliente);
	Optional<Cartao> findByNumero(String numero);
	void deleteByNumero(String numero);
}
