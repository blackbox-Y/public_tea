package com.projects.teashop.tea.domain;

import java.util.List;
import java.util.Set;

import com.projects.teashop.tea.domain.status.Role;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity (name = "user")
@Data
@Table(
	name = "user_table",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "email_unique_constrains", 
			columnNames = {"email"})
	}
)
@NoArgsConstructor
@EqualsAndHashCode(of = {"Id"})
public class User {	
	@Id 
	@Column(
		name = "Id",
		updatable = false,
		unique = true)	
	@SequenceGenerator(
		name = "users_id_seq", 
		sequenceName = "users_id_seq", 
		allocationSize = 1)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "users_id_seq")
	private Long Id;	
	@Column(
		name = "name", 
		nullable = false, 
		columnDefinition = "TEXT")
	private String name;
	
	@Column(
		name = "surname", 
		nullable = false, 
		columnDefinition = "TEXT")
	private String surname;
	
	@Column(
		name = "email", 
		nullable = false)
	private String email;
	
	
	@Column(
		name = "country", 
		columnDefinition = "TEXT")
	private String Country;
			
	@Column(name = "city", columnDefinition = "TEXT")
	private String city;
	
	@Column(name = "adress")
	private String Adress;
	
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	
	@CollectionTable(name = "user_role", 
			 joinColumns = @JoinColumn(
					name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	
	@ManyToMany
	private List<Tea> teas;
	
	@ManyToMany
	private List<Order> orderList;
}



