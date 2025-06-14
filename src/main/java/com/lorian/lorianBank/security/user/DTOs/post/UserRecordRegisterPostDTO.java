package com.lorian.lorianBank.security.user.DTOs.post;

import com.lorian.lorianBank.cliente.DTOs.post.ClientePostDTO;
import com.lorian.lorianBank.security.user.UserRole;

public record UserRecordRegisterPostDTO(
		String username,
		String password,
		UserRole role,
		ClientePostDTO cliente) {
	// Informações que vão ser coletadas do client através de Requests de Register
}
