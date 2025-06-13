package com.lorian.lorianBank.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import com.lorian.lorianBank.cliente.Cliente;



public interface UserRepository extends JpaRepository<User, Long>{

	Optional<UserDetails> findByUsername(String username);
	Optional<UserDetails> findByCliente(Cliente cliente);
}
