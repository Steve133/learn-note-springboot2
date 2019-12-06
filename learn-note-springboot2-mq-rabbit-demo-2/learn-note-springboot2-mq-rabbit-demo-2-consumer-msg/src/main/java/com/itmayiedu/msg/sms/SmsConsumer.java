package com.itmayiedu.msg.sms;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "new_fanout_sms_queue")
//@RabbitListener底层使用aop进行拦截，如果程序没有抛出异常，自动提交事务
//如果aop使用异常通知拦截，获取异常信息的话，自动实现补偿机制，该消息会缓存到rabbitmq服务器端进行存放，一直重试到不跑出异常为准
public class SmsConsumer {

	//rabbitmq 默认情况下，如果消费者程序出现异常自动补偿
	@RabbitHandler
	public void process(String msg,@Header Map<String, Object> headers,Channel channel) throws IOException {
		System.out.println("短信消费者获取生产者消息msg:" + msg);
//		int i = 1/0;//报错自动补偿
		
		
		//手动ACK
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		//手动签收
		channel.basicAck(deliveryTag, false);
	}

}
