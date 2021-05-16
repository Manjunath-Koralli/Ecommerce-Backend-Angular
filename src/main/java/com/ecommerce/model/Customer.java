package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class Customer {

	private Long id;
	//private UUID id;
	private String firstName;
	private String lastName;
	private String email;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private Set<Order> orders = new HashSet<>();
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public void save(Order order) {
		if(order != null) {
			if(orders == null) {
				orders = new HashSet<>();
			}
			orders.add(order);
			order.setCustomer(this);
		}
	}
	
//	public Customer(UUID id, String firstName, String lastName, String email) {
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;		
//	}
}
