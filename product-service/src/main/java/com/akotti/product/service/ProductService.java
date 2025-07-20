package com.akotti.product.service;

import java.util.List;
import java.util.Optional;

import com.akotti.product.entity.Product;

public interface ProductService {
    Product create(Product product);
    List<Product> getAll();
    Optional<Product> getById(Long id);
}
