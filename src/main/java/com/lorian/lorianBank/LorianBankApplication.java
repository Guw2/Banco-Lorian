package com.lorian.lorianBank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteRepository;
import com.lorian.lorianBank.security.user.User;
import com.lorian.lorianBank.security.user.UserRepository;
import com.lorian.lorianBank.security.user.UserRole;

@SpringBootApplication
public class LorianBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(LorianBankApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository user_repo, ClienteRepository cliente_repo, PasswordEncoder encoder) {
		return args -> {
			if(user_repo.findByUsername("admin").isEmpty()) {
				
				Cliente cliente = new Cliente();
				cliente.setNome("admin");
				cliente.setCpf("N/A");
				cliente.setIdade(0);
				cliente.setEndereco("N/A");
				cliente.setTelefone("N/A");
				cliente.setEmail("admin@admin.com");
				cliente_repo.save(cliente);
				
				User user = new User();
				user.setUsername("admin");
				user.setPassword(encoder.encode("admin123"));
				user.setRole(UserRole.ADMIN);
				user.setCliente(cliente);
				user_repo.save(user);
			}
		};
	}

}
