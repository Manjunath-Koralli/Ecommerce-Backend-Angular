package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderHistoryResponse;
import com.ecommerce.model.Order;

public interface OrderHistoryService {
	//OrderHistoryResponse getOrderHistoryByEmailId(String email);
	List<Order> getOrderHistoryByEmailId(String email);
}
