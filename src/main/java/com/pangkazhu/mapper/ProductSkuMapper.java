package com.pangkazhu.mapper;

import com.pangkazhu.dto.product.ProductSkuResDTO;
import com.pangkazhu.po.ProductSkuPO;
import org.mapstruct.Mapper;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:48
 */
@Mapper(componentModel = "spring")
public interface ProductSkuMapper {

	ProductSkuResDTO toDTO(ProductSkuPO po);

}
