package com.lorian.lorianBank.transacao;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.lorian.lorianBank.cartao.CartaoRepository;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.post.DepositoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoCartaoDeCreditoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.PagamentoFaturaPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.SaquePostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransacaoPostDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransferenciaPostDTO;

@Component
public class TransacaoMapper {

	private final ContaRepository conta_repo;
	private final CartaoRepository cartao_repo;
	
	// Constructor Injection
	public TransacaoMapper(ContaRepository conta_repo, CartaoRepository cartao_repo) {
		this.conta_repo = conta_repo;
		this.cartao_repo = cartao_repo;
	}

	// Converte entidade Transação em TransacaoGetDTO
	protected TransacaoGetDTO transacaoToGetDto(Transacao transacao) {
		
		// Cria um novo TransacaoGetDTO
		TransacaoGetDTO transacaoGetDTO = new TransacaoGetDTO();
		
		// Transfere os dados de 'transacao' para 'transacaoGetDTO'
		transacaoGetDTO.setId(transacao.getId());
		transacaoGetDTO.setValor(transacao.getValor());
		transacaoGetDTO.setData(transacao.getData());
		transacaoGetDTO.setTipo(transacao.getTipo());
		
		// Verifica se existe uma conta em 'transacao'
		if(transacao.getConta() != null)
			// Transfere para transacaoGetDTO
			transacaoGetDTO.setConta_remetente(transacao.getConta().getNumero());
		// Verifica se existe uma conta destino em 'transacao'
		if(transacao.getConta_destino() != null)
			// Transfere para transacaoGetDTO
			transacaoGetDTO.setConta_destinatario(transacao.getConta_destino().getNumero());
		
		// Retorna transacaoGetDTO
		return transacaoGetDTO;
		
	}
	
	// Converte TransacaoPostDTO em Transacao
	protected Transacao postDtoToTransacao(TransacaoPostDTO dto) {
		// Cria uma nova transação
		Transacao transacao = new Transacao();
		
		// Transfere as informações de 'dto' para 'transacao'
		transacao.setValor(dto.getValor());
		transacao.setTipo(dto.getTipo());
		
		transacao.setData(Instant.now());
		
		// Analisa se dto é uma instancia de TransferenciaPostDTO
		if(dto instanceof TransferenciaPostDTO transf) {
			
			// Transfere o que for necessário para o caso de um TransferenciaPostDTO
			transacao.setConta(conta_repo.findById(transf.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setConta_destino(conta_repo.findById(transf.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setDescricao(transf.getDescricao());
		}
		
		// Analisa se dto é uma instancia de SaquePostDTO
		else if(dto instanceof SaquePostDTO saque) {
			
			// Transfere o que for necessário para o caso de um SaquePostDTO
			transacao.setConta(conta_repo.findById(saque.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
		}
		
		// Analisa se dto é uma instancia de DepositoPostDTO
		else if(dto instanceof DepositoPostDTO deposito) {
			
			// Transfere o que for necessário para o caso de um DepositoPostDTO
			transacao.setConta_destino(conta_repo.findById(deposito.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
		}
		
		// Analisa se dto é uma instancia de PagamentoCartaoDeCreditoPostDTO
		else if(dto instanceof PagamentoCartaoDeCreditoPostDTO cartao) {
			
			// Transfere o que for necessário para o caso de um PagamentoCartaoDeCreditoPostDTO
			transacao.setConta(cartao_repo.findById(cartao.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getConta());
			transacao.setConta_destino(conta_repo.findById(cartao.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setNumero_cartao(cartao_repo.findById(cartao.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getNumero());
			transacao.setDescricao(cartao.getDescricao());
		}
		
		// Analisa se dto é uma instancia de PagamentoFaturaPostDTO
		else if(dto instanceof PagamentoFaturaPostDTO fatura) {
			
			// Transfere o que for necessário para o caso de um PagamentoFaturaPostDTO
			transacao.setConta(cartao_repo.findById(fatura.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getConta());
			transacao.setNumero_cartao(cartao_repo.findById(fatura.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getNumero());
		}
		
		// Retorna a transacao
		return transacao;
	}
	
}
