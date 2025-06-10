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
	
	// Constructor Injection
	public ContaFactoryImpl(ClienteRepository cliente_repo, ContaRepository conta_repo) {
		this.cliente_repo = cliente_repo;
		this.conta_repo = conta_repo;
	}

	@Override // Implementação do método generate
	public Conta generate(TipoConta tipo, Long cliente_id) {
		// Cria uma nova conta
		Conta conta = new Conta();
		
		// Gera a conta
		conta.setNumero(generateNumero());
		conta.setAgencia(generateAgencia());
		conta.setSaldo(0D);
		
		// Transfere as informações dos parâmetros
		conta.setTipo(tipo);
		conta.setData_de_abertura(Instant.now());
		conta.setCliente(cliente_repo.findById(cliente_id).orElseThrow(() -> new IdNotFoundException("Id não existente.")));
		
		// Retorna a conta
		return conta;
	}
	
	// Gera um número de conta novo
	private Long generateNumero() {
		// Escolhe um número aleatório de 10 dígitos
		Long numero = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
		// Verifica se o número já está presente no banco de dados (bastante raro mas pode acontecer)
		while(conta_repo.findByNumero(numero).isPresent()) {
			// Caso esteja, ele gera outro
			numero = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
		} // Se o número gerado novamente também já está no banco de dados, ele gera outro
		
		// retorna o número
		return numero;
	}
	
	// Escolhe uma agência aleatória
	private String generateAgencia() {
		// Array de agências
		String[] agencias = {"0015", "0016", "0017", "0018", "0019", "0020", "0021", "0022", "0023", "0024", "0025"};
		// Sorteia um índice de 0 a 11
		Integer option = ThreadLocalRandom.current().nextInt(0, 11);
		// Retorna uma agência aleatória
		return agencias[option];
	}
	
}
