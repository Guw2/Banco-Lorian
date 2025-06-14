package com.lorian.lorianBank.security.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.security.jwt.JWTService;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordLoginPostDTO;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

import jakarta.persistence.Column;

@Service
public class UserService {

	private final UserRepository user_repo;
	private final ClienteRepository cliente_repo;
	private final AuthenticationManager manager;
	private final PasswordEncoder encoder;
	private final JWTService jwtService;
	private final ClienteMapper cliente_mapper;

	// Constructor Injection
	public UserService(UserRepository user_repo, ClienteRepository cliente_repo, AuthenticationManager manager,
			PasswordEncoder encoder, JWTService jwtService, ClienteMapper cliente_mapper) {
		this.user_repo = user_repo;
		this.cliente_repo = cliente_repo;
		this.manager = manager;
		this.encoder = encoder;
		this.jwtService = jwtService;
		this.cliente_mapper = cliente_mapper;
	}

	// Operação de registro de usuário no banco de dados + encode de senha
	public String register(UserRecordRegisterPostDTO dto) {
		// Cria um novo usuário
		User user = new User();
		
		// Transfere dados do DTO para o objeto
		user.setUsername(dto.username());
		user.setPassword(encoder.encode(dto.password()));
		user.setRole(dto.role());
		
		// Cria um novo cliente com os dados do dto
		Cliente cliente = cliente_mapper.postDtoToCliente(dto.cliente());
		
		// Associa o cliente
		user.setCliente(cliente);
		
		// Tratamento de exceção caso dê algum problema na hora de persistir o usuário
		try {
			// Persistindo o cliente e o usuário
			cliente_repo.save(cliente);
			user_repo.save(user);
			//Mensagem de sucesso
			return "Usuário cadastrado com sucesso!";
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Operação de login
	public String login(UserRecordLoginPostDTO dto) {
		// Tratamento de exceção caso dê algum problema na hora de logar com username e password
		try {
			// Autenticando o usuário
			Authentication usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
			Authentication auth = manager.authenticate(usernamePassword);
			// Gerando o Token JWT
			String token = jwtService.generateJwtToken((User) auth.getPrincipal());
			// Mensagem de sucesso + Token JWT
			return "Usuário logado com sucesso!\nJWT = " + token;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
