package com.fauzan.DIY_COMMERCE.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fauzan.DIY_COMMERCE.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	Page<Product> findProductBySellerId(String sellerId, Pageable pageable);
}
