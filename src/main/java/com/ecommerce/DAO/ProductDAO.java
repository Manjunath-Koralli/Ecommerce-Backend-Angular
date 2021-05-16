package com.ecommerce.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Product;

@Repository
public interface ProductDAO {
	Product registerProduct(Product product);
	List<Product> getAllProduct();
	List<Product> getAllProductByCatId(Long categoryId);
	List<Product> getAllProductByKeyWord(String keyWord);
	Product getProductById(Long productId);
	Product updateProduct(Long productId,Product product);
	Long deleteProduct(Long productId);
}
