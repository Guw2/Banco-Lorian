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

import com.lorian.lorianBank.cartao.DTOs.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.CartaoPostDTO;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	private final CartaoService service;

	public CartaoController(CartaoService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CartaoGetDTO>> getAll(){
		return new ResponseEntity<List<CartaoGetDTO>>(service.getAllCartoes(), HttpStatus.OK);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<CartaoGetDTO> getByNumero(@PathVariable String numero){
		return new ResponseEntity<CartaoGetDTO>(service.getCartaoByNumero(numero), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CartaoGetDTO> insert(@RequestBody CartaoPostDTO dto){
		return new ResponseEntity<CartaoGetDTO>(service.insertCartao(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> deleteByNumero(@PathVariable String numero){
		service.deleteCartaoByNumero(numero);
		return ResponseEntity.noContent().build();
	}

}
