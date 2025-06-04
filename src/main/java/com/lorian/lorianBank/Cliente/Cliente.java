package com.lorian.lorianBank.Cliente;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.lorian.lorianBank.Cartao.Cartao;
import com.lorian.lorianBank.Conta.Conta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column
	private String nome;
	@Column
	private String cpf;
	@Column
	private Integer idade;
	@Column
	private String endereco;
	@Column
	private String telefone;
	@Column
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Conta> contas;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Cartao> cartoes;
	
	public Cliente() {
	}
	
	public Cliente(UUID id, String nome, String cpf, Integer idade, String endereco, String telefone, String email,
			List<Conta> contas, List<Cartao> cartoes) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.contas = contas;
		this.cartoes = cartoes;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public List<Conta> getConta() {
		return contas;
	}

	public void setConta(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + ", contas=" + contas + ", cartoes=" + cartoes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartoes, contas, cpf, email, endereco, id, idade, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cartoes, other.cartoes) && Objects.equals(contas, other.contas)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(id, other.id)
				&& Objects.equals(idade, other.idade) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}

}
