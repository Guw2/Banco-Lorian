package com.lorian.lorianBank.cartao;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;

@Component
public class CartaoMapper {
	
	public CartaoGetDTO cartaoToGetDTO(Cartao cartao) {
		
		CartaoGetDTO cartaoGetDTO = new CartaoGetDTO();
		
		cartaoGetDTO.setId(cartao.getId());
		cartaoGetDTO.setNumero(cartao.getNumero());
		cartaoGetDTO.setLimite(cartao.getLimite());
		cartaoGetDTO.setBandeira(cartao.getBandeira());
		cartaoGetDTO.setAtivado(cartao.getAtivado());
		cartaoGetDTO.setDono(cartao.getCliente().getEmail());
		cartaoGetDTO.setConta(cartao.getConta().getNumero());
		
		return cartaoGetDTO;
	}

}
