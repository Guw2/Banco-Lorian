package com.lorian.lorianBank.security.user.authenticatedUserOperations;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoUserPostDTO;
import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.post.ContaUserPostDTO;
import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.post.DepositoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoCartaoDeCreditoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoFaturaPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.SaquePostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransferenciaPostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/myuser")
public class UserInfoController {

	private final UserOpsService service;
	
	// Constructor Injection
	public UserInfoController(UserOpsService service) {
		this.service = service;
	}

	@Operation(summary = "Buscar informações do cliente")
	@ApiResponse(responseCode = "200", description = "Cliente encontrado")
	@GetMapping("/cliente") // Busca as informações do cliente associado ao usuário autenticado
	public ResponseEntity<ClienteGetDTO> getClienteInfo(){
		return new ResponseEntity<ClienteGetDTO>(service.getClienteInfo(), HttpStatus.OK);
	}
	
	@Operation(summary = "Buscar contas do cliente")
	@ApiResponse(responseCode = "200", description = "Contas listadas")
	@GetMapping("/contas") // Busca as informações das contas associadas ao usuário autenticado
	public ResponseEntity<List<ContaGetDTO>> getContasInfo(){
		return new ResponseEntity<List<ContaGetDTO>>(service.getContasInfo(), HttpStatus.OK);
	}
	
	@Operation(summary = "Abrir uma nova conta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Conta aberta com sucesso"),
			@ApiResponse(responseCode = "400", description = "Ocorreu um erro ao abrir a conta")
	})
	@PostMapping("/contas/abrir") // Abre uma nova conta e associa automaticamente ao usuário autenticado
	public ResponseEntity<ContaGetDTO> abrirConta(@RequestBody ContaUserPostDTO dto){
		return new ResponseEntity<ContaGetDTO>(service.abrirConta(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar cartões do cliente")
	@ApiResponse(responseCode = "200", description = "Cartões listados")
	@GetMapping("/cartoes") // Busca os cartões associados ao usuário autenticado
	public ResponseEntity<List<CartaoGetDTO>> getCartoesInfo(){
		return new ResponseEntity<List<CartaoGetDTO>>(service.getCartoesInfo(), HttpStatus.OK);
	}
	
	@Operation(summary = "Buscar cartões do cliente por número da conta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cartões listados"),
			@ApiResponse(responseCode = "404", description = "Número de conta não encontrado")
	})
	@GetMapping("/cartoes/{numero_da_conta}") // Busca os cartões associados a uma conta do usuário autenticado
	public ResponseEntity<List<CartaoGetDTO>> getCartoesInfoByNumeroDaConta(@PathVariable Long numero_da_conta){
		return new ResponseEntity<List<CartaoGetDTO>>(service.getCartoesByNumeroDaConta(numero_da_conta), HttpStatus.OK);
	}
	
	@Operation(summary = "Emitir um novo cartão")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cartão emitido"),
			@ApiResponse(responseCode = "400", description = "Erro ao emitir o cartão")
	})
	@PostMapping("/cartoes/emitir") // Emite um novo cartão e associa automaticamente ao usuário autenticado
	public ResponseEntity<CartaoGetDTO> emitirCartao(@RequestBody CartaoUserPostDTO dto){
		return new ResponseEntity<CartaoGetDTO>(service.emitirCartao(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Transferir dinheiro para outra conta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Transferência concluída com sucesso"),
			@ApiResponse(responseCode = "406", description = "Erro de lógica no momento da transferência (Ex: A conta remetente não pertence ao usuário, tentativa de transferir dinheiro para a mesma conta, etc.)"),
			@ApiResponse(responseCode = "400", description = "Erro de transferência")
	})
	@PostMapping("/transacoes/transferir") // Transfere dinheiro para outra conta
	public ResponseEntity<TransacaoGetDTO> transferir(@RequestBody TransferenciaPostDTO dto){
		return new ResponseEntity<TransacaoGetDTO>(service.doTransacao(dto), HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "Depositar dinheiro em uma das contas do usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Depósito feito com sucesso"),
			@ApiResponse(responseCode = "406", description = "Erro de lógica no momento do depósito (Ex: Tentar depositar numa conta não pertencente ao usuário)"),
			@ApiResponse(responseCode = "400", description = "Erro de depósito")
	})
	@PostMapping("/transacoes/depositar") // Deposita dinheiro em uma das contas do usuário autenticado
	public ResponseEntity<TransacaoGetDTO> depositar(@RequestBody DepositoPostDTO dto){
		return new ResponseEntity<TransacaoGetDTO>(service.doTransacao(dto), HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "Sacar dinheiro de uma das contas do usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Saque feito com sucesso"),
			@ApiResponse(responseCode = "406", description = "Erro de lógica no momento do saque (Ex: Tentar sacar dinheiro de uma conta não pertencente ao usuário)"),
			@ApiResponse(responseCode = "400", description = "Erro de saque")
	})
	@PostMapping("/transacoes/sacar") // Saca dinheiro de uma das contas do usuário autenticado
	public ResponseEntity<TransacaoGetDTO> sacar(@RequestBody SaquePostDTO dto){
		return new ResponseEntity<TransacaoGetDTO>(service.doTransacao(dto), HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "Transferir dinheiro para outra conta via crédito")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Transferência concluída com sucesso"),
			@ApiResponse(responseCode = "406", description = "Erro de lógica no momento da transferência (Ex: O cartão não pertence ao usuário ou não foi ativado, tentativa de transferir dinheiro para a mesma conta, etc.)"),
			@ApiResponse(responseCode = "400", description = "Erro de transferência")
	})
	@PostMapping("/transacoes/transferir-cartao") // Transfere dinheiro para outra conta por crédito
	public ResponseEntity<TransacaoGetDTO> transferir_cartao(@RequestBody PagamentoCartaoDeCreditoPostDTO dto){
		return new ResponseEntity<TransacaoGetDTO>(service.doTransacao(dto), HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "Pagar fatura de um dos cartões do usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Fatura paga com sucesso"),
			@ApiResponse(responseCode = "406", description = "Erro de lógica no momento do saque (Ex: Tentar pagar uma fatura não existente, tentar pagar a fatura de um cartão não ativado, etc.)"),
			@ApiResponse(responseCode = "400", description = "Erro no pagamento da fatura")
	})
	@PostMapping("/transacoes/pagar-fatura") // Paga a fatura de um dos cartões do usuário autenticado
	public ResponseEntity<TransacaoGetDTO> pagar_fatura(@RequestBody PagamentoFaturaPostDTO dto){
		return new ResponseEntity<TransacaoGetDTO>(service.doTransacao(dto), HttpStatus.ACCEPTED);
	}
}
