package com.projects.teashop.tea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.teashop.tea.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
	@EntityGraph(attributePaths = "user_role")
	Page <User> findAll(Pageable pageable);
	
	@EntityGraph(attributePaths = "user_role")
	User findByEmail(String email);
	
	@EntityGraph(attributePaths = "user_role")
	Page <User> findByCity(Pageable pageable, String city);
	
//	@EntityGraph(attributePaths = "user_role")
//	@Query("SELECT user FROM user_table "
//			+ "WHERE (coalesce(:name, null) IS NULL OR user.name IN name)"
//			+ "AND (coalesce(:surname, null) IS NULL OR user.surname IN surname)"
//			+ "AND (coalesce(:coutry, null) IS NULL OR user.country IN country)"
//			+ "ORDER BY Id ASC")
//	Page <User> searchRequest (
//			@Param("name") String name, 
//			@Param("surname")String surname,
//			@Param("country") String country
//			);
}

