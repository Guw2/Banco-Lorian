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
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.security.user.User;
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
	
	// Buscar os clientes associados ao usuário autenticado
	public ClienteGetDTO getClienteInfo() {
		// Armazena o usuário autenticado
		User user = getContextUser();
		// Verifica se o usuário não é um valor nulo
		if(user != null) {
			// Armazena o cliente associado e converte para ClienteGetDTO
			ClienteGetDTO cliente = cliente_mapper.clienteToGetDto(user.getCliente());
			// Verifica se o cliente não é um valor nulo, caso não seja, é retornado
			if(cliente != null) return cliente;
			// Caso não seja, uma exceção é lançada
			else throw new RuntimeException("Nenhum cliente foi associado ao usuário " + user.getUsername() + ".");
		}else {
			// Exceção caso o usuário seja um valor nulo
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
	}

	// Busca as contas associadas ao usuário
	public List<ContaGetDTO> getContasInfo(){
		// Armazena o usuário autenticado
		User user = getContextUser();
		// Verifica se o usuário não é um valor nulo
		if(user != null) {
			// Armazena o cliente associado
			Cliente cliente = user.getCliente();
			// Encontra as contas por associadas ao cliente e converte todas para ContaGetDTO
			List<ContaGetDTO> list = 
					conta_repo.findByCliente(cliente).stream()
					.map(x -> conta_mapper.contaToGetDTO(x)).toList();
			
			// Retorna as contas
			return list;
		}else {
			// Exceção caso o usuário seja um valor nulo
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
	}
	
	// Abre uma nova conta
	public ContaGetDTO abrirConta(ContaUserPostDTO dto) {
		// Armazena o usuário autenticado
		User user = getContextUser();
		// Verifica se o usuário não é um valor nulo
		if(user != null) {
			// Usa o ContaFactory pra gerar uma conta com os dados do DTO passado como parâmetro
			Conta conta = conta_factory.generate(dto.tipo(), user.getCliente().getId());
			// Persiste e retorna a conta convertida para ContaGetDTO
			return conta_mapper.contaToGetDTO(conta_repo.save(conta));
		}else {
			// Exceção caso o usuário seja um valor nulo
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
		
	}
	
	// Busca os cartões associados ao usuário
	public List<CartaoGetDTO> getCartoesInfo(){
		User user = getContextUser();
		// Verifica se o usuário não é um valor nulo
		if(user != null) {
			// Encontra os cartões
			List<CartaoGetDTO> list = 
					cartao_repo.findByCliente(user.getCliente()).stream().map(x -> cartao_mapper.cartaoToGetDTO(x)).toList();
			// Retorna a lista
			return list;
		}else {
			// Exceção caso o usuário seja um valor nulo
			throw new RuntimeException("Nenhum usuário foi autenticado.");
		}
	}
	
	// Busca cartões associados a uma conta específica pelo número dela
	public List<CartaoGetDTO> getCartoesByNumeroDaConta(Long numero_da_conta){
		// Encontra a conta pelo número
		Conta conta = conta_repo.findByNumero(numero_da_conta).get();
		// Encontra os cartões associados à conta
		List<CartaoGetDTO> list = 
				cartao_repo.findByConta(conta).stream().map(x -> cartao_mapper.cartaoToGetDTO(x)).toList();
		
		// Retorna a lista
		return list;
	}
	
	// Emite um novo cartão
	public CartaoGetDTO emitirCartao(CartaoUserPostDTO dto) {
		// Armazena o usuário autenticado
		User user = getContextUser();
		// Busca e armazena a conta passada no dto
		Conta conta = conta_repo.findByNumero(dto.numero_da_conta()).get();
		
		// Verifica se essa conta realmente pertence ao usuário autenticado
		if(conta.getCliente().getUser().getUsername().equals(user.getUsername())) {
			// Gera um novo cartão com o CartaoFactory e armazena
			Cartao cartao = cartao_factory.generate(conta.getId());
			// Persiste e retorna o cartão convertido para CartaoGetDTO
			return cartao_mapper.cartaoToGetDTO(cartao_repo.save(cartao));
		}else {
			// Exceção caso a conta não exista ou pertença de outro usuário
			throw new RuntimeException("A conta inserida não pertence a esse usuário.");
		}
	}
	
	// Ativa um cartão
	public String ativarCartao(Long id) {
		// Armazena o usuário autenticado
		User user = getContextUser();
		// Armazena o cartão com o ID passado
		Cartao cartao = cartao_repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Não existe um cartão com esse id."));
		
		// Verifica se o cartão pertence ao usuário cadastrado
		if(cartao.getCliente().getUser().getUsername().equals(user.getUsername())) {
			// Verifica se o cartão já está ativado
			if(!cartao.getAtivado()) {
				// Ativa o cartão
				cartao.setAtivado(true);
				// Persiste o cartão no banco de dados
				cartao_repo.save(cartao);
				// Retorna mensagem de sucesso
				return "Cartão ativado com sucesso!";
			}else {
				// Caso o cartão já tenha sido ativado, uma exceção é lançada
				throw new RuntimeException("Esse cartão já foi ativado.");
			}
		}else {
			// Caso o cartão não pertença ao usuário autenticado, uma exceção é lançada
			throw new RuntimeException("Esse cartão não pertence ao usuário autenticado.");
		}
		
	}
	
	// Operação de transação
	public TransacaoGetDTO doTransacao(TransacaoPostDTO dto) {
		// Chama o método pra validar a transação
		if(validateTransacao(dto)) {
			// Reaproveita o 'doTransacao()' da camada Service de transação
			return transacao_service.doTransacao(dto);
		}else {
			// Caso a transação seja inválida, uma exceção é lançada
			throw new RuntimeException("Não é possível usar o dinheiro de uma conta que não é sua.");
		}
	}
	
	// Validação de transação
	private final Boolean validateTransacao(TransacaoPostDTO dto) {
		// Armazena as contas do usuário
		List<ContaGetDTO> contas = getContasInfo();
		// Armazena os cartões do usuário
		List<CartaoGetDTO> cartoes = getCartoesInfo();
		
		// Instancia as variáveis de ID de conta e de cartão
		Long conta_id = 0L; Long cartao_id = 0L;
		// Valor Boolean pra saber se a operação vai utilizar um cartão ou não
		Boolean use_cartao = false;
		
		// Verifica qual implementação de TransacaoPostDTO foi passada como parâmetro e armazena o ID
		// da conta ou do cartão
		if(dto instanceof TransferenciaPostDTO transf) conta_id = transf.getConta_id();
		else if(dto instanceof DepositoPostDTO deposito) conta_id = deposito.getConta_destino_id();
		else if(dto instanceof SaquePostDTO saque) conta_id = saque.getConta_id();
		else if(dto instanceof PagamentoCartaoDeCreditoPostDTO cartaoTransf) {
			cartao_id = cartaoTransf.getCartao_id(); use_cartao = true;
		}
		else if(dto instanceof PagamentoFaturaPostDTO fatura) {
			cartao_id = fatura.getCartao_id(); use_cartao = true;
		}
		
		// Caso não seja uma operação via cartão
		if(!use_cartao) {
			// Ocorre a tentativa de encontrar a conta informada pelo ID
			for(ContaGetDTO c : contas) {
				// Caso a conta seja encontrada, ela é retornada
				if (c.getId() == conta_id) return true;
			}
		// Caso seja uma operação via cartão
		}else {
			// Ocorre a tentativa de encontrar o cartão informado pelo ID
			for(CartaoGetDTO c : cartoes) {
				// Caso o cartão seja encontrado, ele é retornado
				if (c.getId() == cartao_id) return true;
			}
		}
		
		// Retorna falso caso a verificação passe sem encontrar nenhuma conta/cartão
		return false;
	}
	
	// Método pra recuperar o usuário autenticado
	private final User getContextUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
