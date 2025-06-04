package com.lorian.lorianBank.Conta.DTOs;

import java.time.Instant;

import com.lorian.lorianBank.Cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.Conta.TipoConta;

public record ContaGetDTO(
		Long numero,
		String agencia,
		Double saldo,
		TipoConta tipo,
		Instant data_de_abertura,
		ClienteGetDTO cliente
		) {

}
