package com.lorian.lorianBank.conta;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.conta.DTOs.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.ContaPostDTO;
import com.lorian.lorianBank.conta.factory.ContaFactory;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ContaService {

	private final ContaRepository repo;
	private final ContaFactory generateConta;
	private final ContaMapper mapper;

	public ContaService(ContaRepository repo, ContaFactory generateConta, ContaMapper mapper) {
		this.repo = repo;
		this.generateConta = generateConta;
		this.mapper = mapper;
	}

	public List<ContaGetDTO> getAllContas(){
		return repo.findAll().stream().map(x -> mapper.contaToGetDTO(x)).toList();
	}
	
	public ContaGetDTO getContaByNumero(Long numero) {
		return mapper.contaToGetDTO(repo.findByNumero(numero)
				.orElseThrow(() -> new NumeroNotFoundException("Conta com número " + numero + " não foi registrada.")));
	}
		
	public ContaGetDTO insertConta(ContaPostDTO dto) {
		return mapper.contaToGetDTO(repo.save(generateConta.generate(dto.getTipo(), dto.getCliente_id())));
	}
	
	@Transactional
	public void deleteContaByNumero(Long numero) {
		if(repo.findByNumero(numero).isPresent())
			repo.deleteByNumero(numero);
		else
			throw new NumeroNotFoundException("Conta com número " + numero + " não foi registrada.");
	}
	
}
