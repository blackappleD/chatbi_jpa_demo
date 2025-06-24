package com.pangkazhu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 15:23
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

	SUCCESS(0, "success"),

	// 系统错误 (1xxxx)
	SYSTEM_ERROR(10001, "error.system"),

	// 业务错误 (2xxxx)
	PRODUCT_NOT_FOUND(20001, "error.product.not_found"),
	PRODUCT_NOT_AVAILABLE(20002, "error.product.not_available");

	private final int code;
	private final String msgKey;


	}
