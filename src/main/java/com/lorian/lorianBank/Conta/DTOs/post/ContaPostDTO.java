package com.lorian.lorianBank.conta.DTOs.post;

import com.lorian.lorianBank.conta.TipoConta;

import jakarta.validation.constraints.NotNull;

public class ContaPostDTO {
	
	@NotNull TipoConta tipo;
	@NotNull Long cliente_id;

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	
}
