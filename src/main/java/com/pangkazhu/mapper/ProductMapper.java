package com.pangkazhu.mapper;

import com.pangkazhu.dto.product.ProductCreateDTO;
import com.pangkazhu.dto.product.ProductResDTO;
import com.pangkazhu.po.ProductPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:48
 */
@Mapper(componentModel = "spring", uses = {ProductSkuMapper.class})
public interface ProductMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createTime", ignore = true)
	@Mapping(target = "updateTime", ignore = true)
	ProductPO toPO(ProductCreateDTO dto);


	ProductResDTO toDTO(ProductPO po);


}
