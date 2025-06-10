package com.lorian.lorianBank.conta;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;

@Component
public class ContaMapper {

	private final ClienteMapper cliente_mapper;
	
	// Constructor Injection
	public ContaMapper(ClienteMapper cliente_mapper) {
		this.cliente_mapper = cliente_mapper;
	}

	// Converte entidade Conta em ContaGetDTO
	public ContaGetDTO contaToGetDTO(Conta conta) {
		// Cria um novo ContaGetDTO
		ContaGetDTO contaGetDTO = new ContaGetDTO();
		
		// Transfere os dados de 'conta' para 'contaGetDTO'
		contaGetDTO.setId(conta.getId());
		contaGetDTO.setNumero(conta.getNumero());
		contaGetDTO.setAgencia(conta.getAgencia());
		contaGetDTO.setSaldo(conta.getSaldo());
		contaGetDTO.setTipo(conta.getTipo());
		contaGetDTO.setCliente(cliente_mapper.clienteToGetDto(conta.getCliente()));
		
		// Retorna contaGetDTO
		return contaGetDTO;
	}
	
}
