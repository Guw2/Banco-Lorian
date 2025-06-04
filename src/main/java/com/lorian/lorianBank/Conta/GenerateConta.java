package com.lorian.lorianBank.Conta;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.Cliente.ClienteRepository;

@Component
public class GenerateConta {

	private final ClienteRepository cliente_repo;
	private final ContaRepository conta_repo;
	
	public GenerateConta(ClienteRepository cliente_repo, ContaRepository conta_repo) {
		this.cliente_repo = cliente_repo;
		this.conta_repo = conta_repo;
	}

	protected Conta generate(TipoConta tipo, UUID cliente_id) {
		Conta conta = new Conta();
		conta.setNumero(generateNumero());
		conta.setAgencia(generateAgencia());
		conta.setSaldo(0D);
		conta.setTipo(tipo);
		conta.setData_de_abertura(Instant.now());
		conta.setCliente(cliente_repo.findById(cliente_id).get());
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
