package com.ecommerce.email.model;

public class Order {
	private Long OrderId;
	
	private String name;
	private int quantity;
	private double price;
	private String status;
	private String email;
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
	public Order(Long orderId, String name, int quantity, double price, String status, String email) {
		super();
		OrderId = orderId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Order [OrderId=" + OrderId + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", status=" + status + ", email=" + email + "]";
	}
	
}
