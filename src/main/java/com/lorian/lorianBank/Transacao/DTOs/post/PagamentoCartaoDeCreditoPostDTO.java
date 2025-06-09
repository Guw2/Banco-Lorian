package com.lorian.lorianBank.transacao.DTOs.post;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PagamentoCartaoDeCreditoPostDTO implements TransacaoPostDTO{

	@NotNull private Double valor;
	@NotNull private Long cartao_id;
	@NotNull private Long conta_destino_id;
	@NotBlank private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCartao_id() {
		return cartao_id;
	}

	public void setCartao_id(Long cartao_id) {
		this.cartao_id = cartao_id;
	}

	public Long getConta_destino_id() {
		return conta_destino_id;
	}

	public void setConta_destino_id(Long conta_destino_id) {
		this.conta_destino_id = conta_destino_id;
	}

	@Override
	public Double getValor() {
		return valor;
	}

	@Override
	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public TipoTransacao getTipo() {
		return TipoTransacao.CARTAO_DE_CREDITO;
	}
	
	

}
