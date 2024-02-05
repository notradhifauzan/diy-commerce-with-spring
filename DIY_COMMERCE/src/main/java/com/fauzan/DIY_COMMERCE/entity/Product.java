package com.fauzan.DIY_COMMERCE.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
	/*
	 * TODO 1. add more relevant product attributes
	 * 
	 */
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "productId")
	private String productId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
	@JsonIgnore
	private Seller seller;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductVariation> variations;

	@Column(name = "productName")
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "dateCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dateCreated;

	@Column(name = "lastModified")
	@LastModifiedDate
	private Date lastModified;

	@Column(name = "category")
	private String category;
	
	@PrePersist
	void onPrePersist() {
		dateCreated = new java.util.Date();
		lastModified = new java.util.Date();
	}
	
	/*-----------------------------------------------------------------------------------------------------------*/
	
	
	public String getCategory() {
		return category;
	}

	public List<ProductVariation> getVariations() {
		return variations;
	}

	public void setVariations(List<ProductVariation> variations) {
		this.variations = variations;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Product() {
		this.productId = UUID.randomUUID().toString();
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

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
