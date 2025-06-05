package com.lorian.lorianBank.cliente.DTOs;

public record ClienteGetDTO(
		String nome,
		Integer idade,
		String telefone,
		String email
		) {

}
