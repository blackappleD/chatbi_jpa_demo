package com.pangkazhu.dto.base;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.pangkazhu.enums.ErrorCode;
import com.pangkazhu.util.I18nUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDTO<T> {
	private Integer code;

	private String message;

	private T data;

	private List<String> stackTraces;


	public static <T> BaseResponseDTO<T> success(T data) {
		BaseResponseDTO<T> response = new BaseResponseDTO<>();
		response.code = ErrorCode.SUCCESS.getCode();
		response.data = data;
		return response;
	}


	public static <T> BaseResponseDTO<T> fail(ErrorCode errorCode, Throwable ex, Object[] args) {
		BaseResponseDTO<T> response = new BaseResponseDTO<>(errorCode.getCode(), null, null, null) {
			@Override
			public String getMessage() {
				// 延迟获取消息，在序列化时确定语言
				return I18nUtils.getMessage(errorCode.getMsgKey(), args);
			}
		};
		if (ex != null) {
			response.stackTraces = ListUtil.list(false);
			ex2traceList(ex, response.stackTraces, false);
		}
		return response;
	}

	public static <T> BaseResponseDTO<T> fail(ErrorCode errorCode, Throwable ex) {
		BaseResponseDTO<T> response = new BaseResponseDTO<>(errorCode.getCode(), null, null, null) {
			@Override
			public String getMessage() {
				// 延迟获取消息，在序列化时确定语言
				return I18nUtils.getMessage(errorCode.getMsgKey());
			}
		};
		if (ex != null) {
			response.stackTraces = ListUtil.list(false);
			ex2traceList(ex, response.stackTraces, false);
		}
		return response;
	}

	private static void ex2traceList(Throwable ex, List<String> traceList, boolean isCause) {
		if (ex != null) {
			if (traceList.size() > 1000) {
				traceList.add("堆栈过长，请登录服务器查看日志");
				return;
			}
			if (CharSequenceUtil.isEmpty(ex.getMessage())) {
				traceList.add(String.format(isCause ? "Caused by: %s" : "%s", ex.getClass().getName()));
			} else {
				traceList.add(String.format(isCause ? "Caused by: %s: %s" : "%s: %s", ex.getClass().getName(),
						ex.getMessage()));
			}
			for (var t : ex.getStackTrace()) {
				traceList.add(String.format("        at %s.%s(%s:%d)", t.getClassName(), t.getMethodName(),
						t.getFileName(), t.getLineNumber()));
			}
			ex2traceList(ex.getCause(), traceList, true);
		}
	}

}
