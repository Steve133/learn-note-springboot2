package cn.center.amqp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import cn.center.utils.MQConnectionUtils;

// 简单队列生产者
public class Producer {

	// 队列名称
	private static final String QUEUE_NAME = "yushengjun_644064779";

	public static void main(String[] args) throws IOException, TimeoutException {

		// 1.获取连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.创建队列声明
//		队列名称
//		是否持久化
//		exclusive, 
//		boolean autoDelete, 
//		Map<String, Object> arguments
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		try {
			channel.txSelect();//开启事物
			
			// 4.创建msg
			String msg = "yushengjun_msg";
			System.out.println("生产者投递消息："+msg);
			// 5.发送消息
//		String exchange, 交换机
//		String routingKey, 
//		BasicProperties props, 
//		byte[] body
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			
			int i = 1/0;
			
			channel.txCommit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生产者消息已经回滚");
			channel.txRollback();//回滚
		} finally {
			// 关闭通道和连接
			channel.close();
			connection.close();
		}
	}

}
