package com.lorian.lorianBank.transacao.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransferenciaPostDTO implements TransacaoPostDTO{

	@NotNull @Max(value = 10000) Double valor;
	@NotBlank @Size(max = 120) String descricao;
	@NotNull UUID conta_id;
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
	
	public UUID getConta_id() {
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
