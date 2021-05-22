package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.DAO.CustomerDAO;
import com.ecommerce.DAO.OrderDAO;
import com.ecommerce.dto.OrderHistoryResponse;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
	
	private CustomerDAO customerDao;
	private OrderDAO orderDao;	

	public OrderHistoryServiceImpl(OrderDAO orderDao,CustomerDAO customerDao) {
		super();
		this.customerDao = customerDao;
		this.orderDao = orderDao;		
	}

	@Override
	public List<Order> getOrderHistoryByEmailId(String email) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.getCustomerByEmailId(email);
		Long customerId = customer.getId();
		List<Order> orders = orderDao.getOrderByCustomerId(customerId);
		return orders;
	}

//	@Override
//	public OrderHistoryResponse getOrderHistoryByEmailId(String email) {
//		// TODO Auto-generated method stub
//		Customer customer = customerDao.getCustomerByEmailId(email);
//		long customerId = customer.getId();
//		List<Order> orders = orderDao.getOrderByCustomerId(customerId);
//		orders.forEach((order) -> {
//			System.out.println("Order is:" + order.getOrderTrackingNumber());
//		});
//		return new OrderHistoryResponse(orders);
//	}
	
	
}
