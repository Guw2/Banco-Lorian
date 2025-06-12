package com.lorian.lorianBank.security.user.DTOs.post;

import com.lorian.lorianBank.security.user.UserRole;

public record UserRecordRegisterPostDTO(
		String username,
		String password,
		UserRole role,
		Long cliente_id) {
	// Informações que vão ser coletadas do client através de Requests de Register
}
