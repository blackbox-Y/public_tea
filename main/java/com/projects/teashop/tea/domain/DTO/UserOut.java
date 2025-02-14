package com.projects.teashop.tea.domain.DTO;

import java.util.List;

import com.projects.teashop.tea.domain.Order;
import com.projects.teashop.tea.domain.Tea;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOut {
	private String name;
	private String surname;
	private String email;
	private String Countryl;
	private String Adress;
	
	private List<Tea> teas;
	private List<Order> orderList;
}
