package com.fauzan.DIY_COMMERCE.exception;

public class SellerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SellerNotFoundException(String id) {
		super("No seller found for id: " + id);
	}
}
