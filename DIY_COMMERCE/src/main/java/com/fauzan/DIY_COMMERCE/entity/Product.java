package com.fauzan.DIY_COMMERCE.entity;

import java.util.UUID;

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
@Table(name = "product")
public class Product {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "productId")
	private String productId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="sellerId",referencedColumnName = "sellerId")
	@JsonIgnore
	private Seller seller;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "description")
	private String description;

	public Product() {
		this.productId = UUID.randomUUID().toString();
	}
	
	/*
	 * TODO
	 * 1. add more relevant product attributes
	 * 
	 * */

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", seller=" + seller.toString() + ", productName=" + productName
				+ ", description=" + description + "]";
	}
	
	
}
