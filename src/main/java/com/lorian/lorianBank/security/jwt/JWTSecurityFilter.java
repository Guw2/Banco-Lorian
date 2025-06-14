package com.lorian.lorianBank.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lorian.lorianBank.security.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTSecurityFilter extends OncePerRequestFilter{

	private final JWTService service;
	private final UserRepository repo;
	
	// Constructor Injection
	public JWTSecurityFilter(JWTService service, UserRepository repo) {
		this.service = service;
		this.repo = repo;
	}

	@Override // Implementação do Método doFilterInternal de OncePerRequestFilter
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Armazenando o Token do cabeçalho através do método privado recoverToken
		String token = recoverToken(request);
		// Verifica se o Token é nulo
		if(token != null) {
			// Caso não seja nulo, as operaçõoes a seguir são executadas
			
			// Armazena o subject (definido como nome de usuário no JWTService)
			String subject = service.validateJwtToken(token);
			// Busca o usuário pelo username e armazena na variável 'user'
			UserDetails user = repo.findByUsername(subject)
					.orElseThrow(() -> new UsernameNotFoundException("Este usuário não foi cadastrado."));
			
			// Criando um objeto de autenticação com as informações do usuário
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			// Armazenando o objeto de autenticação no contexto de segurança da aplicação
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		// Avança
		filterChain.doFilter(request, response);
	}
	
	// Método para recuperar o Token do cabeçalho de requisição
	private String recoverToken(HttpServletRequest request) {
		// Armazena o conteúdo de 'Authorization' na variável
		String header = request.getHeader("Authorization");
		if(header == null) return null; // Verfica se o conteúdo armazenado não é um valor nulo
		// Retorna uma String sem o 'Bearer '
		return header.replaceAll("Bearer ", "");
	}

}
