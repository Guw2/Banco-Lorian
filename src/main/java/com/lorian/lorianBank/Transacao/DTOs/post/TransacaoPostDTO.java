package com.lorian.lorianBank.transacao.DTOs.post;

import com.lorian.lorianBank.transacao.TipoTransacao;

public interface TransacaoPostDTO {

	// Métodos obrigatórios em comum para todos os PostDTOs desse pacote
	
	Double getValor();
	void setValor(Double valor);
	TipoTransacao getTipo();
}
