package com.hanqian.bambooCloud.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 连接
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/21 。
 * ============================================================================
 */
public class RabbitMqConnectionUtils {

	/**
	 * 获取MQ的连接
	 */
	public static Connection getConnection() {
		//定义一个连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();

		//设置服务器地址
		connectionFactory.setHost("127.0.0.1");

		//端口
		connectionFactory.setPort(5672);

		//设置库
		connectionFactory.setVirtualHost("/vhost_dzw");

		//用户名
		connectionFactory.setUsername("dzw");

		//密码
		connectionFactory.setPassword("dzw");

		Connection connection = null;
		try {
			connection = connectionFactory.newConnection();
		}catch (Exception e){
			e.printStackTrace();
		}

		return connection;
	}

}
