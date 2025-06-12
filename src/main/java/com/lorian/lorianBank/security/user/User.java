package com.lorian.lorianBank.security.user;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lorian.lorianBank.cliente.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String user;
	@Column
	private String pass;
	@Column
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@OneToOne
	@JoinColumn(name = "cliente_id", unique = true)
	private Cliente cliente;

	public User() {}

	public User(Long id, String user, String pass, UserRole role, Cliente cliente) {
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.role = role;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, id, pass, role, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(id, other.id)
				&& Objects.equals(pass, other.pass) && role == other.role
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + user + ", password=" + pass + ", role=" + role + ", cliente="
				+ cliente + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(getRole() == UserRole.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return getPass();
	}

	@Override
	public String getUsername() {
		return getUser();
	}
	
}
