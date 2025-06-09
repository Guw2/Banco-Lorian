package com.lorian.lorianBank.conta;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import com.lorian.lorianBank.cartao.Cartao;
import com.lorian.lorianBank.cliente.Cliente;
import com.lorian.lorianBank.transacao.Transacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Conta implements ContaOps{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long numero;
	@Column
	private String agencia;
	@Column
	private Double saldo;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoConta tipo;
	@Column
	private Instant data_de_abertura;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private List<Transacao> transacao;
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private List<Cartao> cartoes;
	
	public Conta() {}
	
	public Conta(Long id, Long numero, String agencia, Double saldo, TipoConta tipo, Instant data_de_abertura,
			Cliente cliente, List<Transacao> transacao, List<Cartao> cartoes) {
		this.id = id;
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tipo = tipo;
		this.data_de_abertura = data_de_abertura;
		this.cliente = cliente;
		this.transacao = transacao;
		this.cartoes = cartoes;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public TipoConta getTipo() {
		return tipo;
	}
	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}
	public Instant getData_de_abertura() {
		return data_de_abertura;
	}
	public void setData_de_abertura(Instant data_de_abertura) {
		this.data_de_abertura = data_de_abertura;
	}
		
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Transacao> getTransacao() {
		return transacao;
	}

	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", numero=" + numero + ", agencia=" + agencia + ", saldo=" + saldo + ", tipo=" + tipo
				+ ", data_de_abertura=" + data_de_abertura + ", cliente=" + cliente + ", transacao=" + transacao
				+ ", cartoes=" + cartoes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, cartoes, cliente, data_de_abertura, id, numero, saldo, tipo, transacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(cartoes, other.cartoes)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(data_de_abertura, other.data_de_abertura)
				&& Objects.equals(id, other.id) && Objects.equals(numero, other.numero)
				&& Objects.equals(saldo, other.saldo) && tipo == other.tipo
				&& Objects.equals(transacao, other.transacao);
	}

	@Override
	public void debitar(Double valor) {
		setSaldo(getSaldo() - valor);
	}

	@Override
	public void acrescentar(Double valor) {
		setSaldo(getSaldo() + valor);
	}

}
