package com.lorian.lorianBank.security.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.security.user.DTOs.post.UserRecordLoginPostDTO;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Controller de Autenticação de Usuário", description = "Endpoints de Login e Registro")
public class UserAuthController {

	private final UserService service;
	
	// Contructor Injection
	public UserAuthController(UserService service) {
		this.service = service;
	}

	@Operation(summary = "Registrar um usuário", description = "Persiste um usuário no banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso!"),
			@ApiResponse(responseCode = "400", description = "Ocorreu um erro ao registrar o usuário")
	})
	@PostMapping("/register") // Endpoint de registro de usuário
	public String register(@RequestBody UserRecordRegisterPostDTO dto){
		// Chama o método de registro da camada de serviço
		return service.register(dto);
	}
	
	@Operation(summary = "Logar um usuário", description = "Faz o login de um usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário logado com sucesso! + JWT Token"),
			@ApiResponse(responseCode = "400", description = "Ocorreu um erro no login")
	})
	@PostMapping("/login") // Endpoint de login de usuário
	public String login(@RequestBody UserRecordLoginPostDTO dto) {
		// Chama o método de login da camada de serviço
		return service.login(dto);
	}
	
}
