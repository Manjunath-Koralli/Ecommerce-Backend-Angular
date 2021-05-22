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
import com.ecommerce.DAO.ProductDAOImpl;
import com.ecommerce.model.Product;

@RestController
@Repository
@CrossOrigin(origins="http://localhost:4200")
public class ProductRestController {
	
	@Autowired
	private ProductDAOImpl productDaoImpl;
	
	@GetMapping("/products")
	public @ResponseBody ResponseEntity<?> getProducts(){
		List<Product> products = productDaoImpl.getAllProduct();
		if (products == null) {
			return new ResponseEntity<String>("No Products found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);		
	}
	
	@GetMapping("/category/{id}")
	public @ResponseBody ResponseEntity<?> getProductsByCat(@PathVariable Long id){
		
		//@ResponseBody ResponseEntity List<Product>
		System.out.println("From Controller:" + id);
		List<Product> products =  productDaoImpl.getAllProductByCatId(id);
		if (products == null) {
			return new ResponseEntity<String>("No Products found for category id " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		//return productDaoImpl.getAllProductByCat(id);
	}
	
	@GetMapping("/products/search/{keyword}")
	public @ResponseBody ResponseEntity<?> getProductsByKeyWord(@PathVariable String keyword){
		
		//@ResponseBody ResponseEntity List<Product>
		System.out.println("From Controller:" + keyword);
		List<Product> products =  productDaoImpl.getAllProductByKeyWord(keyword);
		if (products == null) {
			return new ResponseEntity<String>("No Products found for category id " + keyword, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		//return productDaoImpl.getAllProductByCat(id);
	}
	
	@GetMapping("/product/{id}")
	public @ResponseBody ResponseEntity<?> getProductById(@PathVariable Long id){
		
		//@ResponseBody ResponseEntity List<Product>
		System.out.println("From Controller:" + id);
		Product product =  productDaoImpl.getProductById(id);
		if (product == null) {
			return new ResponseEntity<String>("No Products found for category id " + id, HttpStatus.NOT_FOUND);
		}
		//System.out.println(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	
}
