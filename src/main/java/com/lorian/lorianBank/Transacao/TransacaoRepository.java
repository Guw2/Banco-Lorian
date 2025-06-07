package com.lorian.lorianBank.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorian.lorianBank.conta.Conta;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID>{
	
	List<Transacao> findByConta(Conta conta);

}
