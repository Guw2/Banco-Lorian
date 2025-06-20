package com.lorian.lorianBank.transacao;

import java.time.Instant;
import java.util.Objects;
import com.lorian.lorianBank.conta.Conta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Double valor;
	@Column
	private Instant data;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	@Column
	private String descricao;
	
	@Column
	private String numero_cartao = "N/A";
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name = "conta_destino_id")
	private Conta conta_destino;
	

	public Transacao() {}


	public Transacao(Long id, Double valor, Instant data, TipoTransacao tipo, String descricao, String numero_cartao,
			Conta conta, Conta conta_destino) {
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
		this.descricao = descricao;
		this.numero_cartao = numero_cartao;
		this.conta = conta;
		this.conta_destino = conta_destino;
	}

	public String getNumero_cartao() {
		return numero_cartao;
	}

	public void setNumero_cartao(String numero_cartao) {
		this.numero_cartao = numero_cartao;
	}

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
	
	public Conta getConta_destino() {
		return conta_destino;
	}

	public void setConta_destino(Conta conta_destino) {
		this.conta_destino = conta_destino;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", valor=" + valor + ", data=" + data + ", tipo=" + tipo + ", descricao="
				+ descricao + ", conta=" + conta + ", conta_destino=" + conta_destino + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(conta, conta_destino, data, descricao, id, tipo, valor);
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
		return Objects.equals(conta, other.conta) && Objects.equals(conta_destino, other.conta_destino)
				&& Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && tipo == other.tipo && Objects.equals(valor, other.valor);
	}

}
