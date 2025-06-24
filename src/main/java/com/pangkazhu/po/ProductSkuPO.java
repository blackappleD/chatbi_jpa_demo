package com.pangkazhu.po;

import com.pangkazhu.po.generator.ChatbiStrIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:31
 */
@Entity
@Table(name = ProductSkuPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class ProductSkuPO extends BasePO.CommonPO<String> {

	public static final String TABLE_NAME = "chatbi_product_sku";

	@Id
	@ChatbiStrIdGenerator
	private String id;

	@Column(length = 50)
	private String title;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id",
			referencedColumnName = ProductPO.Fields.id,
			foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private ProductPO product;

}
