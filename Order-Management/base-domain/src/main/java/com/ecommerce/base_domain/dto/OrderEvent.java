package com.ecommerce.base_domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderEvent {
	private String message;
	private String status;
	private Order order;
	private Double price;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public OrderEvent(String message, String status, Order order, Double price) {
		super();
		this.message = message;
		this.status = status;
		this.order = order;
		this.price = price;
	}
	public OrderEvent() {
		super();
	}
	@Override
	public String toString() {
		return "OrderEvent [message=" + message + ", status=" + status + ", order=" + order + ", price=" + price + "]";
	}
	
}
