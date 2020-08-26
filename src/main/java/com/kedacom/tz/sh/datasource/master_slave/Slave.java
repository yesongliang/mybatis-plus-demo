package com.kedacom.tz.sh.datasource.master_slave;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Slave {

	// TODO 后续可用于指定使用哪个slave
	String value() default "";
}
