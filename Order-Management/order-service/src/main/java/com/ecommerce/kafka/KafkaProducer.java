package com.ecommerce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ecommerce.base_domain.dto.OrderEvent;

@Service
public class KafkaProducer {
	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaProducer.class);
	private NewTopic topic;
	private KafkaTemplate<String, OrderEvent> kafkatemplate;
	public KafkaProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkatemplate) {
		super();
		this.topic = topic;
		this.kafkatemplate = kafkatemplate;
	}
	public void sendMessage(OrderEvent event) {
		LOGGER.info(String.format("Order event -> %s", event.toString()));
		Message<OrderEvent> message=MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkatemplate.send(message);
	}
	
}
