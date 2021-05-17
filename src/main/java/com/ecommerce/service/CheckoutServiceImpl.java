package com.ecommerce.service;

import java.sql.Connection;

import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.ecommerce.DAO.AddressDAO;
import com.ecommerce.DAO.CustomerDAO;
import com.ecommerce.DAO.OrderDAO;
import com.ecommerce.DAO.OrderItemDAO;
import com.ecommerce.dto.Purchase;
import com.ecommerce.dto.PurchaseResponse;
import com.ecommerce.model.Address;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{	
	
	private CustomerDAO customerDAO;
	private AddressDAO addressDAO;
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	
	public CheckoutServiceImpl(CustomerDAO customerDAO, AddressDAO addressDAO, OrderDAO orderDAO,OrderItemDAO orderItemDAO) {
		super();
		this.customerDAO = customerDAO;
		this.addressDAO = addressDAO;
		this.orderDAO = orderDAO;
		this.orderItemDAO = orderItemDAO;
	}
	
	Connection connection = null;
	{
		connection = com.ecommerce.util.DBUtil.getConnection();
		System.out.println(connection);
	}

	@Override	
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// TODO Auto-generated method stub
		
		//retrieve the order info from dto
		Order order = purchase.getOrder();
		
		//generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderitems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		//populate order with billing and shipping address
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		Customer customer = purchase.getCustomer();
		customer.save(order);				
		
		//save to database	
		//long customerId = saveCustomer(customer);
		long customerId = customerDAO.saveCustomer(customer);
		long billingId = addressDAO.saveAddress(order.getBillingAddress());
		long shippingId = addressDAO.saveAddress(order.getShippingAddress());
		System.out.println("Customer id:" + customerId);
		System.out.println("Billing and Shipping id:" + billingId + " " + shippingId);
		
		long orderId = orderDAO.saveOrder(order,customerId,billingId,shippingId);
		System.out.println("order id:" + orderId);
		orderItems.forEach((item) -> {
			System.out.println("Order Item:" + item);
			orderItemDAO.saveOrderItems(item,orderId);
		});		
		
		//return response
		return new PurchaseResponse(orderTrackingNumber);
		//return null;
	}
	
	private String generateOrderTrackingNumber(){		
		//generate a random uuid
		return UUID.randomUUID().toString();		
	}

}
