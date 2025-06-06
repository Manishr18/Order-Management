package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	@Column(unique = true)
	private String name;
	private  Integer availablequantity;
	private double cost;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAvailablequantity() {
		return availablequantity;
	}
	public void setAvailablequantity(Integer availablequantity) {
		this.availablequantity = availablequantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public StockDetails(Long productId, String name, Integer availablequantity, double cost) {
		super();
		this.productId = productId;
		this.name = name;
		this.availablequantity = availablequantity;
		this.cost = cost;
	}
	public StockDetails() {
		super();
	}
	@Override
	public String toString() {
		return "StockDetails [productId=" + productId + ", name=" + name + ", availablequantity=" + availablequantity
				+ ", cost=" + cost + "]";
	}
	
	
}
