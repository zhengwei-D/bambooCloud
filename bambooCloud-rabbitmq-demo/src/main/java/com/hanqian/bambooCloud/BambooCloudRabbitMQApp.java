package com.hanqian.bambooCloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * springBoot整合rabbitMQ
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/22 。
 * ============================================================================
 */
@EnableRabbit
@SpringBootApplication
public class BambooCloudRabbitMQApp {

	public static void main(String[] args) {
		SpringApplication.run(BambooCloudRabbitMQApp.class, args);
	}

}
