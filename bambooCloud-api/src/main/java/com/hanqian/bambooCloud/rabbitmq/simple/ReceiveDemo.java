package com.hanqian.bambooCloud.rabbitmq.simple;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class ReceiveDemo {

	private static final String QUEUE_NAME = "test_simple_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		//获取连接
		Connection connection = RabbitMqConnectionUtils.getConnection();

		//创建通道
		Channel channel = connection.createChannel();

		//队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

				String msg = new String(body, "utf-8");
				System.out.println("---------接收到--------");
				System.out.println(msg);
				System.out.println("-------------------------");

			}
		};

		//监听队列
		channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

	}

}
