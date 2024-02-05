package com.fauzan.DIY_COMMERCE.DTO;

import com.fauzan.DIY_COMMERCE.entity.Product;
import com.fauzan.DIY_COMMERCE.entity.ProductVariation;

public class VariationDTO {
	private ProductVariation productVariation;
	private Product product;
	
	
	public ProductVariation getProductVariation() {
		return productVariation;
	}
	public void setProductVariation(ProductVariation productVariation) {
		this.productVariation = productVariation;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
