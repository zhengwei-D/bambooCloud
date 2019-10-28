package com.hanqian.bambooCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BambooCloudWxApp {

	public static void main(String[] args) {
		SpringApplication.run(BambooCloudWxApp.class, args);
	}

}
