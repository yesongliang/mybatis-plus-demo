package com.kedacom.tz.sh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * 
 * 分页插件
 * 
 * @author ysl
 *
 */
@Configuration
@MapperScan("com.kedacom.tz.sh.mapper")
@ConditionalOnClass(value = { PaginationInterceptor.class })
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
}
