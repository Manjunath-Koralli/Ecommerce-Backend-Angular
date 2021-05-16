package com.ecommerce.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.State;
import com.ecommerce.util.DBUtil;

@Repository
public class StateDAOImpl implements StateDAO{
	
	Connection connection = null;
	private static List<State> states;
	private static List<State> statesById;
	private static List<State> statesByCid;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}

	@Override
	public List<State> getAllState() {
		// TODO Auto-generated method stub
		String query = "Select * from state";
		states = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				State state = new State();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int country_id = rs.getInt(3);
				
				state.setId(id);
				state.setName(name);
				state.setCountry_id(country_id);
				states.add(state);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return states;
	}

	@Override
	public List<State> getAllStateById(int stateId) {
		// TODO Auto-generated method stub
		String query = "Select * from state where id = ?";
		statesById = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, stateId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				State state = new State();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int country_id = rs.getInt(3);
				
				state.setId(id);
				state.setName(name);
				state.setCountry_id(country_id);
				statesById.add(state);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statesById;
	}

	@Override
	public List<State> getAllStateByCid(int cId) {
		// TODO Auto-generated method stub
		String sQuery = "select * from state where country_id = ?";
		statesByCid = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sQuery);
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				State state = new State();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int country_id = rs.getInt(3);
				
				state.setId(id);
				state.setName(name);
				state.setCountry_id(country_id);
				statesByCid.add(state);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statesByCid;
	}

}
