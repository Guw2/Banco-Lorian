package com.lorian.lorianBank.security.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.lorianBank.security.user.DTOs.post.UserRecordLoginPostDTO;
import com.lorian.lorianBank.security.user.DTOs.post.UserRecordRegisterPostDTO;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	private final UserService service;
	
	public UserAuthController(UserService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public String register(@RequestBody UserRecordRegisterPostDTO dto){
		return service.register(dto);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserRecordLoginPostDTO dto) {
		return service.login(dto);
	}
	
}
