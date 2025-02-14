package com.projects.teashop.tea.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.teashop.tea.domain.Order;
import com.projects.teashop.tea.domain.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	@EntityGraph(attributePaths = {"tea, user, user_role"})
	Page <Order> findAll (Pageable pageable);
	
	@EntityGraph(attributePaths = {"tea, user, user_role"})
	Optional <Order> findWithRolesById (Long Id);
	
	@EntityGraph(attributePaths = {"tea"})
	Page <Order> findByUser(User user, Pageable pageable);
	
    void deleteById (Long Id);
}
