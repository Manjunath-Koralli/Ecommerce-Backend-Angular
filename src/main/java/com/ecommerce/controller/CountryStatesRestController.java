package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.DAO.CountryDAOImpl;
import com.ecommerce.DAO.StateDAOImpl;
import com.ecommerce.model.Country;
import com.ecommerce.model.State;

@RestController
@Repository
@CrossOrigin(origins="http://localhost:4200")
public class CountryStatesRestController {
	
	@Autowired
	private CountryDAOImpl countryDaoImpl;
	
	@Autowired
	private StateDAOImpl stateDaoImpl;
	
	
	@GetMapping("/countries")
	public @ResponseBody ResponseEntity<?> getCountries(){
		List<Country> countries = countryDaoImpl.getAllCountries();
		if (countries == null) {
			return new ResponseEntity<String>("No countries found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List>(countries, HttpStatus.OK);
		
	}
	
	@GetMapping("/countries/{id}")
	public @ResponseBody ResponseEntity  getCountriesById(@PathVariable int id){
		List<Country> countries = countryDaoImpl.getCountriesById(id);
		if (countries == null) {
			return new ResponseEntity<String>("No countries found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List>(countries, HttpStatus.OK);
	}
	
	@GetMapping("/states")
	public @ResponseBody ResponseEntity  getStates(){
		List<State> states = stateDaoImpl.getAllState();
		if (states == null) {
			return new ResponseEntity<String>("No states found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List>(states, HttpStatus.OK);
	}
	
	
	@GetMapping("/states/{id}")
	public @ResponseBody ResponseEntity  getStatesById(@PathVariable int id){
		List<State> states = stateDaoImpl.getAllStateById(id);
		if (states == null) {
			return new ResponseEntity<String>("No states found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List>(states, HttpStatus.OK);
	}
	
	@GetMapping("/states/search/{code}")
	public @ResponseBody ResponseEntity  getStatesByCode(@PathVariable String code){
		int id = countryDaoImpl.getCountryIdByCode(code);
		System.out.println(id);
		List<State> states = stateDaoImpl.getAllStateByCid(id);
		if (states == null) {
			return new ResponseEntity<String>("No states found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List>(states, HttpStatus.OK);
	}
	
	

}
