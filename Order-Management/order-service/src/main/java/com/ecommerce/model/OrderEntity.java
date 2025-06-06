package com.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class OrderEntity {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long OrderId;
	
	private String name;
	private int quantity;
	private double price;
	private String status;
	private String message;
	private String email;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getOrderId() {
		return OrderId;
	}
	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public OrderEntity(Long orderId, String name, int quantity, double price, String status,
			String email, String message) {
		super();
		OrderId = orderId;
		
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.email = email;
		this.message = message;
	}
	public OrderEntity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderEntity [OrderId=" + OrderId + ", name=" + name + ", quantity="
				+ quantity + ", price=" + price + ", status=" + status + ", email=" + email + "]";
	}
	
	
}
