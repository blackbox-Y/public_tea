package com.projects.teashop.tea.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projects.teashop.tea.domain.User;
import com.projects.teashop.tea.repository.UserRepository;
import com.projects.teashop.tea.service.blueprint.UserService;
import com.projects.teashop.tea.constans.ErrorMessage;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
	
	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public Page<User> viewAllUsers(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 12);
		return repo.findAll(pageable);
	}

	@Override
	public User findById(Long Id) {
		return repo.findById(Id).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						ErrorMessage.USER_NOT_FOUND
						)
				);
	}

	@Override
	public User findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public User updateUser(Long Id, User user) {
		findById(Id).setName(user.getName());
		findById(Id).setSurname(user.getSurname());
		findById(Id).setEmail(user.getEmail());
		findById(Id).setCountry(user.getCountry());
		findById(Id).setCity(user.getCity());
		findById(Id).setAdress(user.getAdress());
		return findById(Id);
	}

}
