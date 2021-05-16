package com.ecommerce.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Country;
import com.ecommerce.util.DBUtil;


@Repository
public class CountryDAOImpl implements CountryDAO{

	Connection connection = null;
	private static List<Country> countries;
	private static List<Country> countriesById;
	
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public List<Country> getAllCountries() {
		// TODO Auto-generated method stub
		String query = "Select * from country";
		countries = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				int id = rs.getInt(1);
				String code = rs.getString(2);
				String name = rs.getString(3);
				
				country.setId(id);
				country.setCode(code);
				country.setName(name);
				countries.add(country);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}

	@Override
	public List<Country> getCountriesById(int countryId) {
		// TODO Auto-generated method stub
		String query = "Select * from country where id = ?";
		countriesById = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, countryId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				int id = rs.getInt(1);
				String code = rs.getString(2);
				String name = rs.getString(3);
				
				country.setId(id);
				country.setCode(code);
				country.setName(name);
				countriesById.add(country);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countriesById;
	}

	@Override
	public int getCountryIdByCode(String cCode) {
		// TODO Auto-generated method stub
		String cQuery = "select id from country where code = ?";
		
		PreparedStatement cps;
		ResultSet rs;
		int id = -1;
		try {
			cps = connection.prepareStatement(cQuery);
			cps.setString(1, cCode);
			rs = cps.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
