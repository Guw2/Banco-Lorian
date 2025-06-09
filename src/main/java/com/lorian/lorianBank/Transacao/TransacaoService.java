package com.lorian.lorianBank.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.exceptions.custom.TransacaoException;
import com.lorian.lorianBank.transacao.DTOs.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.TransacaoPostDTO;

@Service
public class TransacaoService {

	private final TransacaoRepository transacao_repo;
	private final ContaRepository conta_repo;
	private final TransacaoMapper mapper;
	
	public TransacaoService(TransacaoRepository transacao_repo, ContaRepository conta_repo, TransacaoMapper mapper) {
		this.transacao_repo = transacao_repo;
		this.conta_repo = conta_repo;
		this.mapper = mapper;
	}

	public List<TransacaoGetDTO> getAllTransacoes(){
		return transacao_repo.findAll().stream().map(x -> mapper.transacaoToGetDto(x)).toList();
	}
	
	public TransacaoGetDTO getTransacaoById(UUID id) {
		return mapper.transacaoToGetDto(transacao_repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
	}
	
	public TransacaoGetDTO doTransacao(TransacaoPostDTO dto) {
		Transacao transacao = mapper.postDtoToTransacao(dto);
		pay(transacao);
		return mapper.transacaoToGetDto(transacao_repo.save(transacao));
	}
	
	private final void pay(Transacao transacao) {
		Double valor = transacao.getValor();
		
		if(transacao.getTipo() == TipoTransacao.TRANSFERENCIA) {
			Conta conta1 = transacao.getConta(); Conta conta2 = transacao.getConta_destino();
			
			if(valor > conta1.getSaldo()) 
				throw new TransacaoException("Saldo insuficiente.");
			else if(valor > 10000) 
				throw new TransacaoException("Limite de R$10000 para transferências ultrapassado.");
			else {
				conta1.debitar(valor);
				conta2.acrescentar(valor);
				conta_repo.save(conta1);
				conta_repo.save(conta2);
			}
			
		}else if(transacao.getTipo() == TipoTransacao.DEPOSITO) {
			Conta conta = transacao.getConta_destino();
			
			if(valor > 5000) 
				throw new TransacaoException("Limite de R$5000 para depósitos ultrapassado.");
			else {
				conta.acrescentar(valor);
				conta_repo.save(conta);
			}
		}else if(transacao.getTipo() == TipoTransacao.SAQUE) {
			Conta conta = transacao.getConta();
			
			if(valor > 5000) 
				throw new TransacaoException("Limite de R$5000 para saques ultrapassado.");
			else {
				conta.debitar(valor);
				conta_repo.save(conta);
			}
		}
	}
		
}
