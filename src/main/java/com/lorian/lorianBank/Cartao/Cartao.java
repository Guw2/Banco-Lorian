package com.lorian.lorianBank.cartao;

import java.time.Instant;
import java.util.Objects;
import com.lorian.lorianBank.cliente.Cliente;
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
public class Cartao implements CartaoOps {
	
	private static final Double TAXA = 0.12;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String numero;
	@Column
	private Integer cvv;
	@Column
	private Instant validade;
	@Column
	private Double limite;
	@Column
	@Enumerated(EnumType.STRING)
	private BandeiraCartao bandeira;
	@Column
	private Boolean ativado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	public Cartao() {}

	public Cartao(Long id, String numero, Integer cvv, Instant validade, Double limite, BandeiraCartao bandeira,
			Cliente cliente, Conta conta) {
		this.id = id;
		this.numero = numero;
		this.cvv = cvv;
		this.validade = validade;
		this.limite = limite;
		this.bandeira = bandeira;
		this.cliente = cliente;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Instant getValidade() {
		return validade;
	}

	public void setValidade(Instant validade) {
		this.validade = validade;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}

	public Boolean getAtivado() {
		return ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", cvv=" + cvv + ", validade=" + validade + ", limite="
				+ limite + ", bandeira=" + bandeira + ", cliente=" + cliente + ", conta=" + conta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bandeira, cliente, conta, cvv, id, limite, numero, validade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return bandeira == other.bandeira && Objects.equals(cliente, other.cliente)
				&& Objects.equals(conta, other.conta) && Objects.equals(cvv, other.cvv) && Objects.equals(id, other.id)
				&& Objects.equals(limite, other.limite) && Objects.equals(numero, other.numero)
				&& Objects.equals(validade, other.validade);
	}

	@Override
	public Double debitar(Double valor) {
		valor = valor + (valor*TAXA);
		setLimite(getLimite() - valor);
		return valor;
	}

	@Override
	public Double creditar(Double valor) {
		if(getLimite() + valor < 500.0) {
			setLimite(getLimite() + valor);
			return valor;
		}
		else {
			var valor_diff = 500.0 - getLimite();
			setLimite(500.0);
			return valor_diff;
		}
	}
	
}
