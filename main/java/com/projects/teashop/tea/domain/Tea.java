package com.projects.teashop.tea.domain;
import java.util.List;
import java.util.Set;

import com.projects.teashop.tea.domain.status.GRAMS;
import com.projects.teashop.tea.domain.status.LeafStructure;
import com.projects.teashop.tea.domain.status.tea_type;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity (name = "tea")
@Data
@Table(name = "tea_table")
@NoArgsConstructor
@EqualsAndHashCode(of = {"Id"})
public class Tea {
	
	@Id 
	@Column(
			name = "id",
			updatable = false,
			unique = true)
	@SequenceGenerator(
			name= "tea_id_seq",
			sequenceName = "tea_id_seq",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.IDENTITY,
			generator = "tea_id_seq")
	private Long Id;
	
	
	@Column ( name = "sort", nullable = false)
	Set <tea_type> sort;
	
	@Column (name = "name", nullable = false)
	private String name;
	
	@Column (name = "region",nullable = false)
	private String region;
	
	@Column (name = "year", nullable = false)
	private Short year;
	
	@Column (name = "effect")
	private String effect;
	
	@Column (name = "taste")
	private String taste;
	
	
	//private List <String> photoURLs;
	
	
	@Column (name = "is_available", 
			columnDefinition = "boolean default true")
	private Boolean isAvailable;
	
	@Column (name = "amount")
	private Integer amount;
	
	@Column (name = "price_per_gram")
	private Double pricePerGram;

	@Column (name = "grams")
	private Set <GRAMS> grams;
	
	@ElementCollection(
			targetClass = LeafStructure.class,
			fetch = FetchType.EAGER
			)
	@CollectionTable(
			name = "leaf_structure", 
			joinColumns = @JoinColumn(
					name = "tea_id"
					)
			)
	@Enumerated(EnumType.STRING)
	@Column (name = "leaf_structure")
	private LeafStructure leaf_structure;	
	
	@Column (name = "rate")
	private Float rate;
	
	
//	@ManyToOne
//	private List <Review> reviews;
	
	@ManyToMany
	private List <Order> order;
}
