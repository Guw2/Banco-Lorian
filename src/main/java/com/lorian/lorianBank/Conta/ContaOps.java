package com.lorian.lorianBank.conta;

public interface ContaOps {

	// Operações obrigatórias para a entidade conta
	// Essa interface foi criada para mais legibilidade do código
	
	void debitar(Double valor);
	void creditar(Double valor);
}
