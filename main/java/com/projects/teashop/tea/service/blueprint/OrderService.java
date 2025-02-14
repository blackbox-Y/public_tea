package com.projects.teashop.tea.service.blueprint;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.projects.teashop.tea.domain.Order;
import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.User;
import com.projects.teashop.tea.domain.DTO.OrderSearchRequest;
import com.projects.teashop.tea.domain.status.GRAMS;

public interface OrderService {
	Order findById(Long Id);

	Page <Order> findByUser (User user, int pagenumber);
	Page <Order> findByUserId (Long Id, int pagenumber);
//	Page <Order> searchOrders (int pagenumber, OrderSearchRequest request);
	
	Order createOrder (User user);
	
	String addToOrder(Long orderId, Long teaId, GRAMS gram);
	String delteFromOrder (Long orderId, Long teaId, GRAMS gram);
	Double totalPrice (HashMap<Tea, GRAMS> orderList);

	Order makeAnOrder (Long orderId);
	
	String deleteById (Long Id);
	
	
}
