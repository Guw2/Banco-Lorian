package com.lorian.lorianBank.security.user.authenticatedUserOperations;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoUserPostDTO;
import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.ContaMapper;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.post.ContaUserPostDTO;
import com.lorian.lorianBank.conta.factory.ContaFactory;
import com.lorian.lorianBank.security.user.User;

@RestController
@RequestMapping("/myuser")
public class UserInfoController {

	private final UserOpsService service;
	
	// Constructor Injection
	public UserInfoController(UserOpsService service) {
		this.service = service;
	}

	@GetMapping("/cliente")
	public ResponseEntity<ClienteGetDTO> getClienteInfo(){
		return new ResponseEntity<ClienteGetDTO>(service.getClienteInfo(), HttpStatus.OK);
	}
	
	@GetMapping("/contas")
	public ResponseEntity<List<ContaGetDTO>> getContasInfo(){
		return new ResponseEntity<List<ContaGetDTO>>(service.getContasInfo(), HttpStatus.OK);
	}
	
	@PostMapping("/contas/abrir")
	public ResponseEntity<ContaGetDTO> abrirConta(@RequestBody ContaUserPostDTO dto){
		return new ResponseEntity<ContaGetDTO>(service.abrirConta(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/cartoes")
	public ResponseEntity<List<CartaoGetDTO>> getCartoesInfo(){
		return new ResponseEntity<List<CartaoGetDTO>>(service.getCartoes(), HttpStatus.OK);
	}
	
	@GetMapping("/cartoes/{numero}")
	public ResponseEntity<List<CartaoGetDTO>> getCartoesInfoByNumeroDaConta(@PathVariable Long numero){
		return new ResponseEntity<List<CartaoGetDTO>>(service.getCartoesByNumeroDaConta(numero), HttpStatus.OK);
	}
	
	@PostMapping("/cartoes/emitir")
	public ResponseEntity<CartaoGetDTO> emitirCartao(@RequestBody CartaoUserPostDTO dto){
		return new ResponseEntity<CartaoGetDTO>(service.emitirCartao(dto), HttpStatus.CREATED);
	}
}
