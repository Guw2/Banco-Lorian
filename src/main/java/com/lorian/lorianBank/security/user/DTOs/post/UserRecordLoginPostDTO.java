package com.lorian.lorianBank.security.user.DTOs.post;

public record UserRecordLoginPostDTO(String username, String password) {
	// Informações que vão ser coletadas do client através de Requests de Login
}
