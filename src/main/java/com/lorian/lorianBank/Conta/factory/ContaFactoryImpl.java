package com.lorian.lorianBank.conta.factory;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.conta.TipoConta;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;

@Component
public class ContaFactoryImpl implements ContaFactory{

	private final ClienteRepository cliente_repo;
	private final ContaRepository conta_repo;
	
	public ContaFactoryImpl(ClienteRepository cliente_repo, ContaRepository conta_repo) {
		this.cliente_repo = cliente_repo;
		this.conta_repo = conta_repo;
	}

	@Override
	public Conta generate(TipoConta tipo, Long cliente_id) {
		Conta conta = new Conta();
		conta.setNumero(generateNumero());
		conta.setAgencia(generateAgencia());
		conta.setSaldo(0D);
		conta.setTipo(tipo);
		conta.setData_de_abertura(Instant.now());
		conta.setCliente(cliente_repo.findById(cliente_id).orElseThrow(() -> new IdNotFoundException("Id não existente.")));
		return conta;
	}
	
	private Long generateNumero() {
		Long numero = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
		while(conta_repo.findByNumero(numero).isPresent()) {
			numero = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
		}
		return numero;
	}
	
	private String generateAgencia() {
		String[] agencias = {"0015", "0016", "0017", "0018", "0019", "0020", "0021", "0022", "0023", "0024", "0025"};
		Integer option = ThreadLocalRandom.current().nextInt(0, 11);
		return agencias[option];
	}
	
}
