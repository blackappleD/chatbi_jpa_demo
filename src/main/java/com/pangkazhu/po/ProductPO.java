package com.pangkazhu.po;

import com.pangkazhu.enums.ProductStatus;
import com.pangkazhu.po.generator.ChatbiStrIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:15
 */
@Entity
@Table(name = ProductPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class ProductPO extends BasePO<String> {

	public static final String TABLE_NAME = "chatbi_product";

	@Id
	@ChatbiStrIdGenerator
	private String id;

	// String类型尽量确定长度
	@Column(nullable = false, length = 128)
	@Comment("产品名称")
	private String name;

	// 价格使用BigDecimal，scale表示小数点后两位
	@Column(precision = 10, scale = 2)
	@Comment("产品价格")
	private BigDecimal price;

	// 枚举
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	@Comment("产品状态")
	private ProductStatus status;

	@Version
	@Comment("产品版本")
	private Integer version;

	// 1. 禁止包含业务逻辑
	// 2. 关联关系标注清晰
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductSkuPO> skus = new ArrayList<>();
}

