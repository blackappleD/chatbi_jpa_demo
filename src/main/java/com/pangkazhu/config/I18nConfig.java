package com.pangkazhu.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 15:13
 */
@Configuration
public class I18nConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();

		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3600);
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator(ResourceBundleMessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}
}