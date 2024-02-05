package com.sparksupport.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparksupport.product.entity.Sale;

/**
 * Repository interface for performing CRUD operations on Sale entities.
 */
public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
