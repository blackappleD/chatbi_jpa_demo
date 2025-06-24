package com.pangkazhu.repo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pangkazhu.enums.ProductStatus;
import com.pangkazhu.po.ProductPO;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/20 15:27
 */
public interface ProductRepo extends JpaRepository<ProductPO, String>, JpaSpecificationExecutor<ProductPO> {


	// 规范命名查询
	List<ProductPO> findByStatus(ProductStatus status);

	// 使用@Query自定义JPQL（不推荐）
	@Query("SELECT p FROM ProductPO p WHERE p.price BETWEEN :minPrice AND :maxPrice")
	List<ProductPO> findProductsByPriceRange(@Param("minPrice") BigDecimal minPrice,
	                                         @Param("maxPrice") BigDecimal maxPrice);

	// 使用Specification进行复杂查询（推荐）
	default Page<ProductPO> page(Pageable pageable, String search, ProductStatus status) {
		Specification<ProductPO> specification = new Specification<>() {
			final List<Predicate> predicates = CollUtil.newArrayList();

			public Predicate toPredicate(Root<ProductPO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (CharSequenceUtil.isNotBlank(search)) {
					predicates.add(cb.or(cb.like(root.get(ProductPO.Fields.name), "%" + search + "%")));
				}
				if (ObjectUtil.isNotNull(status)) {
					predicates.add(cb.equal(root.get(ProductPO.Fields.status), status));
				}
				return query.where(predicates.toArray(new Predicate[0])).getRestriction();
			}
		};
		return this.findAll(specification, pageable);
	}

	// 使用EntityGraph解决N+1问题
	@EntityGraph(attributePaths = {"skus"})
	Optional<ProductPO> findWithSkusById(Long id);

}
