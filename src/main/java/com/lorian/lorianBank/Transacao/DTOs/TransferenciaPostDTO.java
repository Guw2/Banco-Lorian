package com.lorian.lorianBank.transacao.DTOs;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransferenciaPostDTO implements TransacaoPostDTO{

	@NotNull Double valor;
	@NotBlank @Size(max = 120) String descricao;
	@NotNull Long conta_id;
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
	
	public Long getConta_id() {
		return conta_id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public TipoTransacao getTipo() {
		return TipoTransacao.TRANSFERENCIA;
	}
	
}
