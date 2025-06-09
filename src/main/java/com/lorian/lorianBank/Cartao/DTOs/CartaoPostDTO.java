package com.lorian.lorianBank.cartao.DTOs;

import jakarta.validation.constraints.NotNull;

public class CartaoPostDTO {

	@NotNull private Long cliente_id;
	@NotNull private Long conta_id;

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Long getConta_id() {
		return conta_id;
	}

	public void setConta_id(Long conta_id) {
		this.conta_id = conta_id;
	}
	
}
