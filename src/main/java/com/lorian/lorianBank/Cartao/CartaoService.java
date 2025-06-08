package com.lorian.lorianBank.cartao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cartao.DTOs.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.CartaoPostDTO;
import com.lorian.lorianBank.cartao.factory.GenerateCartao;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CartaoService {
	
	private final CartaoRepository repo;
	private final GenerateCartao generateCartao;

	public CartaoService(CartaoRepository repo, GenerateCartao generateCartao) {
		this.repo = repo;
		this.generateCartao = generateCartao;
	}
	
	public List<CartaoGetDTO> getAllCartoes(){
		return repo.findAll().stream().map(x -> CartaoMapper.cartaoToGetDTO(x)).toList();
	}
	
	public CartaoGetDTO getCartaoByNumero(String numero) {
		return CartaoMapper.cartaoToGetDTO(repo.findByNumero(numero)
				.orElseThrow(() -> new NumeroNotFoundException("Este número não foi registrado.")));
	}
	
	public CartaoGetDTO insertCartao(CartaoPostDTO dto) {
		return CartaoMapper.cartaoToGetDTO(repo.save(generateCartao.generate(dto)));
	}
	
	@Transactional
	public void deleteCartaoByNumero(String numero) {
		if(repo.findByNumero(numero).isPresent())
			repo.deleteByNumero(numero);
		else
			throw new NumeroNotFoundException("Este número não foi registrado.");
	}
}
