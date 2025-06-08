package com.lorian.lorianBank.cartao.DTOs;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class CartaoPostDTO {

	@NotNull private UUID cliente_id;
	@NotNull private UUID conta_id;

	public UUID getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(UUID cliente_id) {
		this.cliente_id = cliente_id;
	}

	public UUID getConta_id() {
		return conta_id;
	}

	public void setConta_id(UUID conta_id) {
		this.conta_id = conta_id;
	}
	
}
