package com.hanqian.bambooCloud.rabbitmq.subscribe;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class Receive2 {

	private static final String QUEUE_NAME = "test_queue_subscribe_sms";
	private static final String EXCHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] args) throws IOException {
		Connection connection = RabbitMqConnectionUtils.getConnection();
		Channel channel = connection.createChannel();

		//队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		//绑定队列到交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

		//定义一个消费者
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("sms 接收到：" + msg);
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};

		channel.basicConsume(QUEUE_NAME, false, consumer);
	}

}
