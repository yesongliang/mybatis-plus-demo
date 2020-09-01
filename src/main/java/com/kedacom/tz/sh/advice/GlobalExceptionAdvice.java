package com.kedacom.tz.sh.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kedacom.tz.sh.exception.BusinessException;
import com.kedacom.tz.sh.vo.CommonResponse;

/**
 * 统一自定义异常处理
 * 
 * @author ysl
 *
 */
@RestControllerAdvice(basePackages = "com.kedacom.tz.sh.controller")
public class GlobalExceptionAdvice {

	@ExceptionHandler(value = BusinessException.class)
	public CommonResponse<String> handerAdException(HttpServletRequest req, BusinessException ex) {
		CommonResponse<String> response = new CommonResponse<>(-1, "business error:" + ex.getMessage());
		response.setData(ex.getMessage());
		return response;
	}
}
