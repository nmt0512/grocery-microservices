package com.thieunm.groceryproduct.repository;

import com.thieunm.groceryproduct.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
