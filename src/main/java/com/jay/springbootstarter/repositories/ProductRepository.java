package com.jay.springbootstarter.repositories;


import com.jay.springbootstarter.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findAllByIsActiveTrue();


   @Query("SELECT p FROM products p WHERE p.isActive = true AND p.stockQuantity > 0 AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
   List<Product> searchProduct(String keyword);
}
