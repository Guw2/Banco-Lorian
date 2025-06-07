package com.lorian.lorianBank.transacao.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.transacao.TipoTransacao;

public record TransacaoPostDTO(Double valor,
		TipoTransacao tipo,
		String descricao,
		UUID conta_id,
		UUID conta_destino_id) {

}
