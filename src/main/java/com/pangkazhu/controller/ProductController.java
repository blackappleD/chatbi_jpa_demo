package com.pangkazhu.controller;

import com.pangkazhu.annotations.ChatbiResponseBody;
import com.pangkazhu.dto.product.ProductCreateDTO;
import com.pangkazhu.dto.product.ProductResDTO;
import com.pangkazhu.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@ChatbiResponseBody
public class ProductController {

	@Resource
	private ProductService productService;

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ProductResDTO get(@PathVariable String id) {

		return productService.get(id);
	}

	/**
	 * 使用Valid校验
	 *
	 * @param product
	 * @return
	 */
	@PutMapping
	public String update(@RequestBody @Valid ProductCreateDTO product) {
		return productService.create(product);
	}

}