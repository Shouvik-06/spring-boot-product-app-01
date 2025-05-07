package com.example.product_project_01.models;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductViewController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) Integer categoryId) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
        } else {
            products = productRepository.findAll();
        }
        List<ProductDto> productsDtoList = products
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();

        model.addAttribute("products",productsDtoList);
        return "index";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public RedirectView save(ProductDto productDto) {
//        System.out.println("Create Product Attempted!!!");
//        System.out.println(productDto.toString());
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        return new RedirectView("http://localhost:8080/products");
    }
}
