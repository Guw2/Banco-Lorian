package com.lorian.lorianBank.cartao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoPostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ops/cartoes")
@Tag(name = "Controller de Cartões", description = "Operações Relacionadas aos Cartões")
public class CartaoController {
	
	private final CartaoService service;

	
	// Constructor Injection
	public CartaoController(CartaoService service) {
		this.service = service;
	}
	
	@Operation(summary = "Listar Todos os Cartões", description = "Retorna a Lista Completa de Cartões")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Cartões Listados")
	})
	@GetMapping // Lista todos os Cartões registrados
	public ResponseEntity<List<CartaoGetDTO>> getAll(){
		// Retorna uma lista de CartaoGetDTO já convertidos pela camada de Service
		return new ResponseEntity<List<CartaoGetDTO>>(service.getAllCartoes(), HttpStatus.OK);
	}
	
	@Operation(summary = "Buscar Cartão Por Número", description = "Retorna Um Cartão A Partir de Seu Número")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Cartão Encontrado"),
	    @ApiResponse(responseCode = "404", description = "Número Não Encontrado")
	})
	@GetMapping("/{numero}") // Mostra o Cartão com o número informado no path
	public ResponseEntity<CartaoGetDTO> getByNumero(@PathVariable String numero){
		// Retorna um CartaoGetDTO já convertido pela camada de Service
		return new ResponseEntity<CartaoGetDTO>(service.getCartaoByNumero(numero), HttpStatus.OK);
	}
	
	@Operation(summary = "Inserir Um Cartão", description = "Emite um Novo Cartão")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Cartão Inserido")
	})
	@PostMapping // Emite um novo cartão
	public ResponseEntity<CartaoGetDTO> insert(@RequestBody @Valid CartaoPostDTO dto){
		// Retorna um CartaoGetDTO já convertido pela camada de Service com o status de CREATED
		return new ResponseEntity<CartaoGetDTO>(service.insertCartao(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Deletar Cartão Por Número", description = "Deleta Um Cartão A Partir de Seu Número")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "Cartão Deletado"),
	    @ApiResponse(responseCode = "404", description = "Número Não Encontrado")
	})
	@DeleteMapping("/{numero}") // Deleta um cartão
	public ResponseEntity<?> deleteByNumero(@PathVariable String numero){
		// Executa a operação
		service.deleteCartaoByNumero(numero);
		// Retorna um no content
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Ativar Cartão", description = "Ativa um Cartão A Partir de Seu ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Cartão Ativado"),
	    @ApiResponse(responseCode = "404", description = "ID Não Encontrado")
	})
	@GetMapping("/ativar/{id}") // Ativa um cartão pelo ID
	public String ativarCartao(@PathVariable Long id) {
		// Retorna uma String informando que o cartão foi ativado
		return service.ativarCartaoById(id);
	}

}
