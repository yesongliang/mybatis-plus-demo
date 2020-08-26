package com.kedacom.tz.sh.datasource.master_slave;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@EnableTransactionManagement // 开启事务
//@Configuration // spring中常用到注解，与xml配置相对立。是两种加载bean方式
@MapperScan("com.kedacom.tz.sh.mapper") // 扫描mapperdao的地址
public class MybatisPlusConfig {

	@Bean(name = "master")
	@ConfigurationProperties(prefix = "spring.datasource.druid.master")
	public DataSource master() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "slave1")
	@ConfigurationProperties(prefix = "spring.datasource.druid.slave1")
	public DataSource slave1() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源配置
	 *
	 * @return
	 */
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("master") DataSource master, @Qualifier("slave1") DataSource slave1) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.MASTER.getValue(), master);
		targetDataSources.put(DBTypeEnum.SLAVE1.getValue(), slave1);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(master); // 程序默认数据源，这个要根据程序调用数据源频次，经常把常调用的数据源作为默认
		return dynamicDataSource;
	}

	/**
	 * 配置mybatis-plus的SqlSessionFactory
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(master(), slave1()));

		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
		// PerformanceInterceptor(),OptimisticLockerInterceptor()
		// 添加分页功能
		// mybatis-plus的性能优化-设置最大执行时长和格式化sql输出
		sqlSessionFactory.setPlugins(new Interceptor[] { new PaginationInterceptor().setDialectType("mysql"), new PerformanceInterceptor().setFormat(true) });
//	        sqlSessionFactory.setGlobalConfig(globalConfiguration()); //注释掉全局配置，因为在xml中读取就是全局配置
		return sqlSessionFactory.getObject();
	}
}
