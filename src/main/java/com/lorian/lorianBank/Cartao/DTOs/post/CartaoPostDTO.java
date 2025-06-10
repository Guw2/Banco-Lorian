package com.lorian.lorianBank.cartao.DTOs.post;

import jakarta.validation.constraints.NotNull;

public class CartaoPostDTO {

	// Informações que vão ser coletadas do client através de Requests
	
	@NotNull private Long conta_id;

	public Long getConta_id() {
		return conta_id;
	}

	public void setConta_id(Long conta_id) {
		this.conta_id = conta_id;
	}
	
}
