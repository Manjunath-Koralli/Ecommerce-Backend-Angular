package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;
import com.ecommerce.util.DBUtil;

@Repository
public class AddressDAOImpl implements AddressDAO {
	
	Connection connection = null;
	//private static List<Address> addresses;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public Long saveAddress(Address address) 
	{
		// TODO Auto-generated method stub
		String select_query = "Select max(id) from Address";
		Statement select_stmt;
		int i=0;
		Long temp_aId = 0L;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			Long address_Id = select_rs.getLong(1);
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

	@Override
	public Address getAddressById(Long addressId) {
		// TODO Auto-generated method stub
		String query = "Select * from address where id = ?";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setLong(1, addressId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Address address = new Address();
				address.setId(rs.getLong(1));
				address.setCity(rs.getString(2));
				address.setCountry(rs.getString(3));
				address.setState(rs.getString(4));
				address.setStreet(rs.getString(5));
				address.setZipCode(rs.getString(6));
				return address;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
