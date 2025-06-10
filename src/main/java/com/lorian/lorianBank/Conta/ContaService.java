package com.lorian.lorianBank.conta;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.post.ContaPostDTO;
import com.lorian.lorianBank.conta.factory.ContaFactory;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ContaService {

	private final ContaRepository repo;
	private final ContaFactory generateConta;
	private final ContaMapper mapper;

	// Constructor Injection
	public ContaService(ContaRepository repo, ContaFactory generateConta, ContaMapper mapper) {
		this.repo = repo;
		this.generateConta = generateConta;
		this.mapper = mapper;
	}

	// Resgata todas as contas do banco de dados
	public List<ContaGetDTO> getAllContas(){
		// Retorna um findAll mapeado para converter as contas em DTOs
		return repo.findAll().stream().map(x -> mapper.contaToGetDTO(x)).toList();
	}
	
	// Resgata uma conta por seu número
	public ContaGetDTO getContaByNumero(Long numero) {
		// Recupera uma conta do banco de dados pelo número e armazena na variável
		Conta conta = repo.findByNumero(numero)
				.orElseThrow(() -> new NumeroNotFoundException("Conta com número " + numero + " não foi registrada."));
		// Retorna um ContaGetDTO já mapeado
		return mapper.contaToGetDTO(conta);
	}
	
	// Insere uma conta no banco de dados
	public ContaGetDTO insertConta(ContaPostDTO dto) {
		// Gera uma conta utilizando o Factory
		Conta conta = generateConta.generate(dto.getTipo(), dto.getCliente_id());
		// Salva e retorna um ContaGetDTO já mapeado
		return mapper.contaToGetDTO(repo.save(conta));
	}
	
	// O Transactional está presente para evitar erros
	@Transactional // Deleta uma conta do banco de dados pelo seu número
	public void deleteContaByNumero(Long numero) {
		// Verifica se uma conta com esse número está presente no banco de dados
		if(repo.findByNumero(numero).isPresent())
			repo.deleteByNumero(numero);
		// Caso não esteja, é lançada uma exceção
		else
			throw new NumeroNotFoundException("Conta com número " + numero + " não foi registrada.");
	}
	
}
