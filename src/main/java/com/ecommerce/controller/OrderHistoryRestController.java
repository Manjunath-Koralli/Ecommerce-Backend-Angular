package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.DAO.CustomerDAOImpl;
import com.ecommerce.DAO.OrderDAOImpl;
import com.ecommerce.dto.OrderHistoryResponse;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderHistoryService;


@RestController
@Repository
@CrossOrigin(origins="http://localhost:4200")
public class OrderHistoryRestController {
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@Autowired
	private CustomerDAOImpl customerDAOImpl;
	
	@Autowired
	private OrderDAOImpl orderDAOImpl;	
	
	@GetMapping("/api/orders/search/findCustomerByEmailDao")
	public @ResponseBody ResponseEntity<?> getOrdersByCustomerEmailDao(@RequestParam("email") String email){
		System.out.println("From Controller:" + email);
		Customer customer = customerDAOImpl.getCustomerByEmailId(email);	
		List<Order> orders = orderDAOImpl.getOrderByCustomerId(customer.getId());		
		if (orders == null) {
			return new ResponseEntity<String>("No orders found for email id " + email , HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
//	@GetMapping("/api/orders/search/findCustomerByEmailService")
//	public OrderHistoryResponse getOrdersByCustomerEmailService(@RequestParam("email") String email){
//		System.out.println("From Controller:" + email);
//		OrderHistoryResponse orderHistoryResponse = orderHistoryService.getOrderHistoryByEmailId(email);
//		return orderHistoryResponse;	
//	}	
	
	@GetMapping("/api/orders/search/findCustomerByEmailService")
	public  @ResponseBody ResponseEntity<?>  getOrdersByCustomerEmailService(@RequestParam("email") String email){
		System.out.println("From Controller:" + email);
		List<Order> orders = orderHistoryService.getOrderHistoryByEmailId(email);
		if (orders == null) {
			return new ResponseEntity<String>("No orders found for email id " + email , HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

}
