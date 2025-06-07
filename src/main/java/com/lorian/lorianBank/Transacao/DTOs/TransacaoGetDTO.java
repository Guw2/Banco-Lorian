package com.lorian.lorianBank.transacao.DTOs;

import java.time.Instant;

import com.lorian.lorianBank.transacao.TipoTransacao;

public record TransacaoGetDTO(Double valor,
		Instant data,
		TipoTransacao tipo,
		Long conta_remetente,
		Long conta_destinatario) {

}
