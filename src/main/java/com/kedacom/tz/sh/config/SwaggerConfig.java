package com.kedacom.tz.sh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 生成API接口文档
 * 
 * http://IP:port/${context-path}/swagger-ui.html#/
 * 
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API接口说明--RESTful APIs").description("hello world").contact(new Contact("https://github.com/yesongliang", "https://www.kedacom.com", "15570351952@163.com"))
				.version("1.0").build();
	}
}
