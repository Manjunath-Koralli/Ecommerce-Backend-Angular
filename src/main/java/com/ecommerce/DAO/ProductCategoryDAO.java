package com.ecommerce.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductCategory;

@Repository
public interface ProductCategoryDAO {
	ProductCategory registerProductCategory(ProductCategory product);
	List<ProductCategory> getAllProductCategory();
	ProductCategory updateProduct(Long productCategoryId,ProductCategory product);
	Long deleteProduct(Long productCategoryId);
}
