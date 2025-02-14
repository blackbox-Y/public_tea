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
	
//	@Query("SELECT tea FROM Tea tea " +
//            "WHERE (coalesce(:name, null) IS NULL OR tea.name IN :name) " +
////            "AND (coalesce(:sort, null) IS NULL OR tea.sort IN :sort) " +
//            "AND (coalesce(:region, null) IS NULL OR tea.region IN :region) " +
//            "AND (coalesce(:year, null) IS NULL OR tea.year >= :year) " +
//            "ORDER BY perfume.price ASC")
//	Page <Tea> searchRequest (
//			Pageable pageable,
//			@Param("name") String name,
////			@Param("sort") Set<tea_type> sort,
//			@Param("region") String region,
//			@Param("year") Short year
//			);
	
	void deleteById (Long id);
}
