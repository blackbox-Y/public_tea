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
	
//	
//	@EntityGraph(attributePaths = {"perfumes", "user", "user.roles"})
//    @Query("SELECT orders FROM Order orders " 
//    		+ "WHERE (coalesce(:name, null) IS NULL OR order.name IN name)"
//			+ "AND (coalesce(:surname, null) IS NULL OR order.surname IN surname)"
//			+ "AND (coalesce(:email, null) IS NULL OR order.email IN email)"
//			+ "AND (coalesce(:priceMin, null) IS NULL OR order.totalPrice BETWEEN :priceMin AND :priceMax)"
//			+ "ORDER BY Id ASC")
//    Page<Order> searchOrders(
//    		Pageable pageable, 
//    		@Param("name") String name,
//    		@Param("surname") String surname,
//    		@Param("email") String email,
//    		@Param("priceMin") Integer priceMin,
//    		@Param("priceMax") Integer priceMax
//    		);

//	@EntityGraph(attributePaths = {"perfumes", "user", "user.roles"})
//    @Query("SELECT orders FROM Order orders " 
//    		+ "WHERE (coalesce(:name, null) IS NULL OR order.name IN name)"
//			+ "AND (coalesce(:surname, null) IS NULL OR order.surname IN surname)"
//			+ "AND (coalesce(:email, null) IS NULL OR order.email IN email)"
//			+ "AND (coalesce(:priceMin, null) IS NULL OR order.totalPrice BETWEEN :priceMin AND :priceMax)"
//			+ "AND (coalesce(:user, null) IS NULL OR order.user IN user)"
//			+ "ORDER BY Id ASC")
//    Page<Order> searchUserOrders(
//    		@Param("name") String name,
//    		@Param("surname") String surname,
//    		@Param("email") String email,
//    		@Param("priceMin") Integer priceMin,
//    		@Param("priceMax") Integer pricemax,
//    		@Param("user") User user
//    		);
//    
    void deleteById (Long Id);
}