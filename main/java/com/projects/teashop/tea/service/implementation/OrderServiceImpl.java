package com.projects.teashop.tea.service.implementation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projects.teashop.tea.constans.ErrorMessage;
import com.projects.teashop.tea.domain.Order;
import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.User;
import com.projects.teashop.tea.domain.DTO.OrderSearchRequest;
import com.projects.teashop.tea.domain.status.GRAMS;
import com.projects.teashop.tea.repository.OrderRepository;
import com.projects.teashop.tea.repository.UserRepository;
import com.projects.teashop.tea.service.blueprint.OrderService;
import com.projects.teashop.tea.service.blueprint.SalesService;
import com.projects.teashop.tea.service.blueprint.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
	private final OrderRepository orderRepo;
	private final UserService userService; 
	private final SalesService salesService;
	
	@Override
	public Order findById(Long Id) {
		return orderRepo.findById(Id).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						ErrorMessage.ORDER_NOT_FOUND
						)
				);
	}

	@Override
	public Page<Order> findByUser(User user, int pagenumber) {
		Pageable pageable = PageRequest.of(pagenumber, 10); 
		return orderRepo.findByUser(user, pageable);
	}
	
	@Override
	public Page<Order> findByUserId(Long Id, int pagenumber) {
		return findByUser(userService.findById(Id), pagenumber);
	}	

	@Override
	public Order createOrder(User user) {
		Order order = new Order();
		order.setOrderList(new HashMap<Tea, GRAMS>());
		order.setUser(user);
		order.setDate(LocalDateTime.now());
		order.setName(user.getName());
		order.setSurname(user.getSurname());
		order.setEmail(user.getEmail());
		orderRepo.save(order);
		return order;
	}

	@Override
	public String addToOrder(Long orderId, Long teaId, GRAMS gram) {
		if (!findById(orderId).isOrdered()) {
			Tea tea2buy = salesService.findById(teaId);
			findById(orderId).getOrderList().put(tea2buy, gram);
			findById(orderId).setTotalPrice(totalPrice(findById(orderId).getOrderList()));
			return tea2buy.getName()+" is added to " + orderId.toString()+ " order";
		} else return "You cannot change this order";
	}

	@Override
	public String delteFromOrder (Long orderId, Long teaId, GRAMS gram) {
		if (!findById(orderId).isOrdered()) {
			findById(orderId).getOrderList().remove(teaId, gram);
			String tea = salesService.findById(teaId).getName();
			findById(orderId).setTotalPrice(totalPrice(findById(orderId).getOrderList()));			
			return tea + "is removed from " + orderId.toString() + " order";
		} else return "You cannot change this order";
	}


	@Override
	public Double totalPrice (HashMap<Tea, GRAMS> orderList) {
		Double totalPrice = orderList
				.entrySet().stream().mapToDouble(
						entry -> 
						entry.getKey().getPricePerGram() *
						entry.getValue().getWeightInGrams())
				.sum();
		return totalPrice;
	}

	@Override
	public Order makeAnOrder(Long orderId) {
		findById(orderId).setOrdered(true);
		return findById(orderId);
	}
	
	@Override
	@Transactional
	public String deleteById(Long Id) {
		orderRepo.deleteById(Id);
		return Id.toString()+ " is deleted";
	}
}
