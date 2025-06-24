package com.pangkazhu.dto.product;

import com.pangkazhu.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:10
 */
@Data
public class ProductResDTO {


	@Schema(description = "产品ID", example = "1001")
	private Long id;

	@Schema(description = "产品名称", example = "数据模型产品")
	@NotBlank(message = "产品名称不能为空")
	@Size(max = 128, message = "产品名称长度不能超过128字符")
	private String name;

	@Schema(description = "产品价格", example = "1999.99")
	@DecimalMin(value = "0.0", inclusive = false, message = "价格必须大于0")
	private BigDecimal price;

	@Schema(description = "产品状态", example = "ON_SALE")
	private ProductStatus status;

	@Schema(description = "产品sku")
	private List<ProductSkuDTO> skus;

}
