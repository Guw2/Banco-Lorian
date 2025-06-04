package com.lorian.lorianBank.Cliente;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.Cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.Cliente.DTOs.ClientePostDTO;

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
		cliente.setNome(dto.nome());
		cliente.setCpf(dto.cpf());
		cliente.setIdade(dto.idade());
		cliente.setEndereco(dto.endereco());
		cliente.setTelefone(dto.telefone());
		cliente.setEmail(dto.email());
		return cliente;
	}
		
}
