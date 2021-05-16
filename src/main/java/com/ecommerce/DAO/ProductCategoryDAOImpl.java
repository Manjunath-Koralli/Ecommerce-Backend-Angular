package com.ecommerce.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductCategory;
import com.ecommerce.util.DBUtil;

@Repository
public class ProductCategoryDAOImpl implements ProductCategoryDAO{

	Connection connection = null;
	private static List<ProductCategory> productCategory;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	
	@Override
	public ProductCategory registerProductCategory(ProductCategory product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		// TODO Auto-generated method stub
		String query = "Select * from product_category";
		productCategory = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductCategory category = new ProductCategory();
				Long catId = rs.getLong(1);
				String catName =rs.getString(2);
				
				category.setId(catId);
				category.setCategory_name(catName);
				
				productCategory.add(category);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productCategory;
		
	}

	@Override
	public ProductCategory updateProduct(Long productCategoryId, ProductCategory product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteProduct(Long productCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
