package com.fauzan.DIY_COMMERCE.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fauzan.DIY_COMMERCE.entity.Seller;
import com.fauzan.DIY_COMMERCE.service.SellerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/seller")
public class SellerApi {

	@Autowired
	private SellerService sellerService;

	@PostMapping
	public ResponseEntity<?> saveSeller(@Valid @RequestBody Seller seller) {
		sellerService.saveSeller(seller);
		return ResponseEntity.status(HttpStatus.CREATED).body(seller);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findSellerById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(sellerService.getSeller(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<Seller>> getSellerByPage(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "3") int size) {
		
		if(page < 1) page = 1;
		if(size < 1) size = 1;
		
		PageRequest pageRequest = PageRequest.of(page-1, size);
		
		return new ResponseEntity<>(sellerService.getSellerByPage(pageRequest),HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{sellerId}")
	public ResponseEntity<HttpStatus> deleteSellerById(@PathVariable String sellerId) {
		sellerService.deleteSeller(sellerId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
