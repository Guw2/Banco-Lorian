package com.lorian.lorianBank.cartao.DTOs;

import java.time.Instant;

import com.lorian.lorianBank.cartao.BandeiraCartao;

public record CartaoGetDTO(
		String numero, 
		Integer cvv, 
		Instant validade, 
		Double limite, 
		BandeiraCartao bandeira) {
}