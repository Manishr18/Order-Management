package com.ecommerce.email.model;

public class OrderDto {
	private String message;
	private String status;
	private Order order;
	private String email;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public OrderDto(String message, String status, Order order, String email) {
		super();
		this.message = message;
		this.status = status;
		this.order = order;
		this.email = email;
	}
	

}
