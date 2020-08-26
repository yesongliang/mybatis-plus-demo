package com.kedacom.tz.sh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class MybatisplusApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisplusApplication.class, args);
	}
}
