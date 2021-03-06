package cn.center.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import cn.center.utils.MQConnectionUtils;

import com.rabbitmq.client.AMQP.BasicProperties;

public class ConsumerEmailDirect {
	private static final String QUEUE_NAME = "consumer_topic_email";
	private static final String EXCHANGE_NAME = "topic_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("邮件消费者启动");
		// 1.创建新的连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.消费者关联队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "log.#");//匹配后面多个词
		// 4.消费者绑定交换机 参数1 队列 参数2交换机 参数3 routingKey
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("消费者获取生产者消息:" + msg);
			}
		};
		// 5.消费者监听队列消息
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}