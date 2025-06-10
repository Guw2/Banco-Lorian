package com.lorian.lorianBank.transacao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cartao.CartaoRepository;
import com.lorian.lorianBank.conta.Conta;
import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;
import com.lorian.lorianBank.exceptions.custom.TransacaoException;
import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;
import com.lorian.lorianBank.transacao.DTOs.post.TransacaoPostDTO;

@Service
public class TransacaoService {

	private final TransacaoRepository transacao_repo;
	private final ContaRepository conta_repo;
	private final CartaoRepository cartao_repo;
	private final TransacaoMapper mapper;
	
	// Constructor Injection
	public TransacaoService(TransacaoRepository transacao_repo, ContaRepository conta_repo,
			CartaoRepository cartao_repo, TransacaoMapper mapper) {
		this.transacao_repo = transacao_repo;
		this.conta_repo = conta_repo;
		this.cartao_repo = cartao_repo;
		this.mapper = mapper;
	}

	// Recupera uma lista de todas as transações feitas
	public List<TransacaoGetDTO> getAllTransacoes(){
		// Retorna uma lista mapeada para transformar as transações em DTOs
		return transacao_repo.findAll().stream().map(x -> mapper.transacaoToGetDto(x)).toList();
	}
	
	// Recupera uma transação por ID
	public TransacaoGetDTO getTransacaoById(Long id) {
		// Retorna um TransacaoGetDTO já mapeado
		return mapper.transacaoToGetDto(transacao_repo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Esse id não existe.")));
	}
	
	// Método que executa uma transação
	public TransacaoGetDTO doTransacao(TransacaoPostDTO dto) {
		// Converte o DTO passado como parâmetro em entidade Transacao
		Transacao transacao = mapper.postDtoToTransacao(dto);
		// Aplica a transação
		pay(transacao);
		// Salva e retorna um TransacaoGetDTO já mapeado
		return mapper.transacaoToGetDto(transacao_repo.save(transacao));
	}
	
	// Método para aplicar a transação (pagar)
	private final void pay(Transacao transacao) {
		// Armazena o valor da transacao
		Double valor = transacao.getValor();
		
		// Verifica se o valor é negativo ou zero
		if(valor <= 0) throw new TransacaoException("Valores negativos ou nulos não são aceitos.");
		// Caso não seja, as operações são executadas
		else {
			// Verifica se o tipo da transação é 'TRANSFERENCIA'
			if(transacao.getTipo() == TipoTransacao.TRANSFERENCIA) {
				// Armazena a conta e a conta destino
				Conta conta1 = transacao.getConta(); Conta conta2 = transacao.getConta_destino();
				
				// Verifica se o saldo da conta é suficiente para fazer a transferência
				if(valor > conta1.getSaldo()) 
					throw new TransacaoException("Saldo insuficiente.");
				// Verifica se não vai ultrapassar o limite de transferência
				else if(valor > 10000) 
					throw new TransacaoException("Limite de R$10000 para transferências ultrapassado.");
				// Verifica se o usuário não está tentando transferir para ele mesmo
				else if(conta1.getId() == conta2.getId())
					throw new TransacaoException("Você não pode transferir para a própria conta.");
				// Realiza as operações caso todas as condições sejam falsas
				else {
					conta1.debitar(valor);
					conta2.creditar(valor);
					conta_repo.save(conta1);
					conta_repo.save(conta2);
				}
			}
			
			// Verifica se o tipo da transação é 'DEPOSITO'
			else if(transacao.getTipo() == TipoTransacao.DEPOSITO) {
				// Armazena a conta
				Conta conta = transacao.getConta_destino();
				
				// Verifica se não vai ultrapassar o limite de depósito
				if(valor > 5000) 
					throw new TransacaoException("Limite de R$5000 para depósitos ultrapassado.");
				// Realiza as operações caso a condição seja falsa
				else {
					conta.creditar(valor);
					conta_repo.save(conta);
				}
			}
			
			// Verifica se o tipo da transação é 'SAQUE'
			else if(transacao.getTipo() == TipoTransacao.SAQUE) {
				// Armazena a conta
				Conta conta = transacao.getConta();
				
				// Verifica se não vai ultrapassar o limite de saque
				if(valor > 5000) 
					throw new TransacaoException("Limite de R$5000 para saques ultrapassado.");
				// Realiza as operações caso a condição seja falsa
				else {
					conta.debitar(valor);
					conta_repo.save(conta);
				}
			}
			
			// Verifica se o tipo da transação é 'CARTAO_DE_CREDITO'
			else if(transacao.getTipo() == TipoTransacao.CARTAO_DE_CREDITO) {
				
				// Armazena o cartão pelo número presente na transacao
				Cartao cartao = cartao_repo.findByNumero(transacao.getNumero_cartao())
						.orElseThrow(() -> new NumeroNotFoundException("Não existe um cartão com esse número."));
				
				// Armazena a conta destino
				Conta conta = transacao.getConta_destino();
				
				// Verifica se o cartão possui limite suficiente pra realizar a transferência
				if(valor > cartao.getLimite()) 
					throw new TransacaoException("Você não tem limite para realizar essa transferência.");
				// Verifica se o cartão está ativado para realizar qualquer operação
				else if(cartao.getAtivado() == false)
					throw new TransacaoException("Cartão não ativado.");
				// Verifica se o usuário não está tentando transferir do cartão pra própria conta
				else if(cartao.getConta().getId() == conta.getId()) 
					throw new TransacaoException("Você não pode transferir para a própria conta.");
				// Realiza as operações caso todas as condições sejam falsas
				else {
					cartao.debitar(valor);
					conta.creditar(valor);
					cartao_repo.save(cartao);
					conta_repo.save(conta);
				}
			}
			
			// Verifica se o tipo da transação é 'PAGAMENTO_FATURA'
			else if(transacao.getTipo() == TipoTransacao.PAGAMENTO_FATURA) {
				
				// Armazena o cartão pelo número presente na transacao
				Cartao cartao = cartao_repo.findByNumero(transacao.getNumero_cartao())
						.orElseThrow(() -> new NumeroNotFoundException("Não existe um cartão com esse número."));
				
				// Verifica se existe alguma fatura a ser paga 
				if(cartao.getLimite() == 500.0) throw new TransacaoException("Sem faturas a pagar.");
				// Caso exista, as operações seguintes são executadas
				else {
					// Armazena a conta
					Conta conta = cartao.getConta();
					
					// Calcula o valor a ser creditado da conta
					valor = cartao.creditar(valor);
					
					// Verifica se há saldo suficiente para o pagamento da fatura
					if(conta.getSaldo() < valor) 
						throw new TransacaoException("Saldo insuficiente.");
					// Verifica se o cartão está ativado para realizar qualquer operação
					else if(cartao.getAtivado() == false)
						throw new TransacaoException("Cartão não ativado.");
					// Realiza as operações caso todas as condições sejam falsas
					else {
						conta.debitar(valor);
						transacao.setValor(valor);
						
						cartao_repo.save(cartao);
						conta_repo.save(conta);
					}
				}
			}
		}
	}
		
}
