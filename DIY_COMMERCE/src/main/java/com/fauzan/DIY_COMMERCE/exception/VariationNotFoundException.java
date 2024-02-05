package com.fauzan.DIY_COMMERCE.exception;

public class VariationNotFoundException extends RuntimeException {
	public VariationNotFoundException(String variationId) {
		super("No product variation found for the Id: " + variationId);
	}
}
