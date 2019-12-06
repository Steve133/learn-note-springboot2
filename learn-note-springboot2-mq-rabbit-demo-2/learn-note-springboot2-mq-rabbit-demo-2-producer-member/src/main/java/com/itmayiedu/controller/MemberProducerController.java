package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.rabbitmq.producer.FanoutProducer;

@RestController
public class MemberProducerController {
	@Autowired
	private FanoutProducer fanoutProducer;

	@RequestMapping("/sendMsg")
	public String sendMsg(String queueName) {
		fanoutProducer.send(queueName);
		return "success";
	}

}
