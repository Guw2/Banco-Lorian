package com.lorian.lorianBank.transacao;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.transacao.DTOs.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.TransacaoPostDTO;

@Component
public class TransacaoMapper {

	@Autowired
	private static ContaRepository conta_repo;
	
	protected static TransacaoGetDTO transacaoToGetDto(Transacao transacao) {
		return new TransacaoGetDTO(transacao.getValor(),
				transacao.getData(),
				transacao.getTipo(),
				transacao.getConta().getNumero(),
				transacao.getConta_destino().getNumero());
		
	}
	
	protected static Transacao postDtoToTransacao(TransacaoPostDTO dto) {
		Transacao transacao = new Transacao();
		
		transacao.setValor(dto.valor());
		transacao.setTipo(dto.tipo());
		transacao.setData(Instant.now());
		transacao.setDescricao(dto.descricao());
		transacao.setConta(conta_repo.findById(dto.conta_id())
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
		transacao.setConta_destino(conta_repo.findById(dto.conta_destino_id())
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
		
		return transacao;
	}
	
}
