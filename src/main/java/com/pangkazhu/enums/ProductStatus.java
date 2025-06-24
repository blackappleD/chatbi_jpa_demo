package com.pangkazhu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/19 14:40
 */
@AllArgsConstructor
@Getter
public enum ProductStatus {


	ON_SALE("上架"),
	OFF_SALE("下架");
	private final String label;

}
