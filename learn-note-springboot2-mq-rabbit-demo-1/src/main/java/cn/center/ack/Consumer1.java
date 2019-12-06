package cn.center.ack;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import cn.center.utils.MQConnectionUtils;

public class Consumer1 {
	// 队列名称
	private static final String QUEUE_NAME = "yushengjun_644064779";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("消费消息....01");
		// 1.获取连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		final Channel channel = connection.createChannel();
		// 3.关联队列声明
//		队列名称
//		是否持久化
//		exclusive, 
//		boolean autoDelete, 
//		Map<String, Object> arguments
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

			// 监听获取消息
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("生产者投递消息：" + msg);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 手动应答模式， 告诉列队服务器已经处理成功
					channel.basicAck(envelope.getDeliveryTag(), false);
				}

			}

		};
		// 4.设置应答模式
//		String queue, 
//		boolean autoAck,   true 自动应答模式    false 手动应答模式 
//		Consumer callback
		channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
		// 关闭通道和连接，注释掉保证实时监听消息
		// channel.close();
		// connection.close();
	}
}
