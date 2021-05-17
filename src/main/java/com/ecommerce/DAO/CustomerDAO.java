package com.ecommerce.DAO;


import org.springframework.stereotype.Repository;
import com.ecommerce.model.Customer;

@Repository
public interface CustomerDAO {	
	
	long saveCustomer(Customer customer);
}
