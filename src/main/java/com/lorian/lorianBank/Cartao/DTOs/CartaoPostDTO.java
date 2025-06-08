package com.lorian.lorianBank.cartao.DTOs;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CartaoPostDTO(
		@NotNull UUID cliente_id,
		@NotNull UUID conta_id) {

}
