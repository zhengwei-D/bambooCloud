package com.hanqian.bambooCloud;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private AmqpAdmin amqpAdmin;

	@Test
	public void testRabbitMQ(){
		Map<String, Object> data = new HashMap<>();
		data.put("msg", "hello");
		data.put("name", "董正伟");
		rabbitTemplate.convertAndSend("test_exchange_topic", "china.shanghai", data);
	}

	@Test
	public void testReceive(){
		Object o = rabbitTemplate.receiveAndConvert("test_queue_topic_2");
		System.out.println(o.getClass().getName());
		System.out.println(o);
	}

	@Test
	public void createExchange(){
		Exchange exchange = new DirectExchange("amqpadmin.exchange");
		amqpAdmin.declareExchange(exchange);
	}

}
