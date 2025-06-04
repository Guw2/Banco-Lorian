package com.lorian.lorianBank.Conta;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.Cliente.ClienteMapper;
import com.lorian.lorianBank.Conta.DTOs.ContaGetDTO;

@Component
public class ContaMapper {

	public static ContaGetDTO contaToGetDTO(Conta conta) {
		return new ContaGetDTO(
				conta.getNumero(),
				conta.getAgencia(),
				conta.getSaldo(),
				conta.getTipo(),
				conta.getData_de_abertura(),
				ClienteMapper.clienteToGetDto(conta.getCliente()));
	}
	
}
