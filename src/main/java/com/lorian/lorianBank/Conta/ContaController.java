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

import com.lorian.lorianBank.conta.DTOs.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.ContaPostDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	private final ContaService service;

	public ContaController(ContaService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ContaGetDTO>> getAll(){
		return new ResponseEntity<List<ContaGetDTO>>(service.getAllContas(), HttpStatus.OK);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<ContaGetDTO> getByNumero(@PathVariable Long numero){
		return new ResponseEntity<ContaGetDTO>(service.getContaByNumero(numero), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ContaGetDTO> insert(@RequestBody @Valid ContaPostDTO dto){
		return new ResponseEntity<ContaGetDTO>(service.insertConta(dto), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deleteByNumero(@PathVariable Long numero) {
		service.deleteContaByNumero(numero);
		return ResponseEntity.noContent().build();
	}

}
