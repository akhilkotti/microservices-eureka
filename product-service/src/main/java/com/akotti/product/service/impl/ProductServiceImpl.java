package com.akotti.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.akotti.product.entity.Product;
import com.akotti.product.repository.ProductRepository;
import com.akotti.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }
}

