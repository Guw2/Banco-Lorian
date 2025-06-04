package com.lorian.lorianBank.Cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.Cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.Cliente.DTOs.ClientePostDTO;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private final ClienteRepository repo;

	public ClienteService(ClienteRepository repo) {
		this.repo = repo;
	}
	
	public List<ClienteGetDTO> getAllClientes(){
		return repo.findAll().stream().map(x -> ClienteMapper.clienteToGetDto(x)).toList();
	}
	
	public ClienteGetDTO getClienteByEmail(String email) {
		return ClienteMapper.clienteToGetDto(repo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Email não encontrado.")));
	}
	
	public ClienteGetDTO insertCliente(ClientePostDTO dto) {
		Cliente cliente = ClienteMapper.postDtoToCliente(dto);
		return ClienteMapper.clienteToGetDto(repo.save(cliente));
	}
	
	@Transactional
	public void deleteClienteByEmail(String email) {
		repo.deleteByEmail(email);
	}
}
