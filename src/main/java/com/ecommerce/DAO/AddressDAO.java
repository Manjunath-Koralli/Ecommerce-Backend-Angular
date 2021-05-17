package com.ecommerce.DAO;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;

@Repository
public interface AddressDAO {
	long saveAddress(Address address);
}
