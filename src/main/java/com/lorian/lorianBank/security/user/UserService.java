package com.lorian.lorianBank.security.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordLoginPostDTO;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

@Service
public class UserService {

	private final UserRepository user_repo;
	private final ClienteRepository cliente_repo;
	private final AuthenticationManager manager;
	private final PasswordEncoder encoder;

	
	// Constructor Injection
	public UserService(UserRepository user_repo, ClienteRepository cliente_repo, AuthenticationManager manager,
			PasswordEncoder encoder) {
		this.user_repo = user_repo;
		this.cliente_repo = cliente_repo;
		this.manager = manager;
		this.encoder = encoder;
	}

	// Operação de registro de usuário no banco de dados + encode de senha
	public String register(UserRecordRegisterPostDTO dto) {
		// Cria um novo usuário
		User user = new User();
		
		// Transfere dados do DTO para o objeto
		user.setUser(dto.username());
		user.setPass(encoder.encode(dto.password()));
		user.setRole(dto.role());
		
		// Busca o cliente associado ao usuário por ID para fazer verificações
		Cliente cliente = cliente_repo.findById(dto.cliente_id()).get();
		
		// Verifica se esse cliente já foi associado a outro usuário
		if(user_repo.findByCliente(cliente).isPresent())
			throw new RuntimeException("O cliente de id " + cliente.getId() + " já é um usuário.");
		
		// Associa o cliente
		user.setCliente(cliente);
		
		// Tratamento de exceção caso dê algum problema na hora de persistir o usuário
		try {
			// Persistindo o usuário
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
			// Mensagem de sucesso
			return "Usuário logado com sucesso!";
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
