package com.lorian.lorianBank.cartao.DTOs.post;

import jakarta.validation.constraints.NotNull;

public record CartaoUserPostDTO(@NotNull Long numero_da_conta) {

	// Record simples usado pra coletar apenas o número da conta que o cartão vai ser associado
	// Utilizado em momentos em que o usuário autenticado quer emitir um cartão
}
