package com.fauzan.DIY_COMMERCE.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductApi {
	/*
	 * TODO
	 * 
	 * 1. implement field validation
	 * 2. implement authentication and authorization. especially when deleting a product
	 * 
	 * */
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add/{sellerId}")
	public ResponseEntity<Product> addProduct(@RequestBody Product product,@PathVariable String sellerId) {
		productService.addProduct(product,sellerId);
		
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<Product>> getAllProducts(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "2") int size) {
		
		if(page < 1) page = 1;
		if(size < 1) size = 1;
		
		PageRequest pageable = PageRequest.of(page-1, size, Sort.by("dateCreated").descending());
		Page<Product> products = productService.getAllProducts(pageable);
		
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PatchMapping("/patch/{productId}")
	public ResponseEntity<Product> patchProduct(@PathVariable String productId,@RequestBody Map<String,Object> fields){
		return new ResponseEntity<>(productService.updateProductByFields(productId, fields),HttpStatus.CREATED);
	}
	

}
