package com.lorian.lorianBank.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository repo;
	
	public UserDetailsServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByUser(username)
				.orElseThrow(() -> new UsernameNotFoundException("Este nome de usuário não existe."));
	}

}
