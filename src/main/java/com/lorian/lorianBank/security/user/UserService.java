package com.lorian.lorianBank.security.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

@Service
public class UserService {

	private final UserRepository user_repo;
	private final ClienteRepository cliente_repo;
	private final AuthenticationManager manager;
	private final PasswordEncoder encoder;

	public UserService(UserRepository user_repo, ClienteRepository cliente_repo, AuthenticationManager manager,
			PasswordEncoder encoder) {
		this.user_repo = user_repo;
		this.cliente_repo = cliente_repo;
		this.manager = manager;
		this.encoder = encoder;
	}

	public String register(UserRecordRegisterPostDTO dto) {
		User user = new User();
		user.setUser(dto.username());
		user.setPass(encoder.encode(dto.password()));
		user.setRole(dto.role());
		
		Cliente cliente = cliente_repo.findById(dto.cliente_id()).get();
		
		if(user_repo.findByCliente(cliente).isPresent())
			throw new RuntimeException("O cliente de id " + cliente.getId() + " já é um usuário.");
		
		user.setCliente(cliente);
		
		try {
			user_repo.save(user);
			return "Usuário cadastrado com sucesso!";
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
