package com.example.product_project_01.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    // return all products, OR if categoryId provided, return products filtered by category
    @GetMapping("")
    public List<ProductDto> findAll(@RequestParam(required = false) Integer categoryId) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
        } else {
            products = productRepository.findAll();
        }
        return products
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }

//    @GetMapping("")
//    public List<Product> findAll() {
////        return productRepository.findAll();
//        List<Product> products = productRepository.findAll();
//        for (Product product: products) {
//            product.categoryName = product.getCategory().getName();
//        }
//        return products;
//    }

    @GetMapping("/{id}")
    public ProductDto findByID(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return productMapper.toDto(product.get());
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ProductDto create(@RequestBody Product product) {
        var category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ProductDto update(@RequestBody Product product, @PathVariable Integer id) {
        product.setId(id);

        // check existence of product and category
        var category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        var old_product = productRepository.findById(id).orElse(null);
        if (old_product == null) {
            throw new ProductNotFoundException();
        }

        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        productRepository.delete(product);
    }
}
