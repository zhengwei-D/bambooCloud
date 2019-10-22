package com.hanqian.bambooCloud.rabbitmq.routing;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
public class Send {

	//交换机名称
	private static final String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMqConnectionUtils.getConnection();
		Channel channel = connection.createChannel();

		//exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		String msg = "hello direct";

		String routingKey = "info"; //路由键
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());

		System.out.println("已发送");

		channel.close();
		connection.close();
	}

}
