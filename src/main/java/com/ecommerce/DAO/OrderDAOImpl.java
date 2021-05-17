package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Order;
import com.ecommerce.util.DBUtil;

@Repository
public class OrderDAOImpl implements OrderDAO{

	Connection connection = null;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public long saveOrder(Order order, long customerId, long billingId, long shippingId) {
		// TODO Auto-generated method stub
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
			
			pst.setLong(1,temp_oId);
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

}
