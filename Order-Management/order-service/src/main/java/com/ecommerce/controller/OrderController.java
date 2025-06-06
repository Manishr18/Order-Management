package com.ecommerce.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.base_domain.dto.Order;
import com.ecommerce.base_domain.dto.OrderEvent;
import com.ecommerce.kafka.KafkaProducer;
import com.ecommerce.model.OrderEntity;
import com.ecommerce.repository.OrderServiceRepo;

@RestController
@RequestMapping("/api/v1")

public class OrderController {
	private KafkaProducer kafkaproducer;
	private OrderServiceRepo orderrepo;
	
	public OrderController(KafkaProducer kafkaproducer, OrderServiceRepo orderrepo) {
		super();
		this.kafkaproducer = kafkaproducer;
		this.orderrepo = orderrepo;
	}

	@PostMapping("/orders")
	public String placeorder(@RequestBody Order order) {
		OrderEntity entity=new OrderEntity();
		entity.setName(order.getName());
		entity.setPrice(order.getPrice());
		entity.setStatus("Pending");
		entity.setQuantity(order.getQuantity());
		entity.setEmail(order.getEmail());
		entity.setMessage("Order pending");
		OrderEntity savedEntity= orderrepo.save(entity);
		
		Order savedOrder = new Order();
	    savedOrder.setOrderId(savedEntity.getOrderId());
	    savedOrder.setName(savedEntity.getName());
	    savedOrder.setPrice(savedEntity.getPrice());
	    savedOrder.setQuantity(savedEntity.getQuantity());
	    savedOrder.setStatus(savedEntity.getStatus());
	    savedOrder.setEmail(savedEntity.getEmail());
	    savedOrder.setMessage(savedEntity.getMessage());
		
	    OrderEvent orderevent = new OrderEvent();
	    orderevent.setStatus(savedOrder.getStatus());
	    orderevent.setMessage(savedOrder.getMessage());
	    orderevent.setOrder(savedOrder);

	    kafkaproducer.sendMessage(orderevent);

	    return "Order placed successfully";
		
	}
	@GetMapping("/allorders")
	public List<OrderEntity> getAll() {
		return orderrepo.findAll();
	}
}
