package com.example.product_project_01.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {
    @Mapping(target="categoryName", source="category.name")
    @Mapping(target="categoryId", source="category.id")
    // a non-initialized method that will be auto-generated due to @Mapper
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
