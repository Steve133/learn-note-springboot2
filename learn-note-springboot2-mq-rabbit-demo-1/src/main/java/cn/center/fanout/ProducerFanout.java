package cn.center.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import cn.center.utils.MQConnectionUtils;

// ������  ���������� producerFanout����
public class ProducerFanout {

	// ����������
	private static final String EXCHANGE_NAME = "fanout_exchange";

	public static void main(String[] args) throws Exception {
		// 1.�����µ�����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�󶨵Ľ����� ����1���������� ����2 exchange����
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String msg = "fanout_exchange_msg";
		System.out.println("������Ͷ����Ϣ��" + msg);
		// 4.������Ϣ
		channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
		// System.out.println("�����߷���msg��" + msg);
		// // 5.�ر�ͨ��������
		channel.close();
		connection.close();
		// ע�⣺�������û�а󶨽������Ͷ��У�����Ϣ�ᶪʧ

	}

}
