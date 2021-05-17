package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderItem;
import com.ecommerce.util.DBUtil;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO{
	
	Connection connection = null;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public void saveOrderItems(OrderItem item, long orderId) {
		// TODO Auto-generated method stub
		
		String select_query = "Select max(id) from order_item";
		Statement select_stmt;
		int i=0;
		//int temp_iId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int item_Id = select_rs.getInt(1);
			int temp_iId = ++item_Id;			
			PreparedStatement pst = connection.prepareStatement("Insert into order_item values(?,?,?,?,?,?)");
			
			pst.setLong(1,temp_iId);
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
