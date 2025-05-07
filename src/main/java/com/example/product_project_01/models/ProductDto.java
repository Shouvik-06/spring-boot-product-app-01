package com.example.product_project_01.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data   // Lombok Data class
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private Integer price;
    private Integer stockAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private String categoryName;
    @JsonIgnore
    private Integer categoryId;

    public ProductDto() {}  // No-Args-Constructor

    public Integer getCategoryId() {
        return categoryId;
    }
}
