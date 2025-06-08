package com.lorian.lorianBank.cartao.DTOs;

import java.util.Objects;

import com.lorian.lorianBank.cartao.BandeiraCartao;

public class CartaoGetDTO {
	private String numero; 
	private Double limite; 
	private BandeiraCartao bandeira;
	private String dono;
	private Long conta;
	
	public CartaoGetDTO() {}

	public CartaoGetDTO(String numero, Double limite, BandeiraCartao bandeira, String dono, Long conta) {
		this.numero = numero;
		this.limite = limite;
		this.bandeira = bandeira;
		this.dono = dono;
		this.conta = conta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public Long getConta() {
		return conta;
	}

	public void setConta(Long conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bandeira, conta, dono, limite, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoGetDTO other = (CartaoGetDTO) obj;
		return bandeira == other.bandeira && Objects.equals(conta, other.conta) && Objects.equals(dono, other.dono)
				&& Objects.equals(limite, other.limite) && Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "CartaoGetDTO [numero=" + numero + ", limite=" + limite + ", bandeira=" + bandeira + ", dono=" + dono
				+ ", conta=" + conta + "]";
	}
	
}