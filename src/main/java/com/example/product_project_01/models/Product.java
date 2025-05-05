package com.example.product_project_01.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data     // 'Lombok' annotation that auto-generates a constructor, getters, setters, hashCode, toString, etc.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    // adding a @SequenceGenerator so that we can change the increment (or "allocationSize") to 1 from 50 (default).
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    private Integer id;

    @ManyToOne      // will auto-map to 'category_id' field by default in this case.
//    @JoinColumn(name="category_id")
    @JsonBackReference
    private Category category;

    @Column(unique = true, nullable = false)      // 'name' must exist and be unique
    private String name;

    private Integer price;
    private Integer stockAmount;
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @Transient
//    public String categoryName = "";

    public Product() {} // a No-argument constructor. This is necessary for the 'Entity' functions.

    public Product(Integer id, Category category, String name, Integer price, Integer stockAmount, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
