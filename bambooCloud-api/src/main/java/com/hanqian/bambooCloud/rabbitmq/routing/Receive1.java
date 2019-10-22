package com.hanqian.bambooCloud.rabbitmq.routing;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
public class Receive1 {

	//交换机名称
	private static final String EXCHANGE_NAME = "test_exchange_direct";
	private static final String QUEUE_NAME = "test_queue_direct_1";

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMqConnectionUtils.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");

		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("[1] direct 收到：" + msg);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("[1] direct done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};

		channel.basicConsume(QUEUE_NAME, false, consumer);

	}

}
