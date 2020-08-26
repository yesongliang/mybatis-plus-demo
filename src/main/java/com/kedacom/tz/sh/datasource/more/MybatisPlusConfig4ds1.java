package com.kedacom.tz.sh.datasource.more;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * Mybatis-plus 主数据源ds1配置 多数据源配置依赖数据源配置
 * 
 * @see DataSourceConfig
 */
//@Configuration
@MapperScan(basePackages = "com.kedacom.tz.sh.datasource.more.ds1.dao", sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class MybatisPlusConfig4ds1 {

	// 主数据源 ds1数据源
	@Primary
	@Bean("ds1SqlSessionFactory")
	public SqlSessionFactory ds2SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/ds_mapper/ds1/*.xml"));
		sqlSessionFactory.setPlugins(new Interceptor[] { new PaginationInterceptor(), new PerformanceInterceptor()
//                        .setFormat(true),
		});
		sqlSessionFactory.setGlobalConfig(new GlobalConfig().setBanner(false));
		return sqlSessionFactory.getObject();
	}

	@Primary
	@Bean(name = "ds1TransactionManager")
	public DataSourceTransactionManager ds2TransactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Primary
	@Bean(name = "ds1SqlSessionTemplate")
	public SqlSessionTemplate ds2SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
