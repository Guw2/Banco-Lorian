package com.lorian.lorianBank.cartao;

public interface CartaoOps {

	// Operações obrigatórias para a entidade cartão
	// Essa interface foi criada para mais legibilidade do código
	
	Double debitar(Double valor);
	Double creditar(Double valor);
	
}
