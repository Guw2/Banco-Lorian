package com.lorian.lorianBank.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.cliente.DTOs.post.ClientePostDTO;
import com.lorian.lorianBank.exceptions.custom.EmailNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private final ClienteRepository repo;
	private final ClienteMapper mapper;

	// Constructor Injection
	public ClienteService(ClienteRepository repo, ClienteMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	// Recupera todos os clientes do banco de dados
	public List<ClienteGetDTO> getAllClientes(){
		// Retorna um findAll mapeado para transformar todos os clientes da lista em DTOs
		return repo.findAll().stream().map(x -> mapper.clienteToGetDto(x)).toList();
	}
	
	// Recupera um cliente pelo E-mail
	public ClienteGetDTO getClienteByEmail(String email) {
		// Resgata o cliente com o E-mail informado e armazena na variável
		Cliente cliente = repo.findByEmail(email)
				.orElseThrow(() -> new EmailNotFoundException("Este e-mail não foi cadastrado."));
		
		// Retorna o ClienteGetDTO já mapeado
		return mapper.clienteToGetDto(cliente);
	}
	
	// Insere um cliente no banco de dados
	public ClienteGetDTO insertCliente(ClientePostDTO dto) {
		// Converte o DTO em Cliente e armazena na variável
		Cliente cliente = mapper.postDtoToCliente(dto);
		// Salva e retorna um ClienteGetDTO já mapeado
		return mapper.clienteToGetDto(repo.save(cliente));
	}
	
	@Transactional // Deleta um cliente do banco de dados pelo E-mail
	public void deleteClienteByEmail(String email) {
		// Verifica se o cliente já está presente no banco de dados
		if(repo.findByEmail(email).isPresent())
			repo.deleteByEmail(email);
		// Caso não esteja, é lançada uma exceção
		else
			throw new EmailNotFoundException("Este e-mail não foi cadastrado.");
	}
}
