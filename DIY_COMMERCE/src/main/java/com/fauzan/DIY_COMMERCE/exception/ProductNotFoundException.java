package com.fauzan.DIY_COMMERCE.exception;

public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String productId) {
		super("No product found for the id: " + productId);
	}
}
