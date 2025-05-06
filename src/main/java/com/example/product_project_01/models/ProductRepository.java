package com.example.product_project_01.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // This method gets auto-filled
    public List<Product> findAllByCategoryId(Integer categoryId);
}
