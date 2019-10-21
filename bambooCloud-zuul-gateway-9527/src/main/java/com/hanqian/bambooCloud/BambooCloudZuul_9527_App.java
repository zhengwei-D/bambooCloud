package com.hanqian.bambooCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/18 。
 * ============================================================================
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy
public class BambooCloudZuul_9527_App {
	public static void main(String[] args) {
		SpringApplication.run(BambooCloudZuul_9527_App.class, args);
	}

}
