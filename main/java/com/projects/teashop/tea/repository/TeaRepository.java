package com.projects.teashop.tea.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.status.tea_type;

public interface TeaRepository extends JpaRepository <Tea, Long> {
	Page <Tea> findAll (Pageable pageable);

	Slice <Tea> findBySort (Pageable pageable, tea_type sort);
	
	List <Tea> findByName (String name);
	
	void deleteById (Long id);
}
