package com.lorian.lorianBank.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository repo;
	
	// Constructor Injection
	public UserDetailsServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override // Implementação do método de UserDetailsService para identificar o Username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Busca um usuário por ser Username (User)
		return repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Este nome de usuário não existe."));
	}

}
