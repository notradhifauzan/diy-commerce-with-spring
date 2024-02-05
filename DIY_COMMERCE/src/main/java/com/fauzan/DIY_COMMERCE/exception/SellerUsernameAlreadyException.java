package com.fauzan.DIY_COMMERCE.exception;

public class SellerUsernameAlreadyException extends RuntimeException {
	public SellerUsernameAlreadyException(String message) {
		super("Seller with the username: " + message + " already exist");
	}
}
