package com.lorian.lorianBank.cliente.DTOs;

public record ClientePostDTO(
		String nome,
		String cpf,
		Integer idade,
		String endereco,
		String telefone,
		String email
		) {

}
