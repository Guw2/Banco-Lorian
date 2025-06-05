package com.lorian.lorianBank.conta;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.conta.DTOs.ContaGetDTO;

@Component
public class ContaMapper {

	public static ContaGetDTO contaToGetDTO(Conta conta) {
		return new ContaGetDTO(
				conta.getNumero(),
				conta.getAgencia(),
				conta.getSaldo(),
				conta.getTipo(),
				ClienteMapper.clienteToGetDto(conta.getCliente()));
	}
	
}
