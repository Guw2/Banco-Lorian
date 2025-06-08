package com.lorian.lorianBank.cartao.factory;

import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.DTOs.CartaoPostDTO;

public interface CartaoFactory {

	Cartao generate(CartaoPostDTO dto);
	
}
