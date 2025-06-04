package com.lorian.lorianBank.Cliente;

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

import com.lorian.lorianBank.Cliente.DTOs.ClienteGetDto;
import com.lorian.lorianBank.Cliente.DTOs.ClientePostDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteGetDto>> getAll(){
		return new ResponseEntity<List<ClienteGetDto>>(service.getAllClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<ClienteGetDto> getByEmail(@PathVariable String email){
		return new ResponseEntity<ClienteGetDto>(service.getClienteByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClienteGetDto> insert(@RequestBody ClientePostDTO dto){
		return new ResponseEntity<ClienteGetDto>(service.insertCliente(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<?> delete(@PathVariable String email){
		service.deleteClienteByEmail(email);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
