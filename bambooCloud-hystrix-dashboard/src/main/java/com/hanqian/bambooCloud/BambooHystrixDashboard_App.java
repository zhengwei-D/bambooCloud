package com.hanqian.bambooCloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/17 。
 * ============================================================================
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableHystrixDashboard
public class BambooHystrixDashboard_App {

	public static void main(String[] args) {
		SpringApplication.run(BambooHystrixDashboard_App.class, args);
		//TODO 豪猪监控台页面打开404
	}

	@Bean
	public ServletRegistrationBean getServlet(){
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/actuator/hystrix.stream");
		registrationBean.addUrlMappings("/turbine.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}

}
