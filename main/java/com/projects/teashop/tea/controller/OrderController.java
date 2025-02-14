package com.projects.teashop.tea.controller;


import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.teashop.tea.service.blueprint.OrderService;
import com.projects.teashop.tea.domain.Order;
import com.projects.teashop.tea.domain.User;
import com.projects.teashop.tea.domain.status.GRAMS;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
	private final OrderService orderService;
	
	@GetMapping("/my-orders/page-{pageNumber}")
	@PreAuthorize("hasAuthority('USER')")
	public Page<Order> findUserOrders (
			@RequestBody User user, 
			@PathVariable int PageNumber) 
	{
		return orderService.findByUser(user, PageNumber);
	}
	
	@GetMapping("/find-by-user/page-{pageNumber}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Order> findordersByUser (
			@RequestBody Long Id, 
			@PathVariable int PageNumber) 
	{
		return orderService.findByUserId(Id, PageNumber);
	}
	
	@PostMapping("/make-an-order")
	@PreAuthorize("hasAuthority('USER')")
	public Order createOrder (@RequestBody User user) {
		return orderService.createOrder(user);
	}
	
	@PatchMapping("/add-position/{orderId}")
	@PreAuthorize("hasAuthority('USER')")
	public String addToOrder (
			@PathVariable Long orderId,
			@RequestBody Long teaId, 
			@RequestBody GRAMS gram) 
	{	
		return orderService.addToOrder(orderId, teaId, gram);
	}
	
	@PatchMapping("/remove-position/{orderId}")
	@PreAuthorize("hasAuthority('USER')")
	public String removeFromOrder (
			@PathVariable Long orderId, 
			@RequestBody Long teaId, 
			@RequestBody GRAMS gram) 
	{
		return orderService.delteFromOrder(orderId, teaId, gram);
	}
	
	@PatchMapping("/make-an-order")
	@PreAuthorize("hasAuthority('USER')")
	public Order toOrder (@RequestBody Long Id) {
		return orderService.makeAnOrder(Id);
	}
	
	
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteById(@RequestBody Long Id) {
		return orderService.deleteById(Id);
	}
}
