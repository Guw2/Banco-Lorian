package com.lorian.lorianBank.transacao.DTOs;

import java.util.UUID;

import com.lorian.lorianBank.transacao.TipoTransacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TransacaoPostDTO(@NotNull Double valor,
		@NotNull TipoTransacao tipo,
		@NotBlank @Size(max = 120) String descricao,
		@NotNull UUID conta_id,
		@NotNull UUID conta_destino_id) {

}
