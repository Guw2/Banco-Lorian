package com.lorian.lorianBank.cartao;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.DTOs.CartaoGetDTO;

@Component
public class CartaoMapper {
	
	public static CartaoGetDTO cartaoToGetDTO(Cartao cartao) {
		return new CartaoGetDTO(
				cartao.getNumero(),
				cartao.getLimite(),
				cartao.getBandeira(),
				cartao.getCliente().getEmail(),
				cartao.getConta().getNumero()
				);
	}

}
