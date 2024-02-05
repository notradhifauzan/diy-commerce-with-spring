package com.fauzan.DIY_COMMERCE.exception;

public class SellerEmailAlreadyExistException extends RuntimeException{
	public SellerEmailAlreadyExistException(String email) {
		super("Seller with email: " + email + " already exist");
	}
}
