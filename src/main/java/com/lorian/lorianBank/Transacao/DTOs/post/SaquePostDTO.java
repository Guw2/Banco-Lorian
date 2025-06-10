package com.lorian.lorianBank.transacao.DTOs.post;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotNull;

public class SaquePostDTO implements TransacaoPostDTO{

	// Informações que vão ser coletadas do client através de Requests
	
	@NotNull Double valor;
	@NotNull Long conta_id;
	
	@Override
	public Double getValor() {
		return valor;
	}

	@Override
	public void setValor(Double valor) {
		this.valor = valor; 
	}
	
	public Long getConta_id() {
		return conta_id;
	}

	@Override
	public TipoTransacao getTipo() {
		return TipoTransacao.SAQUE;
	}

	
	
}
