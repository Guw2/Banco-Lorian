package com.lorian.lorianBank.Transacao;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.lorian.lorianBank.Conta.Conta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column
	private Double valor;
	@Column
	private Instant data = Instant.now();
	@Column
	private TipoTransacao tipo;
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	public Transacao() {}

	public Transacao(UUID id, Double valor, Instant data, TipoTransacao tipo, String descricao, Conta conta) {
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
		this.descricao = descricao;
		this.conta = conta;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", valor=" + valor + ", data=" + data + ", tipo=" + tipo + ", descricao="
				+ descricao + ", conta=" + conta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(conta, data, descricao, id, tipo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(conta, other.conta) && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id) && tipo == other.tipo
				&& Objects.equals(valor, other.valor);
	}
	
}
