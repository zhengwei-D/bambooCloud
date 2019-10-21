package com.hanqian.bambooCloud.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/18 。
 * ============================================================================
 */
@Configuration
public class ServletConfigBean {

	@Bean
	public ServletRegistrationBean hystrixMetricsStreamServlet() {
		ServletRegistrationBean regist = new ServletRegistrationBean();
		regist.setServlet(new HystrixMetricsStreamServlet());
		regist.setName("hystrixMetricsStreamServlet");
		regist.setLoadOnStartup(1);
		regist.addUrlMappings("/turbine.stream");
		regist.addUrlMappings("/hystrix.stream");
		regist.addUrlMappings("/actuator/hystrix.stream");
		return regist;
	}

}
