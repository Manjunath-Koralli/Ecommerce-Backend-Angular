package com.ecommerce.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Country;

@Repository
public interface CountryDAO {
	List<Country> getAllCountries();
	List<Country> getCountriesById(int countryId);
	int getCountryIdByCode(String cCode);
}
