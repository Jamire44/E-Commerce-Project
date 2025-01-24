package com.jamie.ecommerce_proj.controller;

import com.jamie.ecommerce_proj.model.Product;
import com.jamie.ecommerce_proj.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }


}
