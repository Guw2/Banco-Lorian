package com.lorian.lorianBank.conta.factory;

import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.TipoConta;

public interface ContaFactory {
	
	Conta generate(TipoConta tipo, Long cliente_id);

}
