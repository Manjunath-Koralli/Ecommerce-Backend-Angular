package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Order;

public class OrderHistoryResponse {
	
	//private Order order;
	private List<Order> orders;
	
	public OrderHistoryResponse(List<Order> orders) {
		super();
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
