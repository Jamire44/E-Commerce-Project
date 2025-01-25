package com.jamie.ecommerce_proj.controller;

import com.jamie.ecommerce_proj.model.Product;
import com.jamie.ecommerce_proj.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(){

        List<Product> products = productService.getProducts();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
        }
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductsById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product newProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id){
        Product product = productService.getProductsById(id);
        byte[] image = product.getImageDate();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(image);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestPart Product product, @RequestPart MultipartFile imageFile){

        Product updatedProduct = null;
        try {
            updatedProduct = productService.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.NOT_FOUND);
        }

        if (updatedProduct != null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Product product = productService.getProductsById(id);
        if (product != null){
            productService.deleteProductById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Not Deleted", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> keywordProduct = productService.searchProductsByKeyword(keyword);
        return new ResponseEntity<>(keywordProduct, HttpStatus.OK);
    }

}
