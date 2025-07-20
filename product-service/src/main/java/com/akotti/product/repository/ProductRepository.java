package com.akotti.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akotti.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
