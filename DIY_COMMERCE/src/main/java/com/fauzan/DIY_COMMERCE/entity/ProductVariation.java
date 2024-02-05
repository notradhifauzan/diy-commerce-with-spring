package com.fauzan.DIY_COMMERCE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_variation")
public class ProductVariation {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "variation_id")
	private String variationId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	@JsonIgnore
	private Product product;
	
	@Column(name = "variation_name")
	private String variationName;
	
	@Column(name = "variation_description")
	private String variationDescription;
	
	@Column(name = "variation_quantity")
	private Integer variationQuantity;
	
	
	public String getVariationId() {
		return variationId;
	}
	
	public void setVariationId(String variationId) {
		this.variationId = variationId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getVariationName() {
		return variationName;
	}
	public void setVariationName(String variationName) {
		this.variationName = variationName;
	}
	public String getVariationDescription() {
		return variationDescription;
	}
	public void setVariationDescription(String variationDescription) {
		this.variationDescription = variationDescription;
	}
	public Integer getVariationQuantity() {
		return variationQuantity;
	}
	public void setVariationQuantity(Integer variationQuantity) {
		this.variationQuantity = variationQuantity;
	}

	@Override
	public String toString() {
		return "ProductVariation [variationId=" + variationId + ", product=" + product + ", variationName="
				+ variationName + ", variationDescription=" + variationDescription + ", variationQuantity="
				+ variationQuantity + "]";
	}
}
