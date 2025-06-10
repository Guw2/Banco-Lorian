package com.lorian.lorianBank.cartao.DTOs.get;

import java.util.Objects;

import com.lorian.lorianBank.cartao.BandeiraCartao;

public class CartaoGetDTO {
	
	// Informações que vão ser mostradas ao client através de Responses
	
	private Long id;
	private String numero; 
	private Double limite; 
	private BandeiraCartao bandeira;
	private Boolean ativado;
	private String dono;
	private Long conta;
	
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