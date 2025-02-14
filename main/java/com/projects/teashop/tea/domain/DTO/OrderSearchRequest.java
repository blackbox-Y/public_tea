package com.projects.teashop.tea.domain.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.projects.teashop.tea.domain.Tea;
import com.projects.teashop.tea.domain.User;
import com.projects.teashop.tea.domain.status.Order_status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchRequest {
	private User user;
	private String name;
	private String surname;
	private Integer priceMin;
	private Integer priceMax;
	private LocalDateTime date;
	private String email;
	private Set<Order_status> status;
	private List<Tea> orderList;
}
