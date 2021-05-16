package com.ecommerce.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.ecommerce.dto.Purchase;
import com.ecommerce.dto.PurchaseResponse;
import com.ecommerce.model.Address;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{	
	
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
		long customerId = saveCustomer(customer);
		long billingId = saveAddress(order.getBillingAddress());
		long shippingId = saveAddress(order.getShippingAddress());
		System.out.println("Customer id:" + customerId);
		System.out.println("Billing and Shipping id:" + billingId + " " + shippingId);
		
		long orderId = saveOrder(order,customerId,billingId,shippingId);
		System.out.println("order id:" + orderId);
		orderItems.forEach((item) -> {
			System.out.println("Order Item:" + item);
			saveOrderItems(item,orderId);
		});		
		
		//return response
		return new PurchaseResponse(orderTrackingNumber);
		//return null;
	}
	
	private String generateOrderTrackingNumber(){		
		//generate a random uuid
		return UUID.randomUUID().toString();		
	}
	
	public long saveCustomer(Customer customer) {
		String select_query = "Select max(id) from customer";
		Statement select_stmt;
		int i=0;
		int temp_cId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int customer_Id = select_rs.getInt(1);
			temp_cId = ++customer_Id;			
			PreparedStatement pst = connection.prepareStatement("Insert into Customer values(?,?,?,?)");
			
			pst.setLong(1,temp_cId);
			pst.setString(2,customer.getFirstName());
			pst.setString(3,customer.getLastName());
			pst.setString(4,customer.getEmail());			
			
			i = pst.executeUpdate();
			System.out.println(i + "customer records inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp_cId;
	}
	
	public long saveAddress(Address address) {
		String select_query = "Select max(id) from Address";
		Statement select_stmt;
		int i=0;
		int temp_aId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int address_Id = select_rs.getInt(1);
			temp_aId = ++address_Id;			
			PreparedStatement pst = connection.prepareStatement("Insert into Address values(?,?,?,?,?,?)");
			
			pst.setLong(1,temp_aId);
			pst.setString(2,address.getCity());
			pst.setString(3,address.getCountry());
			pst.setString(4,address.getState());
			pst.setString(5,address.getStreet());
			pst.setString(6,address.getZipCode());
			
			i = pst.executeUpdate();
			System.out.println(i + "address records inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp_aId;		
	}
	
	public long saveOrder(Order order,long customerId,long billingId,long shippingId) {
		
		Date date = new Date();
		//java.sql.Date d=new java.sql.Date(date.getTime());
		
		String select_query = "Select max(id) from orders";
		Statement select_stmt;
		int i=0;
		int temp_oId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int order_Id = select_rs.getInt(1);
			temp_oId = ++order_Id;			
			PreparedStatement pst = connection.prepareStatement("Insert into orders values(?,?,?,?,?,?,?,?,?,?)");
			
			pst.setLong(1,order_Id);
			pst.setString(2,order.getOrderTrackingNumber());
			pst.setBigDecimal(3,order.getTotalPrice());			
			pst.setInt(4,order.getTotalQuantity());
			pst.setLong(5,billingId);
			pst.setLong(6,customerId);
			pst.setLong(7,shippingId);
			pst.setString(8,order.getStatus());
			pst.setDate(9,new java.sql.Date(date.getTime()));
			pst.setDate(10,new java.sql.Date(date.getTime()));
			
			i = pst.executeUpdate();
			System.out.println(i + "order records inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp_oId;
		
	}
	public void saveOrderItems(OrderItem item, long orderId){	
		String select_query = "Select max(id) from order_item";
		Statement select_stmt;
		int i=0;
		int temp_iId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int item_Id = select_rs.getInt(1);
			temp_iId = ++item_Id;			
			PreparedStatement pst = connection.prepareStatement("Insert into order_item values(?,?,?,?,?,?)");
			
			pst.setLong(1,item_Id);
			pst.setString(2,item.getImageUrl());
			pst.setInt(3,item.getQuantity());			
			pst.setBigDecimal(4,item.getUnitPrice());
			pst.setLong(5,orderId);
			pst.setLong(6,item.getProductId());		
			
			i = pst.executeUpdate();
			System.out.println(i + "orderitem records inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
