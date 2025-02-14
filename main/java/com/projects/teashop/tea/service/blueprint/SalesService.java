package com.projects.teashop.tea.service.blueprint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.DTO.TeaRequest;
import com.projects.teashop.tea.domain.status.tea_type;

public interface SalesService {
	public Page <Tea> findAll (int pageNumber);
	
	public Slice <Tea> findByType (int pageNumber, tea_type type);
	
	public Tea addTea (Tea tea);
	
	public Tea findById (Long Id);
	
	public Tea changeTea (Long Id, Tea tea);

	public String deleteTea (Long Id);	
}
