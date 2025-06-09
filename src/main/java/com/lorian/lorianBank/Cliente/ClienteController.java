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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteGetDTO>> getAll(){
		return new ResponseEntity<List<ClienteGetDTO>>(service.getAllClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<ClienteGetDTO> getByEmail(@PathVariable String email){
		return new ResponseEntity<ClienteGetDTO>(service.getClienteByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClienteGetDTO> insert(@RequestBody @Valid ClientePostDTO dto){
		return new ResponseEntity<ClienteGetDTO>(service.insertCliente(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<?> delete(@PathVariable String email){
		service.deleteClienteByEmail(email);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
