package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderItem;
import com.ecommerce.util.DBUtil;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO{
	
	Connection connection = null;
	private static List<OrderItem> orderItems;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public void saveOrderItems(OrderItem item, Long orderId) {
		// TODO Auto-generated method stub
		
		String select_query = "Select max(id) from order_item";
		Statement select_stmt;
		int i=0;
		//int temp_iId = 0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			Long item_Id = select_rs.getLong(1);
			Long temp_iId = ++item_Id;			
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

	
	
	@Override
	public List<OrderItem> getOrderItemByOrderid(Long orderId) {
		// TODO Auto-generated method stub
		String query = "Select * from order_item where order_id = ?";
		orderItems = new ArrayList<>();
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setLong(1, orderId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getLong(1));
				orderItem.setImageUrl(rs.getString(2));
				orderItem.setQuantity(rs.getInt(3));
				orderItem.setUnitPrice(rs.getBigDecimal(4));
				orderItem.setOrderId(rs.getLong(5));
				orderItem.setProductId(rs.getLong(6));
				orderItems.add(orderItem);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

}
