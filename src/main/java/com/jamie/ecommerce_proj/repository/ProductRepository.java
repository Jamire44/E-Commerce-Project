package com.jamie.ecommerce_proj.repository;

import com.jamie.ecommerce_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
