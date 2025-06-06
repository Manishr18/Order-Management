package com.ecommerce.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecommerce.base_domain.dto.Order;
import com.ecommerce.base_domain.dto.OrderEvent;
import com.ecommerce.email.model.OrderDto;

@Service
public class Sender {
	@Autowired
	private JavaMailSender sender;
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumer(OrderEvent event) {
		Order order=event.getOrder();
		String to=order.getEmail();
		String subject="Order status "+ order.getStatus();
		  String body = (order.getMessage() != null && !order.getMessage().isBlank())
	                ? "name:" + " " +order.getName() +" "+ "price:" + " " +order.getPrice() +" "+ "quantity:" +" "+ order.getQuantity() +" "+
	                		order.getMessage()
	                : getDefaultMessage(event);

	        sendEmail(to, subject, body);
	    }

	    private String getDefaultMessage(OrderEvent event) {
	        if ("SUCCESS".equalsIgnoreCase(event.getStatus())) {
	            return "order name:" + event.getOrder().getName()+"order price" + event.getOrder().getPrice()+ "quantity"+event.getOrder().getQuantity()
	            		+ "Your order has been placed successfully!";
	        } else if ("FAILED".equalsIgnoreCase(event.getStatus())) {
	            return "Order failed due to insufficient stock.";
	        } else {
	            return "Your order is currently in process.";
	        }
	    }
	public void sendEmail(String to,String subject,String text) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		sender.send(message);
		
	}
}
