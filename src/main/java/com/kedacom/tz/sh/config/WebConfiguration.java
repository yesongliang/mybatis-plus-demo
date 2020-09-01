package com.kedacom.tz.sh.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

	// 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 解决静态资源无法访问
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		// 解决swagger无法访问
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		// 解决swagger的js文件无法访问
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
