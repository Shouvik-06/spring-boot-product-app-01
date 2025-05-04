package com.example.product_project_01.models;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

        /*
    Routes:
    Common route: "/api/products"
    "" : index (list all products)
    "/{id}": find (get by id)
    POST "": create
    PUT "": update
    DELETE "": destroy
    "/category": index (of category), i.e. show all categories
    "/product-by-category/{cateogory_id}": get all products in category
     */

    private final ProductRepository productRepository;
    //included 'category' stuff here instead of creating a CategoryController class.
//    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
    }


    @GetMapping("")
    public List<String> findAll() {
//        return productRepository.findAll();
        List<Product> products =  productRepository.findAll();
        List<String> product_entries = new ArrayList<String>();
        for (Product product : products) {
            product_entries.add(String.format("ID:%s,Name:%s,Price:$%s,Category:%s",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory().getName()));
        }
        return product_entries;
    }

}
