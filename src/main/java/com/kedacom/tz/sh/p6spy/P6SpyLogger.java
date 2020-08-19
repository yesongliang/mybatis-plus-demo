package com.kedacom.tz.sh.p6spy;

import java.time.LocalDateTime;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyLogger implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		return !"".equals(sql.trim()) ? "[ " + LocalDateTime.now() + " ] --- | SQL耗时: " + elapsed + "ms | 连接信息: " + category + "-" + connectionId + " | 执行语句: \n" + sql + ";" : "";
	}

}
