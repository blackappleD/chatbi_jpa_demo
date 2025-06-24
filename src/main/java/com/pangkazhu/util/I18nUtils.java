package com.pangkazhu.util;

import com.pangkazhu.enums.ErrorCode;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 15:22
 */
public class I18nUtils {

	private static MessageSource messageSource;

	@Resource
	public void setMessageSource(MessageSource messageSource) {
		I18nUtils.messageSource = messageSource;
	}

	public static String getMessage(String code, Object... args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, code, locale);
	}

	public static String getMessage(ErrorCode errorCode, Object... args) {
		return getMessage(errorCode.getMsgKey(), args);
	}
}
