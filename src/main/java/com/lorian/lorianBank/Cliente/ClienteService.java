package com.lorian.lorianBank.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cliente.DTOs.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.ClientePostDTO;
import com.lorian.lorianBank.exceptions.custom.EmailNotFoundException;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;

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
				.orElseThrow(() -> new EmailNotFoundException("Este e-mail não foi cadastrado.")));
	}
	
	public ClienteGetDTO insertCliente(ClientePostDTO dto) {
		Cliente cliente = ClienteMapper.postDtoToCliente(dto);
		return ClienteMapper.clienteToGetDto(repo.save(cliente));
	}
	
	@Transactional
	public void deleteClienteByEmail(String email) {
		if(repo.findByEmail(email).isPresent())
			repo.deleteByEmail(email);
		else
			throw new EmailNotFoundException("Este e-mail não foi cadastrado.");
	}
}
