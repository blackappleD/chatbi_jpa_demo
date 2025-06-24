package com.pangkazhu.service;

import com.pangkazhu.dto.product.ProductCreateDTO;
import com.pangkazhu.dto.product.ProductResDTO;
import com.pangkazhu.exception.ProductException;
import com.pangkazhu.mapper.ProductMapper;
import com.pangkazhu.po.ProductPO;
import com.pangkazhu.repo.ProductRepo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 12:06
 */
@Service
public class ProductService {

	@Resource
	private ProductRepo productRepo;

	@Resource
	private ProductMapper productMapper;

	/**
	 * 根据id查询，提供ResDTO
	 *
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public ProductResDTO get(String id) {
		return productMapper.toDTO(findById(id));
	}

	/**
	 * 根据id查询，提供PO
	 *
	 * @param id
	 * @return
	 */
	public ProductPO findById(String id) {
		return productRepo.findById(id)
				.orElseThrow(ProductException::notFound);
	}

	/**
	 * 创建
	 *
	 * @param dto
	 * @return
	 */
	@Transactional
	public String create(ProductCreateDTO dto) {
		ProductPO newProduct = productMapper.toPO(dto);
		ProductPO savedProduct = productRepo.save(newProduct);
		return savedProduct.getId();
	}

}
