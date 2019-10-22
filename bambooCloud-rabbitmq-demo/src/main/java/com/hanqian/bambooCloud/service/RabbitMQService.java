package com.hanqian.bambooCloud.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
@Service
public class RabbitMQService {

	@RabbitListener(queues = "test_queue_topic_1")
	public void receive(Map<String, Object> data){
//		Map<String, Object> data = (Map) o;
		System.out.println(data.get("name"));
		System.out.println(data.get("msg"));
	}

}
