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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ops/transacoes")
@Tag(name = "Controller de Transações", description = "Operações Relacionadas às Transações")
public class TransacaoController {
	
	private final TransacaoService service;

	// Constructor Injection
	public TransacaoController(TransacaoService service) {
		this.service = service;
	}
	
	@Operation(summary = "Listar Todas as Transações", description = "Retorna a Lista Completa de Transações")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Transações Listadas")
	})
	@GetMapping // Lista todas as transações
	public List<TransacaoGetDTO> getAll(){
		// Retorna uma lista de TrnasacaoGetDTO direto
		return service.getAllTransacoes();
	}
	
	@Operation(summary = "Buscar Transação Por ID", description = "Retorna Uma Transação A Partir de Seu ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Transação Encontrada"),
	    @ApiResponse(responseCode = "404", description = "ID Não Encontrado")
	})
	@GetMapping("/{id}") // Mostra uma transação com um ID específico
	public TransacaoGetDTO getTransacaoById(@PathVariable Long id) {
		// Retorna um TransacaoGetDTO direto
		return service.getTransacaoById(id);
	}
	
	@Operation(summary = "Operação de Saque", description = "Saca Uma Quantidade X de Dinheiro de Uma Conta")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Saque Feito"),
	    @ApiResponse(responseCode = "406", description = "Erro na Transação")
	})
	@PostMapping("/sacar") // Operação de sacar dinheiro para uma conta
	public TransacaoGetDTO insertTransacaoSaque(@RequestBody @Valid SaquePostDTO dto) {
		// Realiza a operação e retorna um TransacaoGetDTO direto
		return service.doTransacao(dto);
	}
	
	@Operation(summary = "Operação de Depósito", description = "Deposita Uma Quantidade X de Dinheiro em Uma Conta")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Depósito Feito"),
	    @ApiResponse(responseCode = "406", description = "Erro na Transação")
	})
	@PostMapping("/depositar") // Operação de depositar dinheiro na conta
	public TransacaoGetDTO insertTransacaoDeposito(@RequestBody @Valid DepositoPostDTO dto) {
		// Realiza a operação e retorna um TransacaoGetDTO direto
		return service.doTransacao(dto);
	}
	
	@Operation(summary = "Operação de Transferência", description = "Transfere Uma Quantidade X de Dinheiro de Uma Conta Pra Outra")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Transferência Feita"),
	    @ApiResponse(responseCode = "406", description = "Erro na Transação")
	})
	@PostMapping("/transferir") // Operação de transferir dinheiro de uma conta à outra
	public TransacaoGetDTO insertTransacaoTransferencia(@RequestBody @Valid TransferenciaPostDTO dto) {
		// Realiza a operação e retorna um TransacaoGetDTO direto
		return service.doTransacao(dto);
	}
	
	@Operation(summary = "Operação de Transferência Com Cartão de Crédito", description = "Transfere Uma Quantidade X de Dinheiro de Uma Conta Pra Outra Via Cartão de Crédito Com Taxa")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Transferência Feita"),
	    @ApiResponse(responseCode = "406", description = "Erro na Transação")
	})
	@PostMapping("/credito") // Operação de transferir dinheiro de uma conta à outra via cartão de crédito
	public TransacaoGetDTO insertTransacaoCartaoDeCredito(@RequestBody @Valid PagamentoCartaoDeCreditoPostDTO dto) {
		// Realiza a operação e retorna um TransacaoGetDTO direto
		return service.doTransacao(dto);
	}
	
	@Operation(summary = "Operação de Pagamento de Fatura de Cartão de Crédito", description = "Paga a Fatura de Um Cartão de Crédito")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Pagamento Feito"),
	    @ApiResponse(responseCode = "406", description = "Erro na Transação")
	})
	@PostMapping("/credito/fatura") // Operação de pagar a fatura de um cartão de crédito
	public TransacaoGetDTO insertTransacaoFatura(@RequestBody @Valid PagamentoFaturaPostDTO dto) {
		// Realiza a operação e retorna um TransacaoGetDTO direto
		return service.doTransacao(dto);
	}
	
}
