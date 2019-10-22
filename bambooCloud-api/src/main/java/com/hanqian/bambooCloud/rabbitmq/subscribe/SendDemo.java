package com.hanqian.bambooCloud.rabbitmq.subscribe;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅模式
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class SendDemo {

	//交换机名称
	private static final String EXCHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMqConnectionUtils.getConnection();
		Channel channel = connection.createChannel();

		//声明交换机（fanout：不处理路由键）
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		//发送消息
		String msg = "hello subscribe";
		channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());

		System.out.println("发送：" + msg);

		channel.close();
		connection.close();
	}

}
