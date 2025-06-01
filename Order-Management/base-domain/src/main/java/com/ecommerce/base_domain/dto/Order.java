package com.ecommerce.base_domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Order {
	
	private Long orderId;
	private String name;
	private int quantity;
	private double price;
	private String status;
	private String email;
	private String message;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Order(Long orderId, String name, int quantity, double price, String status, String email, String message) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.email = email;
		this.message = message;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", status=" + status + ", email=" + email + ", message=" + message + "]";
	}
	
	

}
