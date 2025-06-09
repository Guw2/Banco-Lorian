package com.lorian.lorianBank.cliente.DTOs;

import java.util.Objects;

public class ClienteGetDTO {

	private Long id;
	private String nome;
	private Integer idade;
	private String telefone;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, idade, nome, telefone);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteGetDTO other = (ClienteGetDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(idade, other.idade)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
	
	@Override
	public String toString() {
		return "ClienteGetDTO [nome=" + nome + ", idade=" + idade + ", telefone=" + telefone + ", email=" + email + "]";
	}
	
}
