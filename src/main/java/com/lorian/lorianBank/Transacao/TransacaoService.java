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

	public TransacaoService(TransacaoRepository repo) {
		this.repo = repo;
	}
	
	public List<TransacaoGetDTO> getAllTransacoes(){
		return repo.findAll().stream().map(x -> TransacaoMapper.transacaoToGetDto(x)).toList();
	}
	
	public TransacaoGetDTO getTransacaoById(UUID id) {
		return TransacaoMapper.transacaoToGetDto(repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
	}
	
	public TransacaoGetDTO insertTransacao(TransacaoPostDTO dto) {
		return TransacaoMapper.transacaoToGetDto(repo.save(TransacaoMapper.postDtoToTransacao(dto)));
	}
	
}
