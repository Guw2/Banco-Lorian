package com.lorian.lorianBank.cartao;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;

@Component
public class CartaoMapper {
	
	
	// Converte a entidade 'Cartao' em 'CartaoGetDTO'
	public CartaoGetDTO cartaoToGetDTO(Cartao cartao) {
		
		// Cria um novo CartaoGetDTO
		CartaoGetDTO cartaoGetDTO = new CartaoGetDTO();
		
		// Utiliza os Setters para transferir as informações de 'cartao' para 'cartaoGetDTO'
		cartaoGetDTO.setId(cartao.getId());
		cartaoGetDTO.setNumero(cartao.getNumero());
		cartaoGetDTO.setLimite(cartao.getLimite());
		cartaoGetDTO.setBandeira(cartao.getBandeira());
		cartaoGetDTO.setAtivado(cartao.getAtivado());
		cartaoGetDTO.setDono(cartao.getCliente().getEmail());
		cartaoGetDTO.setConta(cartao.getConta().getNumero());
		
		// Retorna o DTO
		return cartaoGetDTO;
	}

}
