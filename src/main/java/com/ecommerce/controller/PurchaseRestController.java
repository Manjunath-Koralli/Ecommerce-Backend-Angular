package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dto.Purchase;
import com.ecommerce.dto.PurchaseResponse;
import com.ecommerce.service.CheckoutService;


@RestController
@Repository
@CrossOrigin(origins="http://localhost:4200")
public class PurchaseRestController {
	
	private CheckoutService checkoutService;	
	
	@Autowired
	public PurchaseRestController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping(value = "/api/checkout/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		
		//System.out.println("purchase object is:" + purchase);
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		return purchaseResponse;
	}
	

}
