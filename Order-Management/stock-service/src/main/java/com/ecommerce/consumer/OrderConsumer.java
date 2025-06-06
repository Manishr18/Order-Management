package com.ecommerce.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.base_domain.dto.Order;
import com.ecommerce.base_domain.dto.OrderEvent;
import com.ecommerce.kafkaproducer.KafkaProducer;
import com.ecommerce.model.StockDetails;
import com.ecommerce.repository.stockrepo;

import jakarta.transaction.Transactional;

@Service
public class OrderConsumer {
	private static final Logger LOGGER=LoggerFactory.getLogger(OrderConsumer.class);
	private final KafkaProducer kafkaproducer;
	private final stockrepo Stockrepo;
	
	public OrderConsumer(KafkaProducer kafkaproducer, stockrepo stockrepo) {
		super();
		this.kafkaproducer = kafkaproducer;
		Stockrepo = stockrepo;
	}
	

	  @Transactional
	    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	    public void consume(OrderEvent orderEvent) {
	        Order order = orderEvent.getOrder();
	        String name=order.getName(); 
	        int quantity = order.getQuantity();
	        order.setOrderId(order.getOrderId());

	        Optional<StockDetails> stockOpt = Stockrepo.findByName(name);

	        if (stockOpt.isPresent()) {
	            StockDetails stock = stockOpt.get();
	            if (stock.getAvailablequantity() >= quantity) {
	                stock.setAvailablequantity(stock.getAvailablequantity() - quantity);
	                Stockrepo.save(stock);
	                LOGGER.info("Stock updated for product {}. Remaining quantity: {}", name, stock.getAvailablequantity());
	                order.setPrice(quantity * stock.getCost());
	                order.setStatus("SUCCESS");
	                order.setMessage("Order placed");
	                kafkaproducer.sendmessage(new OrderEvent(order.getMessage(),order.getStatus(), order,order.getPrice()));
	                LOGGER.info("Stock updated for product {}. Remaining quantity: {}", name, stock.getAvailablequantity());
	            } else {
	                LOGGER.warn("Insufficient stock for product {}. Available: {}, Requested: {}", name, stock.getAvailablequantity(), quantity);
	                order.setStatus("Failed");
	                order.setMessage("Order failed Insufficient inventory ");
	                kafkaproducer.sendmessage(new OrderEvent(order.getMessage(),order.getStatus(), order,order.getPrice()));
	                
	            }
	        } else {
	            LOGGER.error("Product not found with ID: {}", name);
	            order.setStatus("FAILED");
	            order.setMessage("Order failed due to not found");
	            kafkaproducer.sendmessage(new OrderEvent(order.getMessage(),order.getStatus(), order,order.getPrice()));
	            
	        }
	    }
	}
