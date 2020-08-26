package com.kedacom.tz.sh.datasource.master_slave;

public enum DBTypeEnum {
	MASTER("master"), SLAVE1("slave1");

	private String value;

	DBTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
