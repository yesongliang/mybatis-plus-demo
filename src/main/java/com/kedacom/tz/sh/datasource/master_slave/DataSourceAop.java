package com.kedacom.tz.sh.datasource.master_slave;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置数据源切换的AOP切面
 * 
 * @author ysl
 *
 */
@Aspect
//@Component
@Slf4j
public class DataSourceAop {

	@Pointcut("@annotation(com.kedacom.tz.sh.datasource.master_slave.Slave)")
	public void slavePointCut() {
	}

	@Pointcut("@annotation(com.kedacom.tz.sh.datasource.master_slave.Master)")
	public void masterPointcut() {
	}

	@Before("slavePointCut()")
	public void slave() {
		log.info("------switch read-------");
		DataSourceContextHolder.slave();
	}

	@Before("masterPointcut()")
	public void master() {
		log.info("------switch write-------");
		DataSourceContextHolder.master();
	}
}
