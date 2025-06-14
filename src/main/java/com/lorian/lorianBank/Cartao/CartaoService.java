package com.lorian.lorianBank.cartao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoPostDTO;
import com.lorian.lorianBank.cartao.factory.CartaoFactory;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CartaoService {
	
	private final CartaoRepository repo;
	private final CartaoFactory generateCartao;
	private final CartaoMapper mapper;

	// Constructor Injection
	public CartaoService(CartaoRepository repo, CartaoFactory generateCartao, CartaoMapper mapper) {
		this.repo = repo;
		this.generateCartao = generateCartao;
		this.mapper = mapper;
	}

	// Recupera todos os cartões do banco de dados
	public List<CartaoGetDTO> getAllCartoes(){
		// Retorna um findAll() mapeado para transformar todos os cartões da lista em DTOs
		return repo.findAll().stream().map(x -> mapper.cartaoToGetDTO(x)).toList();
	}
	
	// Recupera um cartão pelo número
	public CartaoGetDTO getCartaoByNumero(String numero) {
		// Executa a operação de busca por número e armazena na variável 'cartao'
		var cartao = repo.findByNumero(numero)
				.orElseThrow(() -> new NumeroNotFoundException("Este número não foi registrado."));
		// retorna a entidade convertida em CartaoGetDTO
		return mapper.cartaoToGetDTO(cartao);
	}
	
	// Insere um cartão no banco de dados
	public CartaoGetDTO insertCartao(CartaoPostDTO dto) { // Recebe um CartaoPostDTO
		// Gera um cartão utilizando a classe de Factory
		Cartao cartao = generateCartao.generate(dto.getConta_id());
		// Salva e retorna um CartaoGetDTO já mapeado
		return mapper.cartaoToGetDTO(repo.save(cartao));
	}
	
	// O Transactional é necessário para evitar erros
	@Transactional // Deleta um cartão do banco de dados
	public void deleteCartaoByNumero(String numero) {
		// Verifica se o número informado como parâmetro é válido
		if(repo.findByNumero(numero).isPresent())
			repo.deleteByNumero(numero);
		// Caso não seja ele lança uma excessão personalizada
		else
			throw new NumeroNotFoundException("Este número não foi registrado.");
	}
	
	// Ativa um cartão
	public String ativarCartaoById(Long id) {
		// Resgata um cartão do banco de dados pelo seu ID
		Cartao cartao = repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Este id não existe."));
		
		// Verifica se o cartão já foi ativado
		if(cartao.getAtivado() == true)
			return "Este cartão já foi ativado.";
		
		// Caso não tenha sido ativado ainda, as operações de ativação são executadas
		cartao.setAtivado(true);
		repo.save(cartao);
		
		// Retorna uma String informando que o cartão foi ativado
		return "Cartão ativado com sucesso.\nNúmero="
		+ cartao.getNumero() + "\nID=" + cartao.getId();
	}
}
