package com.ecommerce.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Product;
import com.ecommerce.util.DBUtil;

@Repository
public class ProductDAOImpl implements ProductDAO {

	Connection connection = null;
	private static List<Product> products;
	private static List<Product> productsByCat;
	private static List<Product> productsByKey;
	private static Product productById;
	{
		connection = DBUtil.getConnection();
		//System.out.println(connection);
	}
	@Override
	public Product registerProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		String query = "Select * from product";
		products = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				Long id = rs.getLong(1);
				String sku =rs.getString(2);
				String name =rs.getString(3);
				String description = rs.getString(4);
				BigDecimal unitPrice = rs.getBigDecimal(5);
				String imageUrl = rs.getString(6);
				boolean active = rs.getBoolean(7);
				int unitsInStock = rs.getInt(8);
				Date dateCreated =rs.getDate(9);
				Date lastUpdated = rs.getDate(10);
				
				product.setId(id);
				product.setSku(sku);
				product.setName(name);
				product.setDescription(description);
				product.setUnitPrice(unitPrice);
				product.setImageUrl(imageUrl);
				product.setActive(active);
				product.setUnitsInStock(unitsInStock);
				product.setDateCreated(dateCreated);
				product.setLastUpdated(lastUpdated);
				products.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProductByCatId(Long categoryId) {
		// TODO Auto-generated method stub
		System.out.println("From Implementation: " + categoryId);
		String query = "Select * from product where category_id = ?";
		productsByCat = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, categoryId);
			System.out.println(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				Long id = rs.getLong(1);
				String sku = rs.getString(2);
				String name = rs.getString(3);
				String description = rs.getString(4);
				BigDecimal unitPrice = rs.getBigDecimal(5);
				String imageUrl = rs.getString(6);
				boolean active = rs.getBoolean(7);
				int unitsInStock = rs.getInt(8);
				Date dateCreated = rs.getDate(9);
				Date lastUpdated = rs.getDate(10);
				//System.out.println(id + sku + name+description+unitPrice+imageUrl+active+unitsInStock+dateCreated+lastUpdated);
				product.setId(id);
				product.setSku(sku);
				product.setName(name);
				product.setDescription(description);
				product.setUnitPrice(unitPrice);
				product.setImageUrl(imageUrl);
				product.setActive(active);
				product.setUnitsInStock(unitsInStock);
				product.setDateCreated(dateCreated);
				product.setLastUpdated(lastUpdated);
				//System.out.println(product);
				productsByCat.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productsByCat;
	}

	@Override
	public List<Product> getAllProductByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		System.out.println("From Implementation: " + keyWord);
		String query = "Select * from Product p where p.name LIKE ?";
		productsByKey = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, "%"+keyWord+"%");
			//System.out.println(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				Long id = rs.getLong(1);
				String sku = rs.getString(2);
				String name = rs.getString(3);
				String description = rs.getString(4);
				BigDecimal unitPrice = rs.getBigDecimal(5);
				String imageUrl = rs.getString(6);
				boolean active = rs.getBoolean(7);
				int unitsInStock = rs.getInt(8);
				Date dateCreated = rs.getDate(9);
				Date lastUpdated = rs.getDate(10);
				//System.out.println(id + sku + name+description+unitPrice+imageUrl+active+unitsInStock+dateCreated+lastUpdated);
				product.setId(id);
				product.setSku(sku);
				product.setName(name);
				product.setDescription(description);
				product.setUnitPrice(unitPrice);
				product.setImageUrl(imageUrl);
				product.setActive(active);
				product.setUnitsInStock(unitsInStock);
				product.setDateCreated(dateCreated);
				product.setLastUpdated(lastUpdated);
				//System.out.println(product);
				productsByKey.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productsByKey;
		
	}

	@Override
	public Product getProductById(Long productId) {
		// TODO Auto-generated method stub
		System.out.println("From Implementation: " + productId);
		String query = "Select * from product where id = ?";
		
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, productId);
			System.out.println(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				productById = new Product();
				Long id = rs.getLong(1);
				String sku = rs.getString(2);
				String name = rs.getString(3);
				String description = rs.getString(4);
				BigDecimal unitPrice = rs.getBigDecimal(5);
				String imageUrl = rs.getString(6);
				boolean active = rs.getBoolean(7);
				int unitsInStock = rs.getInt(8);
				Date dateCreated = rs.getDate(9);
				Date lastUpdated = rs.getDate(10);
				//System.out.println(id + sku + name+description+unitPrice+imageUrl+active+unitsInStock+dateCreated+lastUpdated);
				productById.setId(id);
				productById.setSku(sku);
				productById.setName(name);
				productById.setDescription(description);
				productById.setUnitPrice(unitPrice);
				productById.setImageUrl(imageUrl);
				productById.setActive(active);
				productById.setUnitsInStock(unitsInStock);
				productById.setDateCreated(dateCreated);
				productById.setLastUpdated(lastUpdated);
				System.out.println(productById.getDescription());
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(productById);
		return productById;
		
	}

	

}
