package com.lorian.lorianBank.Conta;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.Conta.DTOs.ContaGetDTO;
import com.lorian.lorianBank.Conta.DTOs.ContaPostDTO;

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
	
	@PostMapping
	public ResponseEntity<ContaGetDTO> insert(@RequestBody ContaPostDTO dto){
		return new ResponseEntity<ContaGetDTO>(service.insertConta(dto), HttpStatus.CREATED);
	}

}
