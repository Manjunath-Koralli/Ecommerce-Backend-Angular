package com.ecommerce.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderItem;

@Repository
public interface OrderItemDAO {
	void saveOrderItems(OrderItem item, Long orderId);
	List<OrderItem> getOrderItemByOrderid(Long orderId);
}
