package com.lorian.lorianBank.cliente;

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

import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.post.ClientePostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ops/clientes")
@Tag(name = "Controller de Clientes", description = "Operações Relacionadas aos Clientes")
public class ClienteController {

	private final ClienteService service;

	// Constructor Injection
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@Operation(summary = "Listar Todos os Clientes", description = "Retorna a Lista Completa de Clientes")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Clientes Listados")
	})
	@GetMapping // Lista todos os clientes
	public ResponseEntity<List<ClienteGetDTO>> getAll(){
		// Retorna uma lista de ClienteGetDTO já mapeada pela camada de Service
		return new ResponseEntity<List<ClienteGetDTO>>(service.getAllClientes(), HttpStatus.OK);
	}
	
	@Operation(summary = "Buscar Cliente Por E-mail", description = "Retorna Um Cliente A Partir de Seu E-mail")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Cliente Encontrado"),
	    @ApiResponse(responseCode = "404", description = "E-mail Não Encontrado")
	})
	@GetMapping("/{email}") // Mostra um Cliente com um E-mail específico
	public ResponseEntity<ClienteGetDTO> getByEmail(@PathVariable String email){
		// Retorna um ClienteGetDTO já mapeado pela camada de Service
		return new ResponseEntity<ClienteGetDTO>(service.getClienteByEmail(email), HttpStatus.OK);
	}
	
	@Operation(summary = "Inserir Novo Cliente", description = "Insere um Novo Cliente")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Cliente Inserido")
	})
	@PostMapping // Insere um Cliente
	public ResponseEntity<ClienteGetDTO> insert(@RequestBody @Valid ClientePostDTO dto){
		// Retorna um ClienteGetDTO já mapeado pela camada de Service com o status CREATED
		return new ResponseEntity<ClienteGetDTO>(service.insertCliente(dto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Deletar Cliente Por E-mail", description = "Deleta Um Cliente A Partir de Seu E-mail")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "Cliente Deletado"),
	    @ApiResponse(responseCode = "404", description = "E-mail Não Encontrado")
	})
	@DeleteMapping("/{email}") // Deleta um Cliente
	public ResponseEntity<?> delete(@PathVariable String email){
		// Executa a operação
		service.deleteClienteByEmail(email);
		// Retorna um no content
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
