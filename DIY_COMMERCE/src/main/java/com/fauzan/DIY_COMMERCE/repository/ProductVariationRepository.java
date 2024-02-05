package com.fauzan.DIY_COMMERCE.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fauzan.DIY_COMMERCE.entity.ProductVariation;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,String> {
	/*
	 * CUSTOM JPA QUERY CAN BE A BIT TRICKY
	 * 
	 * */
	
	List<ProductVariation> findByProductProductId(String productId);
}
