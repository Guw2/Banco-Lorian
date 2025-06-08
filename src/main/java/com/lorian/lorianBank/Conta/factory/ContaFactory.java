package com.lorian.lorianBank.conta.factory;

import java.util.UUID;

import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.TipoConta;

public interface ContaFactory {
	
	Conta generate(TipoConta tipo, UUID cliente_id);

}
