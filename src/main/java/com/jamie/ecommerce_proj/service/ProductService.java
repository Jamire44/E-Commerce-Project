package com.jamie.ecommerce_proj.service;

import com.jamie.ecommerce_proj.model.Product;
import com.jamie.ecommerce_proj.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    public Product getProductsById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageType(image.getContentType());
        product.setImageName(image.getOriginalFilename());
        product.setImageDate(image.getBytes());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product, MultipartFile imageFile) throws IOException {

        product.setImageDate(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());

        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProductsByKeyword(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
