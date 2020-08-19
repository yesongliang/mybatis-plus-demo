package com.kedacom.tz.sh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kedacom.tz.sh.mapper")
public class MybatisplusApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisplusApplication.class, args);
	}
}
