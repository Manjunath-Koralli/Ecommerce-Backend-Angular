package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	

	@Override
	public long saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
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
	
	
}
