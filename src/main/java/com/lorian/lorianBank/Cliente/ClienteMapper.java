package com.lorian.lorianBank.cliente;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.post.ClientePostDTO;

@Component
public class ClienteMapper {
	
	// Converte entidade Cliente em ClienteGetDTO
	public ClienteGetDTO clienteToGetDto(Cliente cliente) {
		// Cria um novo ClienteGetDTO
		ClienteGetDTO clienteGetDTO = new ClienteGetDTO();
		
		// Transfere os dados de 'cliente' para 'clienteGetDTO'
		clienteGetDTO.setId(cliente.getId());
		clienteGetDTO.setNome(cliente.getNome());
		clienteGetDTO.setIdade(cliente.getIdade());
		clienteGetDTO.setTelefone(cliente.getTelefone());
		clienteGetDTO.setEmail(cliente.getEmail());
		
		// Retorna o clienteGetDTO
		return clienteGetDTO;
	}
	
	// Converte um ClientePostDTO em Cliente
	protected Cliente postDtoToCliente(ClientePostDTO dto) {
		// Cria um novo Cliente
		Cliente cliente = new Cliente();

		// Transfere os dados de 'dto' para 'cliente'
		cliente.setNome(dto.getNome());
		cliente.setCpf(dto.getCpf());
		cliente.setIdade(dto.getIdade());
		cliente.setEndereco(dto.getEndereco());
		cliente.setTelefone(dto.getTelefone());
		cliente.setEmail(dto.getEmail());
		
		// Retorna o cliente
		return cliente;
	}
		
}
