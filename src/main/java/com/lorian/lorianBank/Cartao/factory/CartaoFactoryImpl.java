package com.lorian.lorianBank.cartao.factory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.BandeiraCartao;
import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoPostDTO;
import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.conta.TipoConta;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;

@Component
public class CartaoFactoryImpl implements CartaoFactory {
	
	private final ContaRepository conta_repo;
	
	public CartaoFactoryImpl(ContaRepository conta_repo) {
		this.conta_repo = conta_repo;
	}
	
	@Override
	public Cartao generate(CartaoPostDTO dto) {
		
		if(conta_repo.findById(dto.getConta_id()).get().getTipo() == TipoConta.POUPANCA)
			throw new RuntimeException("Contas poupança não podem ter cartões de crédito associados.");
		else {
			Cartao cartao = new Cartao();
			cartao.setNumero(generateNumero());
			cartao.setCvv(generateCvv());
			cartao.setValidade(LocalDateTime.now().plusYears(5).toInstant(ZoneOffset.of("-03:00")));
			cartao.setLimite(500.0D);
			cartao.setBandeira(chooseBandeira());
			cartao.setAtivado(false);
			cartao.setCliente(conta_repo.findById(dto.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Id de cliente não encontrado.")).getCliente());
			cartao.setConta(conta_repo.findById(dto.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Id de conta não encontrado.")));
			
			return cartao;
		}
	}
	
	private String generateNumero() {
		String numero = "";
		for(int i = 0; i < 4; i++) {
			var sequence = ThreadLocalRandom.current().nextInt(1000, 10000);
			numero += "" + sequence;
			if(i < 3) numero += "-"; 
		}
		return numero;
	}
	
	private Integer generateCvv() {
		Integer cvv = ThreadLocalRandom.current().nextInt(100, 1000);
		return cvv;
	}
	
	private BandeiraCartao chooseBandeira() {
		return BandeiraCartao.values()[ThreadLocalRandom.current().nextInt(0, 7)];
	}

}
