package com.ecommerce.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.State;

@Repository
public interface StateDAO {
	List<State> getAllState();
	List<State> getAllStateById(int id);
	List<State> getAllStateByCid(int id);
}
