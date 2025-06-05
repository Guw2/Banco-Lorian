package com.lorian.lorianBank.conta.DTOs;

import com.lorian.lorianBank.cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.conta.TipoConta;

public record ContaGetDTO(
		Long numero,
		String agencia,
		Double saldo,
		TipoConta tipo,
		ClienteGetDTO cliente
		) {

}
