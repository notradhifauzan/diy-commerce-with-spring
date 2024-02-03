package com.fauzan.DIY_COMMERCE.service;

import java.util.List;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.Seller;

public interface SellerService {
	Seller getSeller(String id);
	Seller saveSeller(Seller seller);
	void deleteSeller(String id);
	List<Seller> getSellers();
	List<Product> getSellerProducts(String id);
}
