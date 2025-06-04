package com.lorian.lorianBank.Conta;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.Conta.DTOs.ContaGetDTO;
import com.lorian.lorianBank.Conta.DTOs.ContaPostDTO;

@Service
public class ContaService {

	private final ContaRepository repo;
	private final GenerateConta generateConta;

	public ContaService(ContaRepository repo, GenerateConta generateConta) {
		this.repo = repo;
		this.generateConta = generateConta;
	}

	public List<ContaGetDTO> getAllContas(){
		return repo.findAll().stream().map(x -> ContaMapper.contaToGetDTO(x)).toList();
	}
	
	public ContaGetDTO getContaByNumero(Long numero) {
		return ContaMapper.contaToGetDTO(repo.findByNumero(numero)
				.orElseThrow(() -> new RuntimeException("Conta com número " + numero + " não foi registrada.")));
	}
		
	public ContaGetDTO insertConta(ContaPostDTO dto) {
		return ContaMapper.contaToGetDTO(repo.save(generateConta.generate(dto.tipo(), dto.cliente_id())));
	}
	
}
