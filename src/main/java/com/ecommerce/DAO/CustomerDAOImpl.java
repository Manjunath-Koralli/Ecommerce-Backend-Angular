package com.ecommerce.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Customer;
import com.ecommerce.util.DBUtil;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	
	Connection connection = null;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	private List<Customer> customers = new ArrayList<Customer>();
	
	
}
