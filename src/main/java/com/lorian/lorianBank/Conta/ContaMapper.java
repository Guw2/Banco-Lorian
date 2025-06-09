package com.lorian.lorianBank.conta;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;

@Component
public class ContaMapper {

	private final ClienteMapper cliente_mapper;
	
	public ContaMapper(ClienteMapper cliente_mapper) {
		this.cliente_mapper = cliente_mapper;
	}

	public ContaGetDTO contaToGetDTO(Conta conta) {
		
		ContaGetDTO contaGetDTO = new ContaGetDTO();
		
		contaGetDTO.setId(conta.getId());
		contaGetDTO.setNumero(conta.getNumero());
		contaGetDTO.setAgencia(conta.getAgencia());
		contaGetDTO.setSaldo(conta.getSaldo());
		contaGetDTO.setTipo(conta.getTipo());
		contaGetDTO.setCliente(cliente_mapper.clienteToGetDto(conta.getCliente()));
		
		return contaGetDTO;
	}
	
}
