package com.fauzan.DIY_COMMERCE.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fauzan.DIY_COMMERCE.entity.Product;

public interface ProductService {
	Product getProduct(String id);
	List<Product> getProductBySellerId(String sellerId);
	Page<Product> getAllProducts(Pageable pageable);
	Product addProduct(Product product);
	
	Product updateProductByFields(String id, Map<String,Object> fields);
	Product addProduct(Product product, String sellerId);
	
	void deleteProduct(String productId);
	void deleteAll();
}
