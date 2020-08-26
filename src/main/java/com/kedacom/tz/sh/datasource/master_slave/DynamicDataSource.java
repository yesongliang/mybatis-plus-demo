package com.kedacom.tz.sh.datasource.master_slave;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据key获取策略设置
 * 
 * @author ysl
 *
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		String datasource = DataSourceContextHolder.getDbType();
		log.info("使用数据源 {}", datasource);
		return datasource;
	}
}
