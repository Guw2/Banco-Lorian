package com.lorian.lorianBank.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lorian.lorianBank.security.user.User;

@Service
public class JWTService {
	
	// Chave secreta para geração do Token
	private final String secretKey = "lorian_bank";
	
	// Método de geração de Token JWT
	public String generateJwtToken(User user) {
		try {
			// Definindo um algoritmo de hash
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			// Gerando o Token
			String token = JWT
					.create()
					.withIssuer("lorian_bank")
					.withSubject(user.getUsername())
					.withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
					.sign(algorithm);
			// Retornando o Token
			return token;
		}catch(JWTCreationException e) {
			// Em caso de algo dar errado, uma exceção é lançada
			throw new RuntimeException(e);
		}
	}
	
	// Método de validação de Token JWT
	public String validateJwtToken(String token) {
		try {
			// Definindo um algoritmo de hash
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			// Validando o Token e armazenando o username do emissor
			String username = JWT.require(algorithm)
					.withIssuer("lorian_bank")
					.build()
					.verify(token)
					.getSubject();
			// Retornando o username 
			return username;
		}catch(JWTVerificationException e) {
			// Em caso de algo dar errado, uma exceção é lançada
			throw new RuntimeException(e);
		}
	}
	
}
