package com.kedacom.tz.sh.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定义http消息的转换器
 * 
 * @author ysl
 *
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.clear();
		converters.add(new MappingJackson2HttpMessageConverter());
//		converters.add(new FastJsonHttpMessageConverter());
	}
}
