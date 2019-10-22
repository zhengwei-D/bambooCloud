package com.hanqian.bambooCloud.rabbitmq.work;

import com.hanqian.bambooCloud.rabbitmq.RabbitMqConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class SendDemo {

	private static final String QUEUE_NAME = "test_work_queue";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		//获取连接
		Connection connection = RabbitMqConnectionUtils.getConnection();

		//获取通道
		Channel channel = connection.createChannel();

		//声明队列（队列名称，是否消息持久化）
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);

		/**
		 * 每个消费者确认消息事前，消息队列不发送下一个消息到消费者，一次只处理一个消息
		 * 限制发送给同一个消费者同一条消息
		 */
		channel.basicQos(1);

		for(int i=0; i<20; i++){
			String msg = "hello " + i;
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			System.out.println("发送：【"+msg+"】");
			Thread.sleep(i*10);
		}

		channel.close();
		connection.close();

	}

}
