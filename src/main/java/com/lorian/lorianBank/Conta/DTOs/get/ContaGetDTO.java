package com.lorian.lorianBank.conta.DTOs.get;

import java.util.Objects;

import com.lorian.lorianBank.cliente.DTOs.get.ClienteGetDTO;
import com.lorian.lorianBank.conta.TipoConta;

public class ContaGetDTO{

	private Long id;
	private Long numero;
	private String agencia;
	private Double saldo;
	private TipoConta tipo;
	private ClienteGetDTO cliente;

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

	public ClienteGetDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteGetDTO cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, cliente, numero, saldo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaGetDTO other = (ContaGetDTO) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(numero, other.numero) && Objects.equals(saldo, other.saldo) && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "ContaGetDTO [numero=" + numero + ", agencia=" + agencia + ", saldo=" + saldo + ", tipo=" + tipo
				+ ", cliente=" + cliente + "]";
	}
	
}
