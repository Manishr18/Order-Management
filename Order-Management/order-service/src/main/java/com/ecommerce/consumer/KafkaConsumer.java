package com.ecommerce.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.base_domain.dto.Order;
import com.ecommerce.base_domain.dto.OrderEvent;
import com.ecommerce.kafka.KafkaProducer;
import com.ecommerce.model.OrderEntity;
import com.ecommerce.model.OrderMapper;
import com.ecommerce.repository.OrderServiceRepo;

@Service
public class KafkaConsumer {
	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaConsumer.class);
	private OrderServiceRepo orderrepo;
	private KafkaProducer kafkaproducer;
	
	public KafkaConsumer(OrderServiceRepo orderrepo, KafkaProducer kafkaproducer) {
		super();
		this.orderrepo = orderrepo;
		this.kafkaproducer = kafkaproducer;
	}

	@KafkaListener(topics = "${spring.kafka.topic.response.name}",groupId = "${spring.kafka.consumer.group-id}")
	public void Consume(OrderEvent orderevent) {
		 Order order = orderevent.getOrder();
	        Long orderId = order.getOrderId();
	        String status=order.getStatus();
	        String message=order.getMessage();
	        double price=order.getPrice();

	        Optional<OrderEntity> orderOpt = orderrepo.findById(orderId);
	        if (orderOpt.isPresent()) {
	            OrderEntity entity = orderOpt.get();
	          	entity.setStatus(status);
	          	entity.setMessage(message);
	          	entity.setPrice(price);
	            orderrepo.save(entity);

	            LOGGER.info("Order status updated from stock response: {}", entity);
	        } else {
	            LOGGER.error("Order not found for update: {}", orderId);
	        }
	    }
}
