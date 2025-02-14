package com.projects.teashop.tea.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.teashop.tea.service.blueprint.UserService;
import com.projects.teashop.tea.domain.User;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
	private final UserService service;
	
	@PostMapping("/reg")
	public User registration (@RequestBody User user) {
		return service.addUser(user);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User getById (@PathVariable Long Id) {
		return service.findById(Id);
	}
	
	@GetMapping("/{email}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User getByEmail (@PathVariable String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/all/page/{i}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Page <User> getAll (@PathVariable int i) {
		return service.viewAllUsers(i);
	}
	
	@GetMapping("/profile-{id}")
	@PreAuthorize("hasAuthority('User')")
	public User update (
			@PathVariable Long Id, 
			@RequestBody User user) 
	{
		return service.updateUser(Id, user);
	}
}
