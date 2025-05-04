package com.example.product_project_01;

import com.example.product_project_01.models.Category;
import com.example.product_project_01.models.CategoryRepository;
import com.example.product_project_01.models.Product;
import com.example.product_project_01.models.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}


	// Database Seeder
//	@Bean
//	CommandLineRunner runner(ProductRepository productRepo, CategoryRepository categoryRepo) {
//		return args -> {
//			categoryRepo.save(new Category(null,"clothing", null));
//			categoryRepo.save(new Category(null,"electronics", null));
//
//
//			productRepo.save(new Product(null, categoryRepo.findById(1).get(), "T-shirt", 14, 5, null));
//			productRepo.save(new Product(null, categoryRepo.findById(1).get(), "Jeans", 16, 10, null));
//			productRepo.save(new Product(null, categoryRepo.findById(2).get(), "Computer", 200, 5, null));
//		};
//	}
}
