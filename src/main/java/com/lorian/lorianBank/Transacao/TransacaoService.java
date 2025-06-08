package com.lorian.lorianBank.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.transacao.DTOs.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.TransacaoPostDTO;

@Service
public class TransacaoService {

	private final TransacaoRepository repo;
	private final TransacaoMapper mapper;
	
	public TransacaoService(TransacaoRepository repo, TransacaoMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public List<TransacaoGetDTO> getAllTransacoes(){
		return repo.findAll().stream().map(x -> mapper.transacaoToGetDto(x)).toList();
	}
	
	public TransacaoGetDTO getTransacaoById(UUID id) {
		return mapper.transacaoToGetDto(repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
	}
	
	public TransacaoGetDTO doTransacao(TransacaoPostDTO dto) {
		Transacao transacao = mapper.postDtoToTransacao(dto);
		return mapper.transacaoToGetDto(repo.save(transacao));
	}
	
}
