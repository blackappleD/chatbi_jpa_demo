package com.pangkazhu.po.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:35
 */
@IdGeneratorType(StrIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChatbiStrIdGenerator {
	// 可以添加配置参数（如果需要）
	// String prefix() default "chatbi";


}
