package com.lorian.lorianBank.cartao.factory;

import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoPostDTO;

public interface CartaoFactory {

	// Design Pattern para "fabricação" de novos cartões
	Cartao generate(Long conta_id);
	
}
