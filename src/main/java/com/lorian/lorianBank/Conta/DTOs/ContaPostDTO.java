package com.lorian.lorianBank.conta.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.conta.TipoConta;

import jakarta.validation.constraints.NotNull;

public record ContaPostDTO(
		@NotNull TipoConta tipo,
		@NotNull UUID cliente_id) {

}
