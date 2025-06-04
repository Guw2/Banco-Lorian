package com.lorian.lorianBank.Cliente;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.Cliente.DTOs.ClienteGetDto;
import com.lorian.lorianBank.Cliente.DTOs.ClientePostDTO;

@Component
public class ClienteMapper {
	
	public static ClienteGetDto clienteToGetDto(Cliente cliente) {
		return new ClienteGetDto(
				cliente.getNome(),
				cliente.getIdade(),
				cliente.getTelefone(),
				cliente.getEmail());
	}
	
	public static Cliente postDtoToCliente(ClientePostDTO dto) {
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
