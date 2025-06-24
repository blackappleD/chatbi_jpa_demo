package com.pangkazhu.exception;

import com.pangkazhu.enums.ErrorCode;
import lombok.Getter;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 15:00
 */
@Getter
public class ProductException extends BaseException {


	private ErrorCode errorCode;

	protected ProductException(ErrorCode errorCode) {
		super(errorCode);
	}

	protected ProductException(ErrorCode errorCode, Object[] args) {
		super(errorCode, args);
	}

	public static ProductException notFound() {
		return new ProductException(ErrorCode.PRODUCT_NOT_FOUND);
	}


}
