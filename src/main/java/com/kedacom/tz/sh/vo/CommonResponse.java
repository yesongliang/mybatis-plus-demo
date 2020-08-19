package com.kedacom.tz.sh.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;
	private T data;

	public CommonResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
