package com.pangkazhu.exception;

import com.pangkazhu.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 14:52
 */
@Getter
public class BaseException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1405961065280191434L;

	public static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.SYSTEM_ERROR;

	private final ErrorCode errorCode;

	private final Object[] args;

	@Setter(AccessLevel.PROTECTED)
	private boolean log;


	protected BaseException(ErrorCode errorCode, Object[] args, Throwable cause, boolean log) {
		super(cause);
		this.errorCode = errorCode;
		this.args = args;
		this.log = log;
	}


	protected BaseException(ErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause, false);
	}

	protected BaseException(ErrorCode errorCode, Object[] args) {

		this(errorCode, args, null, false);
	}

	protected BaseException(ErrorCode errorCode) {

		this(errorCode, null, null, false);
	}


	public BaseException() {
		this(DEFAULT_ERROR_CODE, null, null, false);
	}

}
