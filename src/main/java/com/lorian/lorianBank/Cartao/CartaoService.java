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

	public CartaoService(CartaoRepository repo, CartaoFactory generateCartao, CartaoMapper mapper) {
		this.repo = repo;
		this.generateCartao = generateCartao;
		this.mapper = mapper;
	}

	public List<CartaoGetDTO> getAllCartoes(){
		return repo.findAll().stream().map(x -> mapper.cartaoToGetDTO(x)).toList();
	}
	
	public CartaoGetDTO getCartaoByNumero(String numero) {
		return mapper.cartaoToGetDTO(repo.findByNumero(numero)
				.orElseThrow(() -> new NumeroNotFoundException("Este número não foi registrado.")));
	}
	
	public CartaoGetDTO insertCartao(CartaoPostDTO dto) {
		return mapper.cartaoToGetDTO(repo.save(generateCartao.generate(dto)));
	}
	
	@Transactional
	public void deleteCartaoByNumero(String numero) {
		if(repo.findByNumero(numero).isPresent())
			repo.deleteByNumero(numero);
		else
			throw new NumeroNotFoundException("Este número não foi registrado.");
	}
	
	public String ativarCartaoById(Long id) {
		Cartao cartao = repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Este id não existe."));
		
		if(cartao.getAtivado() == true)
			return "Este cartão já foi ativado.";
		
		cartao.setAtivado(true);
		repo.save(cartao);
		return "Cartão ativado com sucesso.\nNúmero="
		+ cartao.getNumero() + "\nID=" + cartao.getId();
	}
}
