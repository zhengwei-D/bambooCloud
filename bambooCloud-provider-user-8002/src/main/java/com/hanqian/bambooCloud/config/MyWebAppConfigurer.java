package com.hanqian.bambooCloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * springmvc配置
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

//	@Override
//	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorPathExtension(false);
//	}
//
//	@Override
//	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		converters.add(responseBodyConverter());
//		converters.add(mappingJackson2HttpMessageConverter());
//	}
//
//	@Bean
//	public HttpMessageConverter responseBodyConverter() {
//		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//	}
//
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//		return new MappingJackson2HttpMessageConverter();
//	}
}
