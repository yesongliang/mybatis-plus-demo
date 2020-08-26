package com.kedacom.tz.sh.datasource.master_slave;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>(); // 实际上就是开启多个线程，每个线程进行初始化一个数据源

	// 轮询到每个slave
	private static final AtomicInteger COUNTER = new AtomicInteger(-1);

	/**
	 * 设置数据源
	 * 
	 * @param dbTypeEnum
	 */
	public static void setDbType(DBTypeEnum dbTypeEnum) {
		contextHolder.set(dbTypeEnum.getValue());
	}

	/**
	 * 取得当前数据源
	 * 
	 * @return
	 */
	public static String getDbType() {
		return contextHolder.get();
	}

	public static void master() {
		setDbType(DBTypeEnum.MASTER);
		log.info("切换到master");
	}

	public static void slave() {
		// 轮询
		int index = COUNTER.getAndIncrement() % 2;
		if (COUNTER.get() > 9999) {
			COUNTER.set(-1);
		}
		setDbType(DBTypeEnum.SLAVE1);
		log.info(index + "->切换到slave1");
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
