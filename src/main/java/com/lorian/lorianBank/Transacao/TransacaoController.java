package com.lorian.lorianBank.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.transacao.DTOs.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.TransacaoPostDTO;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	private final TransacaoService service;

	public TransacaoController(TransacaoService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<TransacaoGetDTO> getAll(){
		return service.getAllTransacoes();
	}
	
	@GetMapping("/{id}")
	public TransacaoGetDTO getTransacaoById(@PathVariable UUID id) {
		return service.getTransacaoById(id);
	}
	
	@PostMapping
	public TransacaoGetDTO insertTransacao(@RequestBody TransacaoPostDTO dto) {
		return service.insertTransacao(dto);
	}
	
}
