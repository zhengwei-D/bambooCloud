package com.hanqian.bambooCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/17 。
 * ============================================================================
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7003_App {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer7003_App.class, args);
	}

}
