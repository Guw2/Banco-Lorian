package com.lorian.lorianBank.security.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.security.user.DTOs.post.UserRecordLoginPostDTO;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	private final UserService service;
	
	// Contructor Injection
	public UserAuthController(UserService service) {
		this.service = service;
	}

	@PostMapping("/register") // Endpoint de registro de usuário
	public String register(@RequestBody UserRecordRegisterPostDTO dto){
		// Chama o método de registro da camada de serviço
		return service.register(dto);
	}
	
	@PostMapping("/login") // Endpoint de login de usuário
	public String login(@RequestBody UserRecordLoginPostDTO dto) {
		// Chama o método de login da camada de serviço
		return service.login(dto);
	}
	
}
