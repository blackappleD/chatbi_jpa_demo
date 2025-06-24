package com.pangkazhu.web;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.pangkazhu.annotations.ChatbiResponseBody;
import com.pangkazhu.annotations.ChatbiResponseBodyIgnore;
import com.pangkazhu.dto.base.BaseResponseDTO;
import com.pangkazhu.exception.BaseException;
import com.pangkazhu.exception.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2024/9/20 15:22
 */
@Order(0)
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
	public boolean isProdOrPre() {
		return "prod".equals(SpringUtil.getActiveProfile()) || "pre".equals(SpringUtil.getActiveProfile());
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		var method = returnType.getMethod();
		if (method == null) {
			return false;
		}
		// 只针对带有特殊注解的控制器
		return (method.getAnnotation(ChatbiResponseBody.class) != null
				|| returnType.getContainingClass().getAnnotation(ChatbiResponseBody.class) != null
				|| returnType.getDeclaringClass().getAnnotation(ChatbiResponseBody.class) != null)
				&& returnType.getMethodAnnotation(ChatbiResponseBodyIgnore.class) == null;
	}

	@Override
	public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
	                              Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
	                              ServerHttpResponse response) {
		if (body instanceof BaseResponseDTO) {
			return body;
		}
		if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
			return JSONUtil.toJsonStr(BaseResponseDTO.success(body));
		}
		return BaseResponseDTO.success(body);
	}

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Object dgExceptionHandler(BaseException ex) {
		if (ex.isLog()) {
			log.error(ex.getMessage(), ex);
		} else {
			log.debug(ex.getMessage(), ex);
		}
		// 非生产环境可以打印堆栈
		return BaseResponseDTO.fail(ex.getErrorCode(), isProdOrPre() ? null : ex);
	}

	@ExceptionHandler(ProductException.class)
	public Object loginInvalidExceptionHandler(ProductException ex) {
		if (ex.isLog()) {
			log.error(ex.getMessage(), ex);
		} else {
			log.debug(ex.getMessage(), ex);
		}
		return BaseResponseDTO.fail(ex.getErrorCode(), ex, ex.getArgs());
	}


}