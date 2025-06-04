package com.lorian.lorianBank.Cliente.DTOs;

public record ClientePostDTO(
		String nome,
		String cpf,
		Integer idade,
		String endereco,
		String telefone,
		String email
		) {

}
