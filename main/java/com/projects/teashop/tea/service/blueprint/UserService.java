package com.projects.teashop.tea.service.blueprint;

import java.util.List;

import org.springframework.data.domain.Page;

import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.User;

public interface UserService {
	User addUser (User user);
	User findById (Long Id);
	Page <User> viewAllUsers (int pageNumber);
	User updateUser (Long Id, User user);
	User findByEmail (String email);
}
