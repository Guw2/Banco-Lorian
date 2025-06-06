package com.lorian.lorianBank.cartao.DTOs;

import com.lorian.lorianBank.cartao.BandeiraCartao;

public record CartaoGetDTO(
		String numero, 
		Double limite, 
		BandeiraCartao bandeira,
		String dono,
		Long conta
		) {
}