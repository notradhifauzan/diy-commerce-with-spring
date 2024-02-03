package com.fauzan.DIY_COMMERCE.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.Seller;

public interface SellerService {
	Seller getSeller(String id);
	Seller saveSeller(Seller seller);
	void deleteSeller(String id);
	List<Seller> getSellers();
	List<Product> getSellerProducts(String id);
	
	Page<Seller> getSellerByPage(Pageable pageable);
}
