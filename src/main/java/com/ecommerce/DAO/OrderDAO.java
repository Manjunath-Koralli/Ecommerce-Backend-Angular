package com.ecommerce.DAO;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Order;

@Repository
public interface OrderDAO {
	long saveOrder(Order order,long customerId,long billingId,long shippingId);
}
