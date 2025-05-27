package com.ecommerce.kafkaproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ecommerce.base_domain.dto.OrderEvent;

@Service
public class KafkaProducer {
	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaProducer.class);
	@Value("${spring.kafka.topic.response.name}")
	private String topic;
	private KafkaTemplate<String,OrderEvent> kafkatemplate;
	
	public KafkaProducer(KafkaTemplate<String, OrderEvent> kafkatemplate) {
		super();
		this.kafkatemplate = kafkatemplate;
	}

	public void sendmessage(OrderEvent orderevent) {
		LOGGER.info(String.format("Stock details -> %s",orderevent.toString()));
		Message<OrderEvent> message=MessageBuilder
				.withPayload(orderevent)
				.setHeader(KafkaHeaders.TOPIC, topic)
				.build();
		kafkatemplate.send(message);
	}
	
	
	
	
}
