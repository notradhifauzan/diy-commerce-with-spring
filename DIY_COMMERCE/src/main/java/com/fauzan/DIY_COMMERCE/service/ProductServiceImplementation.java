package com.fauzan.DIY_COMMERCE.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.Seller;
import com.fauzan.DIY_COMMERCE.exception.ProductNotFoundException;
import com.fauzan.DIY_COMMERCE.repository.ProductRepository;
import com.fauzan.DIY_COMMERCE.repository.SellerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SellerRepository sellerRepo;
	
	@Override
	public Product getProduct(String id) {
		Product product = unwrapProduct(productRepo.findById(id), id);
		
		return product;
	}

	@Override
	public List<Product> getProductBySellerId(String sellerId) {
		/*
		 * TODO
		 * 
		 * implement pageable to improve performance
		 * 
		 * */
		return null;
	}

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public Product addProduct(Product product,String sellerId) {
		// if this line does not pass, then an exception will occur
		Seller seller = SellerServiceImplementation.unwrapSeller(sellerRepo.findById(sellerId), sellerId);
		
		// add this seller to the product
		product.setSeller(seller);
		
		// if the line above pass, then can proceed with new product addition
		return productRepo.save(product);
	}
	
	@Override
	public void deleteProduct(String productId) {
		Optional<Product> product = productRepo.findById(productId);
		
		if(product.isPresent()) {
			productRepo.deleteById(productId);
		} else {
			throw new ProductNotFoundException(productId);
		}
	}

	@Override
	public Product updateProductByFields(String id, Map<String, Object> fields) {
		Product product = unwrapProduct(productRepo.findById(id), id);
		
		// Remove the productId field from the fields map
	    fields.remove("productId");
	    
		fields.forEach((key,value)->{
			Field field = ReflectionUtils.findRequiredField(Product.class,key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, product, value);
		});
		
		product.setLastModified(new Date());
		return productRepo.save(product);
	}
	
	@Override
	public void deleteAll() {
		productRepo.deleteAll();
	}

	static Product unwrapProduct(Optional<Product> product, String productId) {
		if(product.isPresent()) return product.get();
		
		throw new ProductNotFoundException(productId);
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
