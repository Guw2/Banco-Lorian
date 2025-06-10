package com.lorian.lorianBank.transacao.DTOs.post;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotNull;

public class PagamentoFaturaPostDTO implements TransacaoPostDTO{

	// Informações que vão ser coletadas do client através de Requests
	
	@NotNull private Double valor;
	@NotNull private Long cartao_id;
	
	public Long getCartao_id() {
		return cartao_id;
	}

	public void setCartao_id(Long cartao_id) {
		this.cartao_id = cartao_id;
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
		return TipoTransacao.PAGAMENTO_FATURA;
	}

}
