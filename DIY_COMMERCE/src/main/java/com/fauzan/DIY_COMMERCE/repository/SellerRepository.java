package com.fauzan.DIY_COMMERCE.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.fauzan.DIY_COMMERCE.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String> {
	public Optional<Seller> findSellerByEmail(String email);
	public Optional<Seller> findSellerByUsername(String username);
}
