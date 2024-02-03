package com.fauzan.DIY_COMMERCE.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.Seller;
import com.fauzan.DIY_COMMERCE.exception.SellerNotFoundException;
import com.fauzan.DIY_COMMERCE.repository.SellerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerServiceImplementation implements SellerService {
	/*
	 * TODO
	 * 
	 * 1. 
	 * */
	
	@Autowired
	private SellerRepository sellerRepo;
	
	@Override
	public Seller getSeller(String id) {
		Optional<Seller> seller = sellerRepo.findById(id);
		return unwrapSeller(seller, id);
	}

	@Override
	public Seller saveSeller(Seller seller) {
		seller.setId(UUID.randomUUID().toString().substring(0,8));
		return sellerRepo.save(seller);
	}

	@Override
	public void deleteSeller(String id) {
		
	}

	@Override
	public List<Seller> getSellers() {
		return null;
	}

	@Override
	public List<Product> getSellerProducts(String id) {
		return null;
	}
	
	@Override
	public Page<Seller> getSellerByPage(Pageable pageable) {
		return sellerRepo.findAll(pageable);
	}
	
	static Seller unwrapSeller(Optional<Seller> entity, String id) {
		if(entity.isPresent()) return entity.get();
		else throw new SellerNotFoundException(id);
	}

}
