package com.hanqian.bambooCloud.rabbitmq.topic;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
public class Send {

	private static final String EXCHANGE_NAME = "test_exchange_topic";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMqConnectionUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String msg = "hello topic";
		channel.basicPublish(EXCHANGE_NAME, "china.jiaozuo", null, msg.getBytes());
		System.out.println("已发送");
		channel.close();
		connection.close();
	}

}
