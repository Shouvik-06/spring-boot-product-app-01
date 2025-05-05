package com.example.product_project_01.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final ProductMapper productMapper;
    //included 'category' stuff here instead of creating a CategoryController class.
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public List<ProductDto> findAll() {
        return productRepository.findAll()
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


    // TODO
    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Integer tempID) {
        if (tempID != null) {
            System.out.println("Category ID exists");
            System.out.println("Category ID: " + tempID);
        }

//        productRepository.save(product);
    }


//    CHECK without ID included in JSON, but instead in path variable
    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Product product, @PathVariable Integer id) {
        productRepository.save(product);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
