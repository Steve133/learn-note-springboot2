package cn.center.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import cn.center.utils.MQConnectionUtils;

// 生产者  交换机类型 producerFanout类型
public class ProducerFanout {

	// 交换机名称
	private static final String EXCHANGE_NAME = "fanout_exchange";

	public static void main(String[] args) throws Exception {
		// 1.创建新的连接
		Connection connection = MQConnectionUtils.newConnection();
		// 2.创建通道
		Channel channel = connection.createChannel();
		// 3.绑定的交换机 参数1交互机名称 参数2 exchange类型
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String msg = "fanout_exchange_msg";
		System.out.println("生产者投递消息：" + msg);
		// 4.发送消息
		channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
		// System.out.println("生产者发送msg：" + msg);
		// // 5.关闭通道、连接
		channel.close();
		connection.close();
		// 注意：如果消费没有绑定交换机和队列，则消息会丢失

	}

}
