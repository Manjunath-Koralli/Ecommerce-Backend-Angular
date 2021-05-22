package com.ecommerce.DAO;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;

@Repository
public interface AddressDAO {
	Long saveAddress(Address address);
	//List<Address> getAddressById(long addressId);
	Address getAddressById(Long addressId);
}
