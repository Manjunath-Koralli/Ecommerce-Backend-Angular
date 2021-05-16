package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.DAO.ProductCategoryDAOImpl;
import com.ecommerce.model.ProductCategory;

@RestController
@Repository
@CrossOrigin(origins="http://localhost:4200")
public class ProductCategoryRestController {
	
	@Autowired
	private ProductCategoryDAOImpl productCategoryDAOImpl;
	
	@GetMapping("/product-category")
	public List<ProductCategory> getProductCategories(){
		return productCategoryDAOImpl.getAllProductCategory();
	}

}
