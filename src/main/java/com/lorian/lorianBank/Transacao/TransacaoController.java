package com.lorian.lorianBank.transacao;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.post.DepositoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoCartaoDeCreditoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoFaturaPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.SaquePostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransferenciaPostDTO;

import jakarta.validation.Valid;

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
	public TransacaoGetDTO getTransacaoById(@PathVariable Long id) {
		return service.getTransacaoById(id);
	}
	
	@PostMapping("/sacar")
	public TransacaoGetDTO insertTransacaoSaque(@RequestBody @Valid SaquePostDTO dto) {
		return service.doTransacao(dto);
	}
	
	@PostMapping("/depositar")
	public TransacaoGetDTO insertTransacaoDeposito(@RequestBody @Valid DepositoPostDTO dto) {
		return service.doTransacao(dto);
	}
	
	@PostMapping("/transferir")
	public TransacaoGetDTO insertTransacaoTransferencia(@RequestBody @Valid TransferenciaPostDTO dto) {
		return service.doTransacao(dto);
	}
	
	@PostMapping("/credito")
	public TransacaoGetDTO insertTransacaoCartaoDeCredito(@RequestBody @Valid PagamentoCartaoDeCreditoPostDTO dto) {
		return service.doTransacao(dto);
	}
	
	@PostMapping("/credito/fatura")
	public TransacaoGetDTO insertTransacaoFatura(@RequestBody @Valid PagamentoFaturaPostDTO dto) {
		return service.doTransacao(dto);
	}
	
}
