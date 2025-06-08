package com.lorian.lorianBank.transacao.DTOs;

import com.lorian.lorianBank.transacao.TipoTransacao;

public interface TransacaoPostDTO {

	Double getValor();
	void setValor(Double valor);
	TipoTransacao getTipo();
}
