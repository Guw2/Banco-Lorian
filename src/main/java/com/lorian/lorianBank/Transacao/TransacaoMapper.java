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
	
	public TransacaoMapper(ContaRepository conta_repo, CartaoRepository cartao_repo) {
		this.conta_repo = conta_repo;
		this.cartao_repo = cartao_repo;
	}

	protected TransacaoGetDTO transacaoToGetDto(Transacao transacao) {
		
		TransacaoGetDTO transacaoGetDTO = new TransacaoGetDTO();
		
		transacaoGetDTO.setId(transacao.getId());
		transacaoGetDTO.setValor(transacao.getValor());
		transacaoGetDTO.setData(transacao.getData());
		transacaoGetDTO.setTipo(transacao.getTipo());
		if(transacao.getConta() != null)
			transacaoGetDTO.setConta_remetente(transacao.getConta().getNumero());
		if(transacao.getConta_destino() != null)
			transacaoGetDTO.setConta_destinatario(transacao.getConta_destino().getNumero());
		
		return transacaoGetDTO;
		
	}
	
	protected Transacao postDtoToTransacao(TransacaoPostDTO dto) {
		Transacao transacao = new Transacao();
		
		transacao.setValor(dto.getValor());
		transacao.setData(Instant.now());	
		transacao.setTipo(dto.getTipo());
		
		if(dto instanceof TransferenciaPostDTO transf) {
			transacao.setConta(conta_repo.findById(transf.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setConta_destino(conta_repo.findById(transf.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setDescricao(transf.getDescricao());
		}else if(dto instanceof SaquePostDTO saque) {
			transacao.setConta(conta_repo.findById(saque.getConta_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
		}else if(dto instanceof DepositoPostDTO deposito) {
			transacao.setConta_destino(conta_repo.findById(deposito.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
		}else if(dto instanceof PagamentoCartaoDeCreditoPostDTO cartao) {
			transacao.setConta(cartao_repo.findById(cartao.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getConta());
			transacao.setConta_destino(conta_repo.findById(cartao.getConta_destino_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")));
			transacao.setNumero_cartao(cartao_repo.findById(cartao.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getNumero());
			transacao.setDescricao(cartao.getDescricao());
		}else if(dto instanceof PagamentoFaturaPostDTO fatura) {
			transacao.setConta(cartao_repo.findById(fatura.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getConta());
			transacao.setNumero_cartao(cartao_repo.findById(fatura.getCartao_id())
					.orElseThrow(() -> new IdNotFoundException("Este id não existe.")).getNumero());
		}
		
		return transacao;
	}
	
}
