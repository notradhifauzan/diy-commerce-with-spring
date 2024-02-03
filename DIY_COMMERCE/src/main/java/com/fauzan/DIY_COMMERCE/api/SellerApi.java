package com.fauzan.DIY_COMMERCE.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fauzan.DIY_COMMERCE.entity.Seller;
import com.fauzan.DIY_COMMERCE.service.SellerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/seller")
public class SellerApi {

	@Autowired
	private SellerService sellerService;

	@PostMapping
	public ResponseEntity<?> saveSeller(@RequestBody Seller seller) {
		sellerService.saveSeller(seller);
		return ResponseEntity.status(HttpStatus.CREATED).body(seller);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findSellerById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(sellerService.getSeller(id));
	}
}
