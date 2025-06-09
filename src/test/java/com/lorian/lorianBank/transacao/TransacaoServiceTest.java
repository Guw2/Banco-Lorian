package com.lorian.lorianBank.transacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lorian.lorianBank.conta.ContaRepository;
import com.lorian.lorianBank.transacao.DTOs.get.TransacaoGetDTO;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
	
	@Mock
	private TransacaoRepository transacao_repo;
	@Mock
	private ContaRepository conta_repo;
	@Mock
	private TransacaoMapper mapper;
	@InjectMocks
	private TransacaoService service;
	
	@Test
	void shouldGetAllTransacoes() {
		
		Transacao t1 = new Transacao();
		t1.setId(1L);
		t1.setValor(500.0);
		t1.setData(Instant.now());
		
		TransacaoGetDTO td1 = new TransacaoGetDTO();
		td1.setId(t1.getId());
		td1.setValor(t1.getValor());
		td1.setData(t1.getData());
		
		Transacao t2 = new Transacao();
		t2.setId(2L);
		t2.setValor(550.0);
		t2.setData(Instant.now());
		
		TransacaoGetDTO td2 = new TransacaoGetDTO();
		td2.setId(t2.getId());
		td2.setValor(t2.getValor());
		td2.setData(t2.getData());
		
		List<Transacao> list = List.of(t1, t2);
		
		when(transacao_repo.findAll()).thenReturn(list);
		when(mapper.transacaoToGetDto(t1)).thenReturn(td1);
		when(mapper.transacaoToGetDto(t2)).thenReturn(td2);
		
		assertThat(service.getAllTransacoes()).isEqualTo(List.of(td1, td2));
		
	}
	
}
