package com.pangkazhu;

import com.pangkazhu.po.ProductPO;
import com.pangkazhu.repo.ProductRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackageClasses = ProductPO.class)
@EnableJpaRepositories(basePackageClasses = ProductRepo.class)
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(
				Application.class
		).run(args);
	}
}