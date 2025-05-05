package com.example.product_project_01.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {
    @Mapping(target="categoryName", source="category.name")
    // a non-initialized method that will be auto-generated due to @Mapper
    ProductDto toDto(Product product);
}
