package com.lorian.lorianBank.cliente;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.ClientePostDTO;

@Component
public class ClienteMapper {
	
	public ClienteGetDTO clienteToGetDto(Cliente cliente) {
		ClienteGetDTO clienteGetDTO = new ClienteGetDTO();
		
		clienteGetDTO.setId(cliente.getId());
		clienteGetDTO.setNome(cliente.getNome());
		clienteGetDTO.setIdade(cliente.getIdade());
		clienteGetDTO.setTelefone(cliente.getTelefone());
		clienteGetDTO.setEmail(cliente.getEmail());
		
		return clienteGetDTO;
	}
	
	protected Cliente postDtoToCliente(ClientePostDTO dto) {
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
