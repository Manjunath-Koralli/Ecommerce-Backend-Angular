package com.ecommerce.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Order;

@Repository
public interface OrderDAO {
	long saveOrder(Order order,Long customerId,Long billingId,Long shippingId);
	List<Order> getOrderByCustomerId(Long customerId);
	
}
