package com.fauzan.DIY_COMMERCE.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="seller")
public class Seller {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "sellerId")
	private String Id;	
	
	@Column(name = "email", unique = true)
	@Email
	@NotEmpty
	private String email;
	
	@Column(name = "username", unique = true)
	@NotEmpty
	@NotNull
	private String username;
	
	@Column(name = "password")
	@NotEmpty
	@NotNull
	private String password;
	
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;

	/*--------------------------------------------------------------------------------------------------*/

	public Seller(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Seller() {
		this.Id = UUID.randomUUID().toString().substring(0,8);
	}
	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
