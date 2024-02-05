package com.fauzan.DIY_COMMERCE.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fauzan.DIY_COMMERCE.DTO.ProductDTO;
import com.fauzan.DIY_COMMERCE.DTO.VariationDTO;
import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.ProductVariation;

public interface ProductService {
	Product getProduct(String id);
	Page<Product> getProductBySellerId(String sellerId, Pageable pageable);
	Page<Product> getAllProducts(Pageable pageable);
	
	// using DTO
	ProductDTO getProductById(String id);
	
	Product updateProductByFields(String id, Map<String,Object> fields);
	Product addProduct(Product product, String sellerId);
	
	void deleteProduct(String productId);
	void deleteAll();
	
	ProductVariation addProductVariation(String productId,ProductVariation variation);
	
	ProductVariation getVariation(String variationId);
	
	// using DTO
	VariationDTO getVariationById(String variationId);
	
	ProductVariation patchVariationById(String productId,String variationId, Map<String,Object> fields);
}
