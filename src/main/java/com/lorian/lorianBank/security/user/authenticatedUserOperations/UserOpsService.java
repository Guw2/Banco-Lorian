package com.lorian.lorianBank.security.user.authenticatedUserOperations;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.CartaoMapper;
import com.lorian.lorianBank.cartao.CartaoRepository;
import com.lorian.lorianBank.cartao.DTOs.get.CartaoGetDTO;
import com.lorian.lorianBank.cartao.DTOs.post.CartaoUserPostDTO;
import com.lorian.lorianBank.cartao.factory.CartaoFactory;
import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.cliente.ClienteMapper;
import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.ContaMapper;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.conta.DTOs.get.ContaGetDTO;
import com.lorian.lorianBank.conta.DTOs.post.ContaUserPostDTO;
import com.lorian.lorianBank.conta.factory.ContaFactory;
import com.lorian.lorianBank.security.user.User;
import com.lorian.lorianBank.transacao.TransacaoMapper;
import com.lorian.lorianBank.transacao.TransacaoRepository;
import com.lorian.lorianBank.transacao.TransacaoService;
import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.post.DepositoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoCartaoDeCreditoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoFaturaPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.SaquePostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransacaoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransferenciaPostDTO;

@Service
public class UserOpsService {

	// --- Cliente ---
	private final ClienteMapper cliente_mapper;
	
	// --- Conta ---
	private final ContaRepository conta_repo;
	private final ContaMapper conta_mapper;
	private final ContaFactory conta_factory;
	
	// --- Cartão ---
	private final CartaoRepository cartao_repo;
	private final CartaoMapper cartao_mapper;
	private final CartaoFactory cartao_factory;
	
	// --- Transação ---
	private final TransacaoService transacao_service;
	
	// Constructor Injection
	public UserOpsService(ClienteMapper cliente_mapper, ContaRepository conta_repo, ContaMapper conta_mapper,
			ContaFactory conta_factory, CartaoRepository cartao_repo, CartaoMapper cartao_mapper,
			CartaoFactory cartao_factory, TransacaoService transacao_service) {
		this.cliente_mapper = cliente_mapper;
		this.conta_repo = conta_repo;
		this.conta_mapper = conta_mapper;
		this.conta_factory = conta_factory;
		this.cartao_repo = cartao_repo;
		this.cartao_mapper = cartao_mapper;
		this.cartao_factory = cartao_factory;
		this.transacao_service = transacao_service;
	}
	
	public ClienteGetDTO getClienteInfo() {
		User user = getContextUser();
		if(user != null) {
			ClienteGetDTO cliente = cliente_mapper.clienteToGetDto(user.getCliente());
			if(cliente != null) return cliente;
			else throw new RuntimeException("Nenhum cliente foi associado ao usuário " + user.getUsername() + ".");
		}else {
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
	}

	public List<ContaGetDTO> getContasInfo(){
		User user = getContextUser();
		if(user != null) {
			Cliente cliente = user.getCliente();
			List<ContaGetDTO> list = 
					conta_repo.findByCliente(cliente).stream()
					.map(x -> conta_mapper.contaToGetDTO(x)).toList();
			
			return list;
		}else {
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
	}
	
	public ContaGetDTO abrirConta(ContaUserPostDTO dto) {
		User user = getContextUser();
		Conta conta = conta_factory.generate(dto.tipo(), user.getCliente().getId());
		
		return conta_mapper.contaToGetDTO(conta_repo.save(conta));
	}
	
	public List<CartaoGetDTO> getCartoesInfo(){
		List<CartaoGetDTO> list = 
				cartao_repo.findByCliente(getContextUser().getCliente()).stream().map(x -> cartao_mapper.cartaoToGetDTO(x)).toList();
		
		return list;
	}
	
	public List<CartaoGetDTO> getCartoesByNumeroDaConta(Long numero){
		Conta conta = conta_repo.findByNumero(numero).get();
		List<CartaoGetDTO> list = 
				cartao_repo.findByConta(conta).stream().map(x -> cartao_mapper.cartaoToGetDTO(x)).toList();
		
		return list;
	}
	
	public CartaoGetDTO emitirCartao(CartaoUserPostDTO dto) {
		Conta conta = conta_repo.findByNumero(dto.numero_da_conta()).get();
		
		if(conta.getCliente().getUser().getUsername().equals(getContextUser().getUsername())) {
			Cartao cartao = cartao_factory.generate(conta.getId());
			return cartao_mapper.cartaoToGetDTO(cartao_repo.save(cartao));
		}else {
			throw new RuntimeException("A conta inserida não pertence a esse usuário.");
		}
	}
	
	public TransacaoGetDTO doTransacao(TransacaoPostDTO dto) {
		if(validateTransacao(dto)) {
			return transacao_service.doTransacao(dto);
		}else {
			throw new RuntimeException("Não é possível usar o dinheiro de uma conta que não é sua.");
		}
	}
	
	private final Boolean validateTransacao(TransacaoPostDTO dto) {
		List<ContaGetDTO> contas = getContasInfo();
		List<CartaoGetDTO> cartoes = getCartoesInfo();
		Long conta_id = 0L; Long cartao_id = 0L;
		Boolean use_cartao = false;
		
		if(dto instanceof TransferenciaPostDTO transf) conta_id = transf.getConta_id();
		else if(dto instanceof DepositoPostDTO deposito) conta_id = deposito.getConta_destino_id();
		else if(dto instanceof SaquePostDTO saque) conta_id = saque.getConta_id();
		else if(dto instanceof PagamentoCartaoDeCreditoPostDTO cartaoTransf) {
			cartao_id = cartaoTransf.getCartao_id(); use_cartao = true;
		}
		else if(dto instanceof PagamentoFaturaPostDTO fatura) {
			cartao_id = fatura.getCartao_id(); use_cartao = true;
		}
		
		if(!use_cartao) {
			for(ContaGetDTO c : contas) {
				if (c.getId() == conta_id) return true;
			}
		}else {
			for(CartaoGetDTO c : cartoes) {
				if (c.getId() == cartao_id) return true;
			}
		}
		
		return false;
	}
	
	private final User getContextUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
