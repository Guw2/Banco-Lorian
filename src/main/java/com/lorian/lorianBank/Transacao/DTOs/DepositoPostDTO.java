package com.lorian.lorianBank.transacao.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class DepositoPostDTO implements TransacaoPostDTO{
	
	@NotNull @Max(value = 10000) Double valor;
	@NotNull UUID conta_destino_id;
	
	@Override
	public Double getValor() {
		return valor;
	}
	
	@Override
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public UUID getConta_destino_id() {
		return conta_destino_id;
	}

	@Override
	public TipoTransacao getTipo() {
		return TipoTransacao.DEPOSITO;
	}


	
	
}
