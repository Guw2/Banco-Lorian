package com.lorian.lorianBank.transacao.DTOs;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class SaquePostDTO implements TransacaoPostDTO{

	@NotNull @Max(value = 10000) Double valor;
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
