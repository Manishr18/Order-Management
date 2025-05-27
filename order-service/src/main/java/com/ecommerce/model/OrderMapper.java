package com.ecommerce.model;

import com.ecommerce.base_domain.dto.Order;

public class OrderMapper {
	public static OrderEntity orderDto(Order order) {
		OrderEntity entity=new OrderEntity();
		entity.setOrderId(order.getOrderId());
		entity.setName(order.getName());
		entity.setPrice(order.getPrice());
		entity.setQuantity(order.getQuantity());
		entity.setStatus(order.getStatus());
		entity.setEmail(order.getEmail());	
		entity.setMessage(order.getMessage());
		
		return entity;
	}
	public static Order dto(OrderEntity entity) {
		return new Order(entity.getOrderId(), entity.getName(), entity.getQuantity(), entity.getPrice(),entity.getStatus(),entity.getEmail(),entity.getMessage());
		
	}

}
