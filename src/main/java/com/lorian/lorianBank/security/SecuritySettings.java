package com.lorian.lorianBank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lorian.lorianBank.security.jwt.JWTSecurityFilter;

@Configuration
@EnableWebSecurity
public class SecuritySettings {

	private final JWTSecurityFilter jwtSecurityFilter;
	
	// Constructor Injection
	public SecuritySettings(JWTSecurityFilter jwtSecurityFilter) {
		this.jwtSecurityFilter = jwtSecurityFilter;
	}

	@Bean // Configurações do Filter Chain
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				// Desabilitar o CSRF
				.csrf(csrf -> csrf.disable())
				// Tornando a sessão Stateless
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Configurando permissões de rotas
				.authorizeHttpRequests(
						request -> request
							.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
							.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
							.requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
							.anyRequest().authenticated())
				.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
				// Buildando tudo
				.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
