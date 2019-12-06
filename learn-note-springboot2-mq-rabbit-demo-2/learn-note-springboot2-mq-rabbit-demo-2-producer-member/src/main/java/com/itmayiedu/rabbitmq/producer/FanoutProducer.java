package com.itmayiedu.rabbitmq.producer;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(String queueName) {
		System.out.println("queueName:" + queueName);
		String msg = "msg:" + new Date();
		// 发送消息
		amqpTemplate.convertAndSend(queueName, msg);
	}

}
