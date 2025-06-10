package com.lorian.lorianBank.conta;

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

import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.post.ContaPostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
@Tag(name = "Controller de Contas", description = "Operações Relacionadas às Contas")
public class ContaController {
	
	private final ContaService service;

	// Constructor Injection
	public ContaController(ContaService service) {
		this.service = service;
	}
	
	@Operation(summary = "Listar Todas as Contas", description = "Retorna a Lista Completa de Contas")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Contas Listadas")
	})
	@GetMapping // Lista todas as contas
	public ResponseEntity<List<ContaGetDTO>> getAll(){
		// Retorna uma lista de ContaGetDTO já mapeada pela camada de Service
		return new ResponseEntity<List<ContaGetDTO>>(service.getAllContas(), HttpStatus.OK);
	}
	
	@Operation(summary = "Buscar Conta Por Número", description = "Retorna Uma Conta A Partir de Seu Número")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Conta Encontrada"),
	    @ApiResponse(responseCode = "404", description = "Número Não Encontrado")
	})
	@GetMapping("/{numero}") // Mostra uma conta com o número informado
	public ResponseEntity<ContaGetDTO> getByNumero(@PathVariable Long numero){
		// Retorna um ContaGetDTO já mapeado pela camada de Service
		return new ResponseEntity<ContaGetDTO>(service.getContaByNumero(numero), HttpStatus.OK);
	}
	
	@Operation(summary = "Abrir Uma Conta", description = "Abre uma Nova Conta")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Conta Aberta")
	})
	@PostMapping // Insere uma conta
	public ResponseEntity<ContaGetDTO> insert(@RequestBody @Valid ContaPostDTO dto){
		// Retorna um ContaGetDTO já mapeado pela camada de Service com o status CREATED
		return new ResponseEntity<ContaGetDTO>(service.insertConta(dto), HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "Deletar Conta Por Número", description = "Deleta Uma Conta A Partir de Seu Número")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "Conta Deletada"),
	    @ApiResponse(responseCode = "404", description = "Número Não Encontrado")
	})
	@DeleteMapping("/{numero}") // Deleta uma conta por seu número
	public ResponseEntity<?> deleteByNumero(@PathVariable Long numero) {
		// Executa a operação
		service.deleteContaByNumero(numero);
		// Retorna um no content
		return ResponseEntity.noContent().build();
	}

}
