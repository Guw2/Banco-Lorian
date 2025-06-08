package com.lorian.lorianBank.cliente.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClientePostDTO(
		@NotBlank String nome,
		@NotBlank String cpf,
		@NotNull @Min(value = 18) @Positive Integer idade,
		@NotBlank String endereco,
		@NotBlank String telefone,
		@NotBlank @Email String email
		) {

}
