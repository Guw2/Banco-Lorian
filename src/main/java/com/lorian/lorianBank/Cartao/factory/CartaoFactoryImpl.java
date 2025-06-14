package com.lorian.lorianBank.cartao.factory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.BandeiraCartao;
import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoPostDTO;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.conta.TipoConta;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;

@Component
public class CartaoFactoryImpl implements CartaoFactory {
	
	private final ContaRepository conta_repo;
	
	// Constructor Injection
	public CartaoFactoryImpl(ContaRepository conta_repo) {
		this.conta_repo = conta_repo;
	}
	
	@Override // Implementação do método generate
	public Cartao generate(Long conta_id) {
		
		// Verifica se a conta associada ao DTO é poupança
		// Se for, é lançada uma exceção
		if(conta_repo.findById(conta_id).get().getTipo() == TipoConta.POUPANCA)
			throw new RuntimeException("Contas poupança não podem ter cartões de crédito associados.");
		// Caso não seja, o cartão é gerado normalmente
		else {
			// É criado um novo cartão
			Cartao cartao = new Cartao();
			// Gera os dados
			cartao.setNumero(generateNumero());
			cartao.setCvv(generateCvv());
			cartao.setValidade(LocalDateTime.now().plusYears(5).toInstant(ZoneOffset.of("-03:00")));
			cartao.setLimite(500.0D);
			cartao.setBandeira(chooseBandeira());
			cartao.setAtivado(false);
			// Transfere informações do DTO passado como parâmetro
			cartao.setCliente(conta_repo.findById(conta_id)
					.orElseThrow(() -> new IdNotFoundException("Id de cliente não encontrado.")).getCliente());
			cartao.setConta(conta_repo.findById(conta_id)
					.orElseThrow(() -> new IdNotFoundException("Id de conta não encontrado.")));
			
			// Retorna o cartão
			return cartao;
		}
	}
	
	// Operação de gerar um novo número de cartão
	private String generateNumero() {
		// Começa com uma String vazia
		String numero = "";
		
		// Com uma estrutura de repetição, são geradas 4 sequências de 4 números
		// separadas por hífens
		for(int i = 0; i < 4; i++) {
			var sequence = ThreadLocalRandom.current().nextInt(1000, 10000);
			numero += "" + sequence;
			if(i < 3) numero += "-"; 
		}
		// Retorna o número gerado
		return numero;
	}
	
	// Operação de gerar um CVV para o cartão
	private Integer generateCvv() {
		// Gera um número aleatório de 3 dígitos
		Integer cvv = ThreadLocalRandom.current().nextInt(100, 1000);
		// Retorna o CVV
		return cvv;
	}
	
	// Escolhe aleatóriamente uma bandeira pro cartão
	private BandeiraCartao chooseBandeira() {
		// Retorna um Enum com um índice aleatório de 0 a 7
		return BandeiraCartao.values()[ThreadLocalRandom.current().nextInt(0, 7)];
	}

}
