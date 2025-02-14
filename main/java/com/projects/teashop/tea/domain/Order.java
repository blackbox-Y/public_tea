package com.projects.teashop.tea.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.projects.teashop.tea.domain.status.GRAMS;
import com.projects.teashop.tea.domain.status.Order_status;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Orders")
@NoArgsConstructor
@EqualsAndHashCode(of = {"Id"})
public class Order { 
		@Id 
		@Column(name = "Id")
		@SequenceGenerator(
				name = "order_id_seq", 
				sequenceName = "order_id_seq", 
				allocationSize = 1)
	    @GeneratedValue(
	    		strategy = GenerationType.SEQUENCE,
	    		generator = "order_id_seq")
	    private Long Id;
		
		@ManyToMany
		private HashMap<Tea, GRAMS> orderList = new HashMap<>();
		
		@ElementCollection(targetClass = Order_status.class, 
							fetch = FetchType.EAGER)
		@CollectionTable(name = "order_status", 
						joinColumns = @JoinColumn(name = "TEA_id"))
		@Enumerated(EnumType.STRING)
	    private Set<Order_status> status;
		
		@ManyToOne
		private User user;
		
		
		@Column(name = "total_price", nullable = false)
	    private Double totalPrice;
		
		@Column(name = "date", 
				columnDefinition = "timestamp default current_timestamp")
	    private LocalDateTime date = LocalDateTime.now();
		
		@Column(name = "name", 
				nullable = false, 
				columnDefinition = "TEXT")
		private String name;
		
		@Column(name = "surname", 
				nullable = false,
				columnDefinition = "TEXT")
		private String surname;
		
		@Column(name = "email", nullable = false)
		private String email;
		
		@Column(name = "isOrdered", updatable = false)
		private boolean isOrdered;
		
}
