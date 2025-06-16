package com.lorian.lorianBank.cartao.factory;

import com.lorian.lorianBank.cartao.Cartao;

public interface CartaoFactory {

	// Design Pattern para "fabricação" de novos cartões
	Cartao generate(Long conta_id);
	
}
