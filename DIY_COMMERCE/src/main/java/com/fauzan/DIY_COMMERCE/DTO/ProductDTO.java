package com.fauzan.DIY_COMMERCE.DTO;

import java.util.List;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.ProductVariation;

public class ProductDTO {
	private Product product;
	private List<ProductVariation> variations;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductVariation> getVariations() {
		return variations;
	}
	public void setVariations(List<ProductVariation> variations) {
		this.variations = variations;
	}
	
	
}
