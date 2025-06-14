package com.lorian.lorianBank.conta.DTOs.post;

import com.lorian.lorianBank.conta.TipoConta;

public record ContaUserPostDTO(TipoConta tipo) {

	// Record simples usado pra coletar apenas o tipo de conta que o usuário quer abrir
	// Utilizado em momentos em que o usuário autenticado quer abrir uma conta para o cliente associado
	
}
