package com.sparksupport.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparksupport.product.entity.Product;

/**
 * Repository interface for performing CRUD operations on Product entities.
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
