package com.projects.teashop.tea.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.status.tea_type;
import com.projects.teashop.tea.service.implementation.SalesServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/tea")
public class SalesController {
	private final SalesServiceImpl salesService;
	
	@GetMapping("/all/{PageNumber}")
	public Page <Tea> showAll (
			@PathVariable int PageNumber
			) 
	{
		return salesService.findAll(PageNumber);
	}
	
	@GetMapping("/{teaType}/{PageNumber}")
	public Slice <Tea> showTypes (
			@PathVariable int PageNumber,
			@PathVariable tea_type type) 
	{
		return salesService.findByType(PageNumber, type);
	}
	
	@GetMapping("/{Id}")
	public Tea findById (@PathVariable Long Id) {
		return salesService.findById(Id);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Tea addTea (@RequestBody Tea tea) {
		return salesService.addTea(tea);
	}
	
	@PatchMapping("/add/{Id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Tea updateTea (
			@PathVariable Long Id, 
			@RequestBody Tea tea) 
	{
		return salesService.changeTea(Id, tea);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTea (@RequestBody Long Id) {
		return salesService.deleteTea(Id);
	}
	
	
	
//	@GetMapping("/show/find")
//	public Page <Tea> findSpecific (int PageNumber, @RequestParam TeaRequest request) {
//		return salesService.searchTea(PageNumber, request);
//	}
	
}
