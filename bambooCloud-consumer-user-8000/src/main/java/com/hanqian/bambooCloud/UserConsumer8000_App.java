package com.hanqian.bambooCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hanqian.bambooCloud"})
@ComponentScan("com.hanqian.bambooCloud")
public class UserConsumer8000_App {

	public static void main(String[] args) {
		SpringApplication.run(UserConsumer8000_App.class, args);
	}

}
