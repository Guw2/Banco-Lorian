package com.lorian.lorianBank.cartao;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.DTOs.CartaoGetDTO;

@Component
public class CartaoMapper {
	
	public static CartaoGetDTO cartaoToGetDTO(Cartao cartao) {
		return new CartaoGetDTO(cartao.getNumero(), cartao.getCvv(), cartao.getValidade(), cartao.getLimite(), cartao.getBandeira());
	}

}
