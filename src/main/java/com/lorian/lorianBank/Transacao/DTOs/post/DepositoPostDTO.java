package com.lorian.lorianBank.transacao.DTOs.post;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotNull;

public class DepositoPostDTO implements TransacaoPostDTO{
	
	@NotNull Double valor;
	@NotNull Long conta_destino_id;
	
	@Override
	public Double getValor() {
		return valor;
	}
	
	@Override
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Long getConta_destino_id() {
		return conta_destino_id;
	}

	@Override
	public TipoTransacao getTipo() {
		return TipoTransacao.DEPOSITO;
	}


	
	
}
