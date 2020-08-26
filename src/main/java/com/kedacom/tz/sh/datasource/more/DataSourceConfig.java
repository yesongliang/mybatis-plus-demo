package com.kedacom.tz.sh.datasource.more;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 多数据源配置
 * 
 * @author ysl
 *
 */
//@Configuration
public class DataSourceConfig {

	/**
	 * 主数据源ds1配置信息加载
	 * 
	 * @return
	 */
	@Primary
	@Bean(name = "ds1DataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.ds1")
	public DataSourceProperties ds1DataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * 主数据源ds1初始化
	 * 
	 * @param dataSourceProperties
	 * @return
	 */
	@Primary
	@Bean(name = "ds1DataSource")
	public DataSource ds1DataSource(@Qualifier("ds1DataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	/**
	 * 数据源ds2配置信息加载
	 * 
	 * @return
	 */
	@Bean(name = "ds2DataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.ds2")
	public DataSourceProperties ds2DataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * 数据源ds2初始化
	 * 
	 * @param dataSourceProperties
	 * @return
	 */
	@Bean("ds2DataSource")
	public DataSource ds2DataSource(@Qualifier("ds2DataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}
}
