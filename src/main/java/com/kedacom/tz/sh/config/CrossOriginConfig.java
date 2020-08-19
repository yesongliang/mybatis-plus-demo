package com.kedacom.tz.sh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 允许跨域请求配置
 * 
 * @author ysl
 *
 */
@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH").maxAge(3600).allowCredentials(true);
	}

}
