package com.lorian.lorianBank.transacao.DTOs.get;

import java.time.Instant;

import com.lorian.lorianBank.transacao.TipoTransacao;

public class TransacaoGetDTO {
	
	// Informações que vão ser mostradas ao client através de Responses
	
	private Long id;
	private Double valor;
	private Instant data;
	private TipoTransacao tipo;
	private Long conta_remetente;
	private Long conta_destinatario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Instant getData() {
		return data;
	}
	public void setData(Instant data) {
		this.data = data;
	}
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	public Long getConta_remetente() {
		return conta_remetente;
	}
	public void setConta_remetente(Long conta_remetente) {
		this.conta_remetente = conta_remetente;
	}
	public Long getConta_destinatario() {
		return conta_destinatario;
	}
	public void setConta_destinatario(Long conta_destinatario) {
		this.conta_destinatario = conta_destinatario;
	}
	
}
