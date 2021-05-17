package com.ecommerce.DAO;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderItem;

@Repository
public interface OrderItemDAO {
	void saveOrderItems(OrderItem item, long orderId);
}
