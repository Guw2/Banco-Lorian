package com.lorian.lorianBank.conta.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.conta.TipoConta;

import jakarta.validation.constraints.NotNull;

public class ContaPostDTO {
	
	@NotNull TipoConta tipo;
	@NotNull UUID cliente_id;

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public UUID getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(UUID cliente_id) {
		this.cliente_id = cliente_id;
	}
	
}
