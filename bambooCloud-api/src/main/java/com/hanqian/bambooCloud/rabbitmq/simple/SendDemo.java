package com.hanqian.bambooCloud.rabbitmq.simple;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class SendDemo {

	private static final String QUEUE_NAME = "test_simple_queue";

	public static void main(String[] args) throws IOException, TimeoutException {

		//获取到连接
		Connection connection = RabbitMqConnectionUtils.getConnection();

		//从连接中创建一个通道
		Channel channel = connection.createChannel();

		//创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String msg = "hello world";

		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("---已发送---");

		channel.close();
		connection.close();

	}

}
