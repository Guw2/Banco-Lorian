package com.lorian.lorianBank.cliente;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.ClientePostDTO;

@Component
public class ClienteMapper {
	
	public static ClienteGetDTO clienteToGetDto(Cliente cliente) {
		return new ClienteGetDTO(
				cliente.getNome(),
				cliente.getIdade(),
				cliente.getTelefone(),
				cliente.getEmail());
	}
	
	protected static Cliente postDtoToCliente(ClientePostDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setCpf(dto.getCpf());
		cliente.setIdade(dto.getIdade());
		cliente.setEndereco(dto.getEndereco());
		cliente.setTelefone(dto.getTelefone());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}
		
}
